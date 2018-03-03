package me.kbrewster.api.mixins.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
import java.util.Set;

@Mixin(SPacketPlayerPosLook.class)
public class MixinSPacketPlayerPosLook {

    @Shadow private double x;

    @Shadow private double y;

    @Shadow private double z;

    @Shadow private float yaw;

    @Shadow private float pitch;

    @Shadow private Set<SPacketPlayerPosLook.EnumFlags> flags;

    @Shadow private int teleportId;

    @Overwrite
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.yaw = buf.readFloat();
        this.pitch = buf.readFloat();
        this.flags = SPacketPlayerPosLook.EnumFlags.unpack(buf.readUnsignedByte());
      //  this.teleportId = buf.readVarInt();
    }

    @Overwrite
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeFloat(this.yaw);
        buf.writeFloat(this.pitch);
        buf.writeByte(SPacketPlayerPosLook.EnumFlags.pack(this.flags));
    //    buf.writeVarInt(this.teleportId);
    }

}
