package xyz.bluspring.zerro.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.Packet1Login;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Accessor;
import xyz.bluspring.zerro.Zerro;

import java.net.InetAddress;

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
    @Overwrite
    public void run() {
        try {
            String serverId = ((NetLoginHandlerAccessor) this.getLoginHandler()).getServerId();
            // apparently MC doesn't even bother having the UUID??? so that's interesting
            GameProfile profile = new GameProfile(null, this.getLoginPacket().username);

            InetAddress address = ((NetworkManagerAccessor) this.getLoginHandler().netManager).getNetworkSocket().getInetAddress();

            GameProfile authedProfile = Zerro.Companion.getInstance().getSessionService().hasJoinedServer(profile, serverId, address);

            if (authedProfile == null)
                throw new IllegalStateException("Player has not logged in!");

            ((NetLoginHandlerAccessor) this.getLoginHandler()).setLoginPacket(this.getLoginPacket());
        } catch (Exception e) {
            e.printStackTrace();
            this.getLoginHandler().kickUser("Failed to verify username!");
        }
    }
}
