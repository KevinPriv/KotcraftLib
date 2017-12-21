package me.kbrewster.api.mixins.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryEffectRenderer.class)
public abstract class MixinInventoryEffectRenderer extends GuiContainer {

    @Shadow
    protected boolean hasActivePotionEffects;

    public MixinInventoryEffectRenderer(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    @Inject(method = "updateActivePotionEffects", at = @At("HEAD"), cancellable = true)
    private void updateActivePotionEffects(CallbackInfo ci) {
        this.hasActivePotionEffects = !mc.player.getActivePotionEffects().isEmpty();
        this.guiLeft = (this.width - this.xSize) / 2;
        ci.cancel();
    }

}
