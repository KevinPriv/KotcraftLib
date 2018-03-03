package me.kbrewster.api.protocol

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.common.collect.Maps
import net.minecraft.network.EnumConnectionState
import net.minecraft.network.EnumPacketDirection
import net.minecraft.network.Packet
import org.apache.logging.log4j.LogManager

abstract class ProtocolAdapter {

    abstract val protocol: Protocols

    abstract val settings: ProtocolSettings

    val STATES_BY_CLASS = Maps.newHashMap<Class<out Packet<*>>, EnumConnectionState>()

    val stateConnectionMaps: MutableMap<EnumConnectionState, MutableMap<EnumPacketDirection,
            BiMap<Int, Class<out Packet<*>>>>> = Maps.newEnumMap(EnumConnectionState::class.java)


    protected fun registerPacket(state: EnumConnectionState,
                                 direction: EnumPacketDirection,
                                 packetClass: Class<out Packet<*>>) {
        var bimap: BiMap<Int, Class<out Packet<*>>>? = this.stateConnectionMaps[state]!![direction]

        if (bimap == null) {
            bimap = HashBiMap.create()
            this.stateConnectionMaps[state]!!.put(direction, bimap)
        }

        if (bimap!!.containsValue(packetClass)) {
            val s = direction.toString() + " packet " + packetClass + " is already known to ID " + bimap.inverse()[packetClass]
           LogManager.getLogger().fatal(s)
            throw IllegalArgumentException(s)
        } else {
            bimap.put(Integer.valueOf(bimap.size), packetClass)
        }
    }

    @Throws(InstantiationException::class, IllegalAccessException::class)
    fun getPacket(state: EnumConnectionState, direction: EnumPacketDirection, packetId: Int): Packet<*>? {
        val oclass = this.stateConnectionMaps[state]!![direction]!![Integer.valueOf(packetId)] as Class<*>?
        println("Recieving ${oclass?.simpleName} ID: $packetId($state)")
        return if (oclass == null) null else oclass.newInstance() as Packet<*>
    }

    fun getPacketId(state: EnumConnectionState, direction: EnumPacketDirection, packetIn: Packet<*>): Int {
        try {
            val packet = (this.stateConnectionMaps[state]!![direction] as BiMap<*, *>).inverse()[packetIn.javaClass] as Int
            println("Sending ${packetIn.javaClass.simpleName} ID: $packet($state)")
            return packet
        } catch(e: Exception) {
            e.printStackTrace()
            println("Error while sending ${packetIn.javaClass.simpleName}")
        }
        return 0
    }
}