package xyz.bluspring.zerro.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.Packet1Login;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.bluspring.zerro.Zerro;

@Mixin(targets = "net.minecraft.src.ThreadLoginVerifier")
public abstract class ThreadLoginVerifierMixin {
    @Accessor
    public abstract NetLoginHandler getLoginHandler();

    @Accessor
    public abstract Packet1Login getLoginPacket();

    /**
     * @author BluSpring
     * @reason Use AuthLib's Join Server system instead
     */
    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    public void run(CallbackInfo ci) {
        ci.cancel();

        try {
            String serverId = ((NetLoginHandlerAccessor) this.getLoginHandler()).getServerId();
            // apparently MC doesn't even bother having the UUID??? so that's interesting
            GameProfile profile = new GameProfile(null, this.getLoginPacket().username);

            GameProfile authedProfile = Zerro.Companion.getInstance().getSessionService().hasJoinedServer(profile, serverId, null);

            if (authedProfile == null)
                throw new IllegalStateException("Player has not logged in!");

            ((NetLoginHandlerAccessor) this.getLoginHandler()).setLoginPacket(this.getLoginPacket());
        } catch (Exception e) {
            e.printStackTrace();
            this.getLoginHandler().kickUser("Failed to verify username!");
        }
    }
}
