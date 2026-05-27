package com.zixiao.yrtz.mixin.skeletonDontHurtPlayer;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends Mob {

    protected AbstractSkeletonMixin() {
        super(null, null);
    }

        @Overwrite
    protected void registerGoals() {
        AbstractSkeleton abstractSkeleton = (AbstractSkeleton) (Object) this;

        abstractSkeleton.goalSelector.addGoal(2, new RestrictSunGoal(abstractSkeleton));
        abstractSkeleton.goalSelector.addGoal(3, new FleeSunGoal(abstractSkeleton, 1.0D));
        abstractSkeleton.goalSelector.addGoal(3, new AvoidEntityGoal<>(abstractSkeleton, Wolf.class, 6.0F, 1.0D, 1.2D));
        abstractSkeleton.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(abstractSkeleton, 1.0D));
        abstractSkeleton.goalSelector.addGoal(6, new RandomLookAroundGoal(abstractSkeleton));
        abstractSkeleton.targetSelector.addGoal(1, new HurtByTargetGoal(abstractSkeleton));
        abstractSkeleton.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(abstractSkeleton, IronGolem.class, true));
        abstractSkeleton.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(abstractSkeleton, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }
}