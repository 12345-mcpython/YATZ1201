package com.zixiao.yrtz.steve.normalSteve.minerSteve;

import com.zixiao.yrtz.steve.baseEntity.ModMonster;
import com.zixiao.yrtz.steve.ai.SteveAttackGoal;
import com.zixiao.yrtz.steve.baseEntity.NormalSteveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class MinerSteveEntity extends NormalSteveEntity {

    public MinerSteveEntity(EntityType<? extends ModMonster> type, Level level) {
        super(type, level);
        if (this.getNavigation() instanceof GroundPathNavigation navigation) {
            navigation.setCanOpenDoors(true);
            navigation.setCanPassDoors(true);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(2,
                new SteveAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3,
                new MoveBackToVillageGoal(this, 1.0D, false));
        this.goalSelector.addGoal(6,
                new GolemRandomStrollInVillageGoal(this, 1.0D));
        this.goalSelector.addGoal(7,
                new LookAtPlayerGoal(this, Player.class, 8.0F));

        this.goalSelector.addGoal(8,
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
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level,
                                        DifficultyInstance difficulty,
                                        MobSpawnType spawnType,
                                        @Nullable SpawnGroupData spawnData,
                                        @Nullable CompoundTag tag) {

        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);

        RandomSource random = this.getRandom();
        int type = random.nextInt(5);

        ItemStack pickaxe = ItemStack.EMPTY;

        switch (type) {
            case 0 -> {
                this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
                this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
                pickaxe = new ItemStack(Items.WOODEN_PICKAXE);
            }
            case 1 -> {
                this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
                this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
                pickaxe = new ItemStack(Items.GOLDEN_PICKAXE);
            }
            case 2 -> {
                this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
                this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
                pickaxe = new ItemStack(Items.IRON_PICKAXE);
            }
            case 3 -> {
                this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
                this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
                pickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
            }
            case 4 -> {
                this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
                this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
                pickaxe = new ItemStack(Items.STONE_PICKAXE);
            }
        }
        if (random.nextInt(10) == 0) {
            pickaxe.enchant(Enchantments.BLOCK_EFFICIENCY, 1);
        }

        this.setItemSlot(EquipmentSlot.MAINHAND, pickaxe);

        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
        this.setDropChance(EquipmentSlot.HEAD, 0.0F);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);
        this.setDropChance(EquipmentSlot.FEET, 0.0F);

        return data;
    }
@Override
protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
    super.dropCustomDeathLoot(source, looting, recentlyHit);

    ItemStack mainHand = this.getItemBySlot(EquipmentSlot.MAINHAND);
    int count = this.random.nextInt(3); // 0~2
    if (count == 0) {
        return;
    }
    if (mainHand.is(Items.WOODEN_PICKAXE)) {
        this.spawnAtLocation(new ItemStack(Items.OAK_PLANKS, count));
    } else if (mainHand.is(Items.STONE_PICKAXE)) {
        this.spawnAtLocation(new ItemStack(Items.COBBLESTONE, count));
    } else if (mainHand.is(Items.IRON_PICKAXE)) {
        this.spawnAtLocation(new ItemStack(Items.IRON_INGOT, count));
    } else if (mainHand.is(Items.GOLDEN_PICKAXE)) {
        this.spawnAtLocation(new ItemStack(Items.GOLD_INGOT, count));
    } else if (mainHand.is(Items.DIAMOND_PICKAXE)) {
        this.spawnAtLocation(new ItemStack(Items.DIAMOND, count));
    }
}

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false; // 永久存在
    }
}