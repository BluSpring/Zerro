package xyz.bluspring.zerro.mixin;

import net.minecraft.client.MinecraftApplet;
import net.minecraft.src.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftApplet.class)
public class MinecraftAppletMixin {
    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lnet/minecraft/src/Session;sessionId:Ljava/lang/String;"))
    public String avoidPrintingSessionId(Session instance) {
        return "(session ID omitted for security reasons)";
    }
}
