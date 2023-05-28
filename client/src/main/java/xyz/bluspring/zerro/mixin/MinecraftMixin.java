package xyz.bluspring.zerro.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftApplet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.bluspring.zerro.client.ZerroClient;

import java.awt.*;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    public void addMinecraftReference(Component canvas, Canvas minecraftApplet, MinecraftApplet i, int j, int bl, boolean par6, CallbackInfo ci) {
        ZerroClient.setMinecraft((Minecraft) (Object) this);
    }
}
