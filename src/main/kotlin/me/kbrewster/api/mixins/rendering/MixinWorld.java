package me.kbrewster.api.mixins.rendering;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class MixinWorld {

    @Inject(method = "checkLightFor", at = @At("HEAD"), cancellable = true)
    private void checkLightFor(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Boolean> ci)
    {
        ci.setReturnValue(false);
    }

    @Inject(method = "getLightFromNeighborsFor", at = @At("HEAD"), cancellable = true)
    private void getLightFromNeighborsFor(EnumSkyBlock type, BlockPos pos, CallbackInfoReturnable<Integer> ci)
    {
        ci.setReturnValue(15);
    }

    @Inject(method = "getLightFromNeighbors", at = @At("HEAD"), cancellable = true)
    private void getLightFromNeighbor(BlockPos pos, CallbackInfoReturnable<Integer> ci)
    {
        ci.setReturnValue(15);
    }

    @Inject(method = "getRawLight", at = @At("HEAD"), cancellable = true)
    private void getRawLight(BlockPos pos, EnumSkyBlock lightType, CallbackInfoReturnable<Integer> ci)
    {
        ci.setReturnValue(15);
    }

    @Inject(method = "getLight(Lnet/minecraft/util/math/BlockPos;)I", at = @At("HEAD"), cancellable = true)
    private void getLight(BlockPos pos, CallbackInfoReturnable<Integer> ci)
    {
        ci.setReturnValue(15);
    }

    @Inject(method = "getLight(Lnet/minecraft/util/math/BlockPos;Z)I", at = @At("HEAD"), cancellable = true)
    private void getLight(BlockPos pos, boolean checkNeighbors, CallbackInfoReturnable<Integer> ci)
    {
        ci.setReturnValue(15);
    }
}
