package me.kbrewster.api.mixins.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(SPacketSoundEffect.class)
public class MixinSPacketSoundEffect {

    @Shadow private SoundEvent sound;

    @Shadow private SoundCategory category;

    @Shadow private int posX;

    @Shadow private int posY;

    @Shadow private int posZ;

    @Shadow private float soundVolume;

    @Shadow private float soundPitch;

    @Overwrite
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        String soundName = buf.readString(256);
        SoundEvent.REGISTRY.forEach(sound -> {
            System.out.println(sound.getSoundName().getResourceDomain());

        });
       // this.sound =
        this.posX = buf.readInt();
        this.posY = buf.readInt();
        this.posZ = buf.readInt();
        this.soundVolume = buf.readFloat();
        this.soundPitch = buf.readUnsignedByte();

     /*   this.sound = SoundEvent.REGISTRY.getObjectById(buf.readVarInt());
        this.category = (SoundCategory)buf.readEnumValue(SoundCategory.class);
        this.posX = buf.readInt();
        this.posY = buf.readInt();
        this.posZ = buf.readInt();
        this.soundVolume = buf.readFloat();
        this.soundPitch = buf.readFloat();*/
    }

    @Overwrite
    public void writePacketData(PacketBuffer buf) throws IOException
    {
    /*    buf.writeVarInt(SoundEvent.REGISTRY.getIDForObject(this.sound));
        buf.writeEnumValue(this.category);
        buf.writeInt(this.posX);
        buf.writeInt(this.posY);
        buf.writeInt(this.posZ);
        buf.writeFloat(this.soundVolume);
        buf.writeFloat(this.soundPitch);*/
    }
}
