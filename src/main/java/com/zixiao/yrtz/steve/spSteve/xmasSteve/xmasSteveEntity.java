package com.zixiao.yrtz.steve.spSteve.xmasSteve;

import com.zixiao.yrtz.steve.baseEntity.SteveEntity;
import com.zixiao.yrtz.steve.ai.xmasSteveAttackGoal;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class xmasSteveEntity extends Monster {
    private static final Item[] DROP_POOL = new Item[]{
            Items.IRON_SHOVEL,
            Items.IRON_PICKAXE,
            Items.IRON_AXE,
            Items.IRON_SWORD,
            Items.WOODEN_SWORD,
            Items.WOODEN_SHOVEL,
            Items.WOODEN_PICKAXE,
            Items.WOODEN_AXE,
            Items.STONE_SWORD,
            Items.STONE_SHOVEL,
            Items.STONE_PICKAXE,
            Items.STONE_AXE,
            Items.DIAMOND_SWORD,
            Items.DIAMOND_SHOVEL,
            Items.DIAMOND_PICKAXE,
            Items.DIAMOND_AXE,
            Items.GOLDEN_SWORD,
            Items.GOLDEN_SHOVEL,
            Items.GOLDEN_PICKAXE,
            Items.GOLDEN_AXE,
            Items.WOODEN_HOE,
            Items.STONE_HOE,
            Items.IRON_HOE,
            Items.DIAMOND_HOE,
            Items.GOLDEN_HOE,
            Items.LEATHER_HELMET,
            Items.LEATHER_CHESTPLATE,
            Items.LEATHER_LEGGINGS,
            Items.LEATHER_BOOTS,
            Items.CHAINMAIL_HELMET,
            Items.CHAINMAIL_CHESTPLATE,
            Items.CHAINMAIL_LEGGINGS,
            Items.CHAINMAIL_BOOTS,
            Items.IRON_HELMET,
            Items.IRON_CHESTPLATE,
            Items.IRON_LEGGINGS,
            Items.IRON_BOOTS,
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Items.GOLDEN_HELMET,
            Items.GOLDEN_CHESTPLATE,
            Items.GOLDEN_LEGGINGS,
            Items.GOLDEN_BOOTS,
            Items.DIAMOND,
            Items.IRON_INGOT,
            Items.GOLD_INGOT,
            Items.GUNPOWDER,
            Items.PAINTING,
            Items.GOLDEN_APPLE,
            Items.ENCHANTED_GOLDEN_APPLE,
            Items.SADDLE,
            Items.REDSTONE,
            Items.SNOWBALL,
            Items.CLAY_BALL,
            Items.BOOK,
            Items.SLIME_BALL,
            Items.COMPASS,
            Items.CLOCK,
            Items.GLOWSTONE_DUST,
            Items.BONE,
            Items.CAKE,
            Items.SHEARS,
            Items.ROTTEN_FLESH,
            Items.ENDER_PEARL,
            Items.BLAZE_ROD,
            Items.GHAST_TEAR,
            Items.GOLD_NUGGET,
            Items.NETHER_WART,
            Items.SPIDER_EYE,
            Items.FERMENTED_SPIDER_EYE,
            Items.BLAZE_POWDER,
            Items.MAGMA_CREAM,
            Items.BREWING_STAND,
            Items.CAULDRON,
            Items.ENDER_EYE,
            Items.GLISTERING_MELON_SLICE,
            Items.EXPERIENCE_BOTTLE,
            Items.FIRE_CHARGE,
            Items.WRITABLE_BOOK,
            Items.EMERALD,
            Items.ITEM_FRAME,
            Items.FLOWER_POT,
            Items.CARROT_ON_A_STICK,
            Items.PUMPKIN_PIE,
            Items.MUSIC_DISC_13,
            Items.MUSIC_DISC_CAT,
            Items.MUSIC_DISC_BLOCKS,
            Items.MUSIC_DISC_CHIRP,
            Items.MUSIC_DISC_FAR,
            Items.MUSIC_DISC_MALL,
            Items.MUSIC_DISC_MELLOHI,
            Items.MUSIC_DISC_STAL,
            Items.MUSIC_DISC_STRAD,
            Items.MUSIC_DISC_WARD,
            Items.MUSIC_DISC_11,
            Items.MUSIC_DISC_WAIT,
            Items.STRING,
            Items.MAP
    };

    public xmasSteveEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1,
                new xmasSteveAttackGoal(this, 1.0D, false));
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
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }
    @Override
    protected void dropCustomDeathLoot(net.minecraft.world.damagesource.DamageSource source,
                                       int looting,
                                       boolean recentlyHit) {

        super.dropCustomDeathLoot(source, looting, recentlyHit);
        if (this.random.nextFloat() <= 0.65F) {

            Item item = DROP_POOL[this.random.nextInt(DROP_POOL.length)];
            int count = this.random.nextFloat() < 0.6F ? 2 : 1;

            for (int i = 0; i < count; i++) {
                this.spawnAtLocation(new ItemStack(item, 1));
            }
        }
    }

    @Override
    public int getExperienceReward() {
        return 53;
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

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.getY() < 63) {
            this.noActionTime = 0;
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return this.getY() < 63;
    }
}