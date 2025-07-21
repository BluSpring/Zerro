package xyz.bluspring.zerro.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Mixin(targets = "net.minecraft.src.NetworkAcceptThread")
public abstract class NetworkAcceptThreadMixin {
    @Redirect(method = "run", at = @At(value = "INVOKE", target = "Ljava/net/ServerSocket;accept()Ljava/net/Socket;"))
    private Socket zerro$discardLocalIPs(ServerSocket instance) throws IOException {
        Socket socket = instance.accept();
        InetAddress address = socket.getInetAddress();

        // Discard Pterodactyl pings
        if (address.getHostAddress().equals("172.31.1.1"))
            return null;

        return socket;
    }
}
