package me.kbrewster.api.mixins.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketTeams;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
import java.util.Collection;

@Mixin(SPacketTeams.class)
public class MixinSPacketTeams {

    @Shadow private String name;

    @Shadow private int action;

    @Shadow private String displayName;

    @Shadow private String prefix;

    @Shadow private String suffix;

    @Shadow private int friendlyFlags;

    @Shadow private String nameTagVisibility;

    @Shadow private int color;

    @Shadow @Final private Collection<String> players;

    /**
     * Reads the raw packet data from the data stream.
     */
    @Overwrite
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.name = buf.readString(16);
        this.action = buf.readByte();

        if (this.action == 0 || this.action == 2)
        {
            this.displayName = buf.readString(32);
            this.prefix = buf.readString(16);
            this.suffix = buf.readString(16);
            this.friendlyFlags = buf.readByte();
            this.nameTagVisibility = buf.readString(32);
      //      this.collisionRule = buf.readString(32);
            this.color = buf.readByte();
        }

        if (this.action == 0 || this.action == 3 || this.action == 4)
        {
            int i = buf.readVarInt();

            for (int j = 0; j < i; ++j)
            {
                this.players.add(buf.readString(40));
            }
        }
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    @Overwrite
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeString(this.name);
        buf.writeByte(this.action);

        if (this.action == 0 || this.action == 2)
        {
            buf.writeString(this.displayName);
            buf.writeString(this.prefix);
            buf.writeString(this.suffix);
            buf.writeByte(this.friendlyFlags);
            buf.writeString(this.nameTagVisibility);
       //     buf.writeString(this.collisionRule);
            buf.writeByte(this.color);
        }

        if (this.action == 0 || this.action == 3 || this.action == 4)
        {
            buf.writeVarInt(this.players.size());

            for (String s : this.players)
            {
                buf.writeString(s);
            }
        }
    }

}
