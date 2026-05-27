package com.zixiao.yrtz.mixin.zombieDontHurtPlayer;

import com.zixiao.yrtz.steve.baseEntity.SteveEntity;
import com.zixiao.yrtz.steve.spSteve.endersummonerSteve.endersummonerSteveEntity;
import com.zixiao.yrtz.steve.spSteve.xmasSteve.xmasSteveEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.zixiao.yrtz.steve.ai.ZombieBowAttackGoal;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Mob {

    protected ZombieMixin() {
        super(null, null);
    }

        @Overwrite
    protected void addBehaviourGoals() {
        Zombie zombie = (Zombie) (Object) this;
        zombie.goalSelector.addGoal(2, new net.minecraft.world.entity.ai.goal.ZombieAttackGoal(zombie, 1.0D, false));
        zombie.goalSelector.addGoal(2, new ZombieBowAttackGoal(zombie, 1.0D, 10,10,15F));
        zombie.goalSelector.addGoal(6, new net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal(zombie, 1.0D, true, 4, zombie::canBreakDoors));
        zombie.goalSelector.addGoal(7, new net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal(zombie, 1.0D));
        zombie.targetSelector.addGoal(1, new HurtByTargetGoal(zombie));

        zombie.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(zombie, SteveEntity.class, true));
        zombie.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(zombie, endersummonerSteveEntity.class, true));
        zombie.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(zombie, xmasSteveEntity.class, true));
        zombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, AbstractVillager.class, false));
        zombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, IronGolem.class, true));
        zombie.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(zombie, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }
}
