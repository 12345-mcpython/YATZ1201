package com.zixiao.yrtz.mixin.zombieAndSkeletonPickup;

import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin {

    @Redirect(
            method = "finalizeSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/AbstractSkeleton;setCanPickUpLoot(Z)V"
            )
    )
    private void alwaysCanPickUpLoot(AbstractSkeleton skeleton, boolean value) {
        skeleton.setCanPickUpLoot(true);
    }
}
