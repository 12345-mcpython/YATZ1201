package com.zixiao.yrtz.steve.deadSteve.deadarrowSteve;

import com.zixiao.yrtz.steve.baseEntity.SteveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class DeadArrowSteveEntity extends SteveEntity implements RangedAttackMob {
    private final RangedBowAttackGoal<DeadArrowSteveEntity> bowGoal =
            new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal meleeGoal =
            new MeleeAttackGoal(this, 1.2D, false) {

                @Override
                public void start() {
                    super.start();
                    DeadArrowSteveEntity.this.setAggressive(true);
                }

                @Override
                public void stop() {
                    super.stop();
                    DeadArrowSteveEntity.this.setAggressive(false);
                }
            };

    public DeadArrowSteveEntity(EntityType<? extends DeadArrowSteveEntity> type, Level level) {
        super(type, level);

        ItemStack bow = new ItemStack(Items.BOW);
        if (this.random.nextFloat() < 0.1F) {
            bow.enchant(Enchantments.POWER_ARROWS, 1);
        }
        this.setItemSlot(EquipmentSlot.MAINHAND, bow);
        this.reassessWeaponGoal();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level,
                                        DifficultyInstance difficulty,
                                        MobSpawnType spawnType,
                                        @Nullable SpawnGroupData spawnData,
                                        @Nullable CompoundTag tag) {

        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);

        RandomSource random = this.getRandom();
        if (random.nextBoolean()) {
            this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
            this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));

        } else {
            this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
            this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
        }

        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
        this.setDropChance(EquipmentSlot.HEAD, 0.0F);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);
        this.setDropChance(EquipmentSlot.FEET, 0.0F);

        return data;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
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
    public void reassessWeaponGoal() {

        if (this.level().isClientSide) {
            return;
        }

        this.goalSelector.removeGoal(this.meleeGoal);
        this.goalSelector.removeGoal(this.bowGoal);

        ItemStack itemstack = this.getMainHandItem();
        if (itemstack.is(Items.BOW)) {

            int attackInterval = 20;
            if (this.level().getDifficulty() != Difficulty.HARD) {
                attackInterval = 40;
            }

            this.bowGoal.setMinAttackInterval(attackInterval);

            this.goalSelector.addGoal(2, this.bowGoal);

        } else {
            this.goalSelector.addGoal(2, this.meleeGoal);
        }
    }
    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {

        ItemStack arrowStack = this.getProjectile(
                this.getItemInHand(
                        ProjectileUtil.getWeaponHoldingHand(
                                this,
                                item -> item instanceof net.minecraft.world.item.BowItem
                        )
                )
        );

        AbstractArrow arrow =
                ProjectileUtil.getMobArrow(this, arrowStack, velocity);

        double dx = target.getX() - this.getX();
        double dy = target.getY(0.3333333333333333D) - arrow.getY();
        double dz = target.getZ() - this.getZ();

        double distance = Math.sqrt(dx * dx + dz * dz);

        arrow.shoot(
                dx,
                dy + distance * 0.2D,
                dz,
                1.6F,
                (float)(14 - this.level().getDifficulty().getId() * 4)
        );

        this.playSound(
                SoundEvents.SKELETON_SHOOT,
                1.0F,
                1.0F / (this.random.nextFloat() * 0.4F + 0.8F)
        );

        this.level().addFreshEntity(arrow);
    }
    @Override
    public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
        return weapon == Items.BOW;
    }
    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {

        super.setItemSlot(slot, stack);

        if (!this.level().isClientSide) {
            this.reassessWeaponGoal();
        }
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}