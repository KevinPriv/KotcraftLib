package me.kbrewster.api.mixins.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(SPacketJoinGame.class)
public class MixinSPacketJoinGame {

    @Shadow private int playerId;

    @Shadow private boolean hardcoreMode;

    @Shadow private GameType gameType;

    @Shadow private int dimension;

    @Shadow private EnumDifficulty difficulty;

    @Shadow private int maxPlayers;

    @Shadow private WorldType worldType;

    @Shadow private boolean reducedDebugInfo;

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.playerId = buf.readInt();
        int i = buf.readUnsignedByte();
        this.hardcoreMode = (i & 8) == 8;
        i = i & -9;
        this.gameType = GameType.getByID(i);
        this.dimension = buf.readByte();
        this.difficulty = EnumDifficulty.getDifficultyEnum(buf.readUnsignedByte());
        this.maxPlayers = buf.readUnsignedByte();
        this.worldType = WorldType.parseWorldType(buf.readString(16));

        if (this.worldType == null)
        {
            this.worldType = WorldType.DEFAULT;
        }

        this.reducedDebugInfo = buf.readBoolean();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeInt(this.playerId);
        int i = this.gameType.getID();

        if (this.hardcoreMode)
        {
            i |= 8;
        }

        buf.writeByte(i);
        buf.writeInt(this.dimension);
        buf.writeByte(this.difficulty.getDifficultyId());
        buf.writeByte(this.maxPlayers);
        buf.writeString(this.worldType.getName());
        buf.writeBoolean(this.reducedDebugInfo);
    }
}
