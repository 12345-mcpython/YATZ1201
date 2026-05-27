package com.zixiao.yrtz.mixin.zombieAndSkeletonPickup;

import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Zombie.class)
public abstract class ZombiePickMixin {

    @Redirect(
            method = "finalizeSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Zombie;setCanPickUpLoot(Z)V"
            )
    )
    private void alwaysCanPickUpLoot(Zombie zombie, boolean value) {
        zombie.setCanPickUpLoot(true);
    }
}
