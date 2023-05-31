package xyz.bluspring.zerro

import com.google.gson.JsonParser
import com.mojang.authlib.minecraft.MinecraftSessionService
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
import net.fabricmc.api.ModInitializer
import java.math.BigInteger
import java.net.Proxy
import java.net.URL
import java.util.*


class Zerro : ModInitializer {
    val authService = YggdrasilAuthenticationService(Proxy.NO_PROXY)
    val sessionService: MinecraftSessionService = authService.createMinecraftSessionService()

    override fun onInitialize() {
        instance = this
    }

    fun getIdOfPlayer(username: String): UUID? {
        return try {
            val encoded = URL("https://api.mojang.com/users/profiles/minecraft/$username").readText()
            val json = JsonParser.parseString(encoded).asJsonObject

            convertUuid(json.get("id").asString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun convertUuid(string: String): UUID {
        val withoutDashes = string.replace("-".toRegex(), "")
        val bi1 = BigInteger(withoutDashes.substring(0, 16), 16)
        val bi2 = BigInteger(withoutDashes.substring(16, 32), 16)

        return UUID(bi1.toLong(), bi2.toLong())
    }

    companion object {
        lateinit var instance: Zerro
    }
}