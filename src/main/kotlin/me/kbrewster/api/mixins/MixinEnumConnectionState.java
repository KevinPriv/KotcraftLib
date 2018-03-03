package me.kbrewster.api.mixins;

import com.google.common.collect.BiMap;
import me.kbrewster.api.protocol.b.Protocolv47;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(EnumConnectionState.class)
public class MixinEnumConnectionState {

    @Inject(method = "getPacket", at = @At("HEAD"), cancellable = true)
    private void getPacket(EnumPacketDirection direction, int packetId, CallbackInfoReturnable<Packet<?>> ci) throws InstantiationException, IllegalAccessException
    {
        ci.setReturnValue(Protocolv47.INSTANCE.getPacket((EnumConnectionState)(Object) this, direction, packetId));
    }


    @Inject(method = "getPacketId", at = @At("HEAD"), cancellable = true)
    private void getPacketId(EnumPacketDirection direction, Packet<?> packetIn, CallbackInfoReturnable<Integer> ci) throws Exception {
         ci.setReturnValue(Protocolv47.INSTANCE.getPacketId((EnumConnectionState)(Object) this,direction, packetIn));
    }

}
