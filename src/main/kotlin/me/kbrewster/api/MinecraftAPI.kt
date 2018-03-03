@file:JvmName("MinecraftAPI")
package me.kbrewster.api

import me.kbrewster.api.protocol.Protocols
import net.minecraft.client.Minecraft
import org.apache.logging.log4j.LogManager

const val VERSION = "SNAPSHOT-1.0"

var FORGE = false
var PROTOCOL = Protocols.`47`
val PROTOCOL_VERSION = Integer.parseInt(PROTOCOL.name)

val logger = LogManager.getLogger("MCAPI")!!

val mc = Minecraft.getMinecraft()

var tickSpeed = 20