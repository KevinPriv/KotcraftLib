
package me.kbrewster.api.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Shadow
    private final Timer timer = new Timer(20.0F);

}
