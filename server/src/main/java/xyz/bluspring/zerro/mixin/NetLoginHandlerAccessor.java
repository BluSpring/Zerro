package xyz.bluspring.zerro.mixin;

import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.Packet1Login;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NetLoginHandler.class)
public interface NetLoginHandlerAccessor {
    @Accessor
    String getServerId();

    @Accessor("field_9004_h")
    Packet1Login getLoginPacket();

    @Accessor("field_9004_h")
    void setLoginPacket(Packet1Login loginPacket);
}
