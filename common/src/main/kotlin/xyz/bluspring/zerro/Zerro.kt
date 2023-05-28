package xyz.bluspring.zerro

import com.google.gson.JsonParser
import com.mojang.authlib.minecraft.MinecraftSessionService
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
import net.fabricmc.api.ModInitializer
import java.net.URL
import java.util.*

class Zerro : ModInitializer {
    val authService = YggdrasilAuthenticationService(null)
    val sessionService: MinecraftSessionService = authService.createMinecraftSessionService()

    override fun onInitialize() {
        instance = this
    }

    fun getIdOfPlayer(username: String): UUID? {
        return try {
            val encoded = URL("https://api.mojang.com/users/profiles/minecraft/$username").readText()
            val json = JsonParser.parseString(encoded).asJsonObject

            UUID.fromString(json.get("id").asString.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        lateinit var instance: Zerro
    }
}