package xyz.bluspring.zerro.mixin;

import net.minecraft.src.NetServerHandler;
import net.minecraft.src.ServerConfigurationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetServerHandler.class)
public class NetServerHandlerMixin {
    @Redirect(method = {"handlePlace", "handleBlockDig"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ServerConfigurationManager;isOp(Ljava/lang/String;)Z"))
    private boolean disableSpawnProtection(ServerConfigurationManager instance, String s) {
        return true;
    }
}
