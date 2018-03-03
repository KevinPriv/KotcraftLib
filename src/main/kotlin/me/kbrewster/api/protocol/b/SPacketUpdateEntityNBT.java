package me.kbrewster.api.protocol.b;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

import java.io.IOException;

public class SPacketUpdateEntityNBT  implements Packet<INetHandlerPlayClient> {
    private int entityId;
    private NBTTagCompound tagCompound;

    public SPacketUpdateEntityNBT() {
    }

    public SPacketUpdateEntityNBT(int entityIdIn, NBTTagCompound tagCompoundIn) {
        this.entityId = entityIdIn;
        this.tagCompound = tagCompoundIn;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarInt();
        this.tagCompound = buf.readCompoundTag();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarInt(this.entityId);
        buf.writeCompoundTag(this.tagCompound);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayClient handler) {
        //   handler.handleEntity(this);
    }

    public NBTTagCompound getTagCompound() {
        return this.tagCompound;
    }

    public Entity getEntity(World worldIn) {
        return worldIn.getEntityByID(this.entityId);
    }
}