package com.zixiao.yrtz.mixin.ironGolemMixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.DefendVillageTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IronGolem.class)
public class IronGolemMixin {

    @Overwrite
    protected void registerGoals() {
        IronGolem self = (IronGolem)(Object)this;

        self.goalSelector.addGoal(1, new MeleeAttackGoal(self, 1.0D, true));
        self.goalSelector.addGoal(2, new MoveTowardsTargetGoal(self, 0.9D, 32.0F));
        self.goalSelector.addGoal(2, new MoveBackToVillageGoal(self, 0.6D, false));
        self.goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(self, 0.6D));
        self.goalSelector.addGoal(5, new OfferFlowerGoal(self));
        self.goalSelector.addGoal(7, new LookAtPlayerGoal(self, Player.class, 6.0F));
        self.goalSelector.addGoal(8, new RandomLookAroundGoal(self));

        self.targetSelector.addGoal(1, new DefendVillageTargetGoal(self));
        self.targetSelector.addGoal(2, new HurtByTargetGoal(self));

        self.targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<>(
                        self,
                        Player.class,
                        10,
                        true,
                        false,
                        target -> true
                )
        );

        self.targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<>(
                        self,
                        Mob.class,
                        5,
                        false,
                        false,
                        e -> e instanceof Enemy && !(e instanceof Creeper)
                )
        );

        self.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(self, false));
    }
}