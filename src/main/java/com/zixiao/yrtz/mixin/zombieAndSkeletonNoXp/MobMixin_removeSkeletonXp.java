package com.zixiao.yrtz.mixin.zombieAndSkeletonNoXp;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin_removeSkeletonXp {

    @Inject(method = "getExperienceReward", at = @At("HEAD"), cancellable = true)
    private void removeSkeletonXp(CallbackInfoReturnable<Integer> cir) {
        if ((Object)this instanceof AbstractSkeleton) {
            cir.setReturnValue(0);
        }
    }
}
