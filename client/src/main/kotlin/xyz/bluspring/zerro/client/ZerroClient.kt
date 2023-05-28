package xyz.bluspring.zerro.client

import com.mojang.authlib.GameProfile
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.Minecraft
import xyz.bluspring.zerro.Zerro
import java.util.*

class ZerroClient : ClientModInitializer {
    override fun onInitializeClient() {
        instance = this
    }

    private var profile: GameProfile? = null

    fun getProfile(): GameProfile {
        if (profile == null) {
            val uuid = Zerro.instance.getIdOfPlayer(minecraft.session.username)
            profile = GameProfile(
                uuid ?: UUID.nameUUIDFromBytes(minecraft.session.username.toByteArray()),
                minecraft.session.username
            )
        }

        return profile!!
    }

    companion object {
        lateinit var instance: ZerroClient

        @JvmStatic
        lateinit var minecraft: Minecraft
    }
}