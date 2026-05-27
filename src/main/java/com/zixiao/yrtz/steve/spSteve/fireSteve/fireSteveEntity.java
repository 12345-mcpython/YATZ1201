package com.zixiao.yrtz.steve.spSteve.fireSteve;

import com.zixiao.yrtz.steve.baseEntity.normalSteveEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;

public class fireSteveEntity extends normalSteveEntity implements RangedAttackMob {
    private final RangedAttackGoal fireballGoal =
            new RangedAttackGoal(this, 1.0D, 10, 16.0F);

    public fireSteveEntity(EntityType<? extends fireSteveEntity> type, Level level) {
        super(type, level);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(
                1,
                new RangedAttackGoal(this, 1.0D, 10, 16.0F)
        );
        this.goalSelector.addGoal(
                4,
                new LookAtPlayerGoal(this, Player.class, 8.0F)
        );

        this.goalSelector.addGoal(
                5,
                new RandomLookAroundGoal(this)
        );
        this.targetSelector.addGoal(
                1,
                new HurtByTargetGoal(this)
        );

        this.targetSelector.addGoal(
                2,
                new NearestAttackableTargetGoal<>(this, Player.class, true)
        );
        this.targetSelector.addGoal(
                3,
                new NearestAttackableTargetGoal<>(
                        this,
                        Zombie.class,
                        true,
                        target -> !(target instanceof ZombifiedPiglin)
                )
        );
    }
    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {

        double dx = target.getX() - this.getX();
        double dy = target.getY(0.5D) - this.getY(0.5D);
        double dz = target.getZ() - this.getZ();

        boolean ghastFireball = this.random.nextInt(10) == 0;

        if (ghastFireball) {
            LargeFireball fireball = new LargeFireball(
                    this.level(),
                    this,
                    dx,
                    dy,
                    dz,
                    1
            );

            fireball.setPos(
                    this.getX(),
                    this.getEyeY() - 0.1D,
                    this.getZ()
            );

            this.playSound(
                    SoundEvents.GHAST_SHOOT,
                    1.0F,
                    1.0F / (this.random.nextFloat() * 0.4F + 0.8F)
            );

            this.level().addFreshEntity(fireball);

        } else {
            SmallFireball fireball = new SmallFireball(
                    this.level(),
                    this,
                    dx,
                    dy,
                    dz
            );

            fireball.setPos(
                    this.getX(),
                    this.getEyeY() - 0.1D,
                    this.getZ()
            );

            this.playSound(
                    SoundEvents.BLAZE_SHOOT,
                    1.0F,
                    1.0F / (this.random.nextFloat() * 0.4F + 0.8F)
            );

            this.level().addFreshEntity(fireball);
        }
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
}