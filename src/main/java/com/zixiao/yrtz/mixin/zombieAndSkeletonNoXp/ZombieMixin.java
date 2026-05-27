package com.zixiao.yrtz.mixin.zombieAndSkeletonNoXp;

import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Zombie.class)
public class ZombieMixin {


    @Overwrite
    public int getExperienceReward() {
        return 0;
    }
}
