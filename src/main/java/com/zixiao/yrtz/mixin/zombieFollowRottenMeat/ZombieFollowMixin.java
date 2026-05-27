package com.zixiao.yrtz.mixin.zombieFollowRottenMeat;

import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public abstract class ZombieFollowMixin {

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void addTemptGoal(CallbackInfo ci) {

        Zombie zombie = (Zombie) (Object) this;

        zombie.goalSelector.addGoal(
                3,
                new TemptGoal(
                        zombie,
                        1D,
                        Ingredient.of(Items.ROTTEN_FLESH),
                        false
                )
        );
    }
}
