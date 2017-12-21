package me.kbrewster.api.eventbus

import com.esotericsoftware.reflectasm.MethodAccess
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.ITextComponent

data class EventSubscriber(val instance: Any, val methodAccess: MethodAccess, val mIndex: Int)

annotation class InvokeEvent

class InitializationEvent
class ServerJoinEvent(val server: String, val port: Int)
class ServerLeaveEvent
class RightMouseClickEvent
class LeftMouseClickEvent
class KeypressEvent(val key: Int)
class SpawnpointChangeEvent(val blockPos: BlockPos)
class TickEvent
class RenderEvent
class ChatEvent(val chat: ITextComponent)
class SingleplayerJoinEvent