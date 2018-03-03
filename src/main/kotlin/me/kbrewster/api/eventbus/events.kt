package me.kbrewster.api.eventbus

import com.esotericsoftware.reflectasm.MethodAccess
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.ITextComponent
import java.util.concurrent.TimeUnit

data class EventSubscriber(val instance: Any, val methodAccess: MethodAccess, val mIndex: Int)

annotation class InvokeEvent


class InitializationEvent
class ServerJoinEvent(val server: String, val port: Int)
class ServerLeaveEvent
class MouseClickEvent(val side: Int)
class KeypressEvent(val key: Int, val isRepeat: Boolean)
class SpawnpointChangeEvent(val blockPos: BlockPos)
class TickEvent(val time: TimeUnit)
class RenderEvent
class ChatEvent(val chat: ITextComponent)
class SingleplayerJoinEvent