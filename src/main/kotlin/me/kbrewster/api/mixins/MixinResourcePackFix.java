package me.kbrewster.api.mixins;

import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ResourcePackListEntry.class)
public class MixinResourcePackFix {

    /**
     * Fixes Minecraft's shitty rendering code
     * @author Kevin Brewster
     */
    @Overwrite
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
    {

    }
}
