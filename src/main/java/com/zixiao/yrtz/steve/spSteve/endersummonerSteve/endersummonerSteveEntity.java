package com.zixiao.yrtz.steve.spSteve.endersummonerSteve;

import com.zixiao.yrtz.reegister.ModEntities;
import com.zixiao.yrtz.steve.baseEntity.SteveEntity;
import com.zixiao.yrtz.steve.ai.enderSteveAttackGoal;
import net.minecraft.world.entity.*;
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
import net.minecraft.world.level.ServerLevelAccessor;

public class endersummonerSteveEntity extends Monster {

    public endersummonerSteveEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1,
                new enderSteveAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3,
                new WaterAvoidingRandomStrollGoal(this, 1.0D));
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
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    @Override
    public boolean hurt(net.minecraft.world.damagesource.DamageSource source, float amount) {

        boolean result = super.hurt(source, amount);

        if (!this.level().isClientSide && this.isAlive()) {

            EntityType<?>[] types = new EntityType[]{
                    ModEntities.DEAD_SUMMONER_STEVE.get(),
                    ModEntities.DEAD_ARROW_STEVE.get(),
                    ModEntities.DEAD_RENZHE_STEVE.get(),
                    ModEntities.DEAD_DIAMOND_STEVE.get(),
                    ModEntities.DEAD_IRON_STEVE.get(),
                    ModEntities.DEAD_MINER_STEVE.get(),
                    ModEntities.DEAD_FARMER_STEVE.get()
            };

            EntityType<?> type = types[this.random.nextInt(types.length)];

            SteveEntity summoned = (SteveEntity) type.create(this.level());

            summoned.moveTo(
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    this.getYRot(),
                    this.getXRot()
            );

            summoned.finalizeSpawn(
                    (ServerLevelAccessor) this.level(),
                    this.level().getCurrentDifficultyAt(summoned.blockPosition()),
                    MobSpawnType.MOB_SUMMONED,
                    null,
                    null
            );

            this.level().addFreshEntity(summoned);
        }

        return result;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (target instanceof SteveEntity) {
            return false;
        }
        return super.canAttack(target);
    }

    @Override
    public boolean isAlliedTo(Entity entity) {
        if (entity instanceof SteveEntity) {
            return true;
        }
        return super.isAlliedTo(entity);
    }
}