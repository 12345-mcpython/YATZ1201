package com.zixiao.yrtz.steve.deadSteve.deadrenzheSteve;

import com.zixiao.yrtz.steve.baseEntity.modMonster;
import com.zixiao.yrtz.steve.ai.SteveAttackGoal;
import com.zixiao.yrtz.steve.baseEntity.SteveEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class deadrenzheSteveEntity extends SteveEntity {

    public deadrenzheSteveEntity(EntityType<? extends modMonster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1,
                new SteveAttackGoal(this, 1.3D, false));
        this.goalSelector.addGoal(3,
                new WaterAvoidingRandomStrollGoal(this, 1.3D));
        this.goalSelector.addGoal(4,
                new LookAtPlayerGoal(this, Player.class, 8.0F));

        this.goalSelector.addGoal(5,
                new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1,
                new HurtByTargetGoal(this));

        this.targetSelector.addGoal(2,
                new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<>(this, Zombie.class, true));
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 28.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    @Override
    protected void jumpFromGround() {
        this.setDeltaMovement(this.getDeltaMovement().x, 0.95D, this.getDeltaMovement().z);
        this.hasImpulse = true;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false; // 永久存在
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }
}