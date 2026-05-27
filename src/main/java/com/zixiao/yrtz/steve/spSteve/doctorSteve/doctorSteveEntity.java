package com.zixiao.yrtz.steve.spSteve.doctorSteve;

import com.zixiao.yrtz.steve.baseEntity.modMonster;
import com.zixiao.yrtz.steve.baseEntity.normalSteveEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class doctorSteveEntity extends normalSteveEntity implements RangedAttackMob {

    public doctorSteveEntity(EntityType<? extends modMonster> type, Level level) {
        super(type, level);
        ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
        this.setItemSlot(EquipmentSlot.MAINHAND, bottle);

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
                new RangedAttackGoal(this, 1.0D, 60, 10.0F));
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

        @Override
    public void performRangedAttack(LivingEntity target, float velocity) {

        if (this.level().isClientSide) return;

        double dx = target.getX() - this.getX();
        double dy = target.getEyeY() - 1.1D - this.getY();
        double dz = target.getZ() - this.getZ();

        double dist = Math.sqrt(dx * dx + dz * dz);
        ThrownPotion potion = new ThrownPotion(this.level(), this);

        ItemStack stack = new ItemStack(Items.SPLASH_POTION);
        ItemStack potionStack = PotionUtils.setPotion(stack, Potions.HEALING);

        potion.setItem(potionStack);
        potion.shoot(
                dx,
                dy + dist * 0.2D,
                dz,
                0.75F,
                8.0F
        );

        this.level().addFreshEntity(potion);

        if (!this.isSilent()) {
            this.level().playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    SoundEvents.WITCH_THROW,
                    this.getSoundSource(),
                    1.0F,
                    0.8F + this.random.nextFloat() * 0.4F
            );
        }
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}