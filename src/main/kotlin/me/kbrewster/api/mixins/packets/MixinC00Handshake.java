package me.kbrewster.api.mixins.packets;

import me.kbrewster.api.MinecraftAPI;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.client.C00Handshake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(C00Handshake.class)
public class MixinC00Handshake {

    @Shadow
    private int protocolVersion;
    @Shadow
    private String ip;
    @Shadow
    private int port;

    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onConstructed(CallbackInfo ci) {
        this.protocolVersion = MinecraftAPI.getPROTOCOL_VERSION();
    }


}
