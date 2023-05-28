package xyz.bluspring.zerro.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.bluspring.zerro.Zerro;
import xyz.bluspring.zerro.client.ZerroClient;

@Mixin(NetClientHandler.class)
public abstract class NetClientHandlerMixin {
    @Invoker
    public abstract void invokeAddToSendQueue(Packet packet);

    @Accessor
    public abstract NetworkManager getNetManager();

    /**
     * @author BluSpring
     * @reason Use AuthLib's Join Server system instead
     */
    @Inject(method = "handleHandshake", at = @At("HEAD"), cancellable = true)
    public void handleHandshake(Packet2Handshake handshakePacket, CallbackInfo ci) {
        ci.cancel();

        Zerro zerro = Zerro.Companion.getInstance();
        ZerroClient zerroClient = ZerroClient.Companion.getInstance();
        Minecraft minecraft = ZerroClient.getMinecraft();

        if (handshakePacket.username.equals("-")) {
            // Offline mode
            this.invokeAddToSendQueue(new Packet1Login(minecraft.session.username, "Password", 8));
        } else {
            try {
                zerro.getSessionService().joinServer(zerroClient.getProfile(), minecraft.session.sessionId, handshakePacket.username);
                this.invokeAddToSendQueue(new Packet1Login(minecraft.session.username, "Password", 8));
            } catch (Exception e) {
                e.printStackTrace();
                this.getNetManager().networkShutdown("disconnect.genericReason", "Internal client error: " + e.toString());
            }
        }
    }
}
