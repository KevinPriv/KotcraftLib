package me.kbrewster.api.mixins.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(SPacketEntityTeleport.class)
public class MixinSPacketEntityTeleport {

    @Shadow private int entityId;

    @Shadow private double posX;

    @Shadow private double posY;

    @Shadow private double posZ;

    @Shadow private byte yaw;

    @Shadow private byte pitch;

    @Shadow private boolean onGround;

    @Overwrite
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.entityId = buf.readVarInt();
        this.posX = buf.readInt();
        this.posY = buf.readInt();
        this.posZ = buf.readInt();
        this.yaw = buf.readByte();
        this.pitch = buf.readByte();
        this.onGround = buf.readBoolean();
    }


    @Overwrite
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeVarInt(this.entityId);
        buf.writeInt(((int) this.posX));
        buf.writeInt((int) this.posY);
        buf.writeInt((int)this.posZ);
        buf.writeByte(this.yaw);
        buf.writeByte(this.pitch);
        buf.writeBoolean(this.onGround);
    }
}
