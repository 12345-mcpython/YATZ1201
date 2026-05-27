package com.zixiao.yrtz.steve.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ZombieBowAttackGoal extends Goal {
    private final Zombie zombie;
    private final Level level;
    private final double speedModifier;
    private final int attackIntervalMin;
    private final int attackIntervalMax;
    private final float attackRadius;
    private int attackTime = -1;
    private int seeTime;
    private int strafeTime = -1;
    private boolean strafingClockwise = false;

    public ZombieBowAttackGoal(Zombie zombie, double speedModifier, int attackIntervalMin, int attackIntervalMax, float attackRadius) {
        this.zombie = zombie;
        this.level = zombie.level();
        this.speedModifier = speedModifier;
        this.attackIntervalMin = attackIntervalMin;
        this.attackIntervalMax = attackIntervalMax;
        this.attackRadius = attackRadius;
    }

    public boolean canUse() {
        ItemStack mainHandItem = this.zombie.getMainHandItem();
        if (mainHandItem.getItem() != Items.BOW) {
            return false;
        } else {
            LivingEntity target = this.zombie.getTarget();
            if (target != null && target.isAlive()) {
                if (target.getType().toString().equals("minecraft:player")) {
                    Player player = (Player)target;
                    if (player.isSpectator() || player.getAbilities().instabuild) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public boolean canContinueToUse() {
        if (this.zombie.getMainHandItem().getItem() != Items.BOW) {
            return false;
        } else {
            LivingEntity target = this.zombie.getTarget();
            if (target != null && target.isAlive()) {
                if (target.getType().toString().equals("minecraft:player")) {
                    Player player = (Player)target;
                    if (player.isSpectator() || player.getAbilities().instabuild) {
                        return false;
                    }
                }

                return !(this.zombie.distanceToSqr(target) > (double)256.0F) && this.isHoldingBow();
            } else {
                return false;
            }
        }
    }

    public void start() {
        super.start();
        this.attackTime = -1;
        this.seeTime = 0;
        this.strafeTime = -1;
    }

    public void stop() {
        super.stop();
        this.seeTime = 0;
        this.zombie.stopUsingItem();
    }

    public void tick() {
        LivingEntity target = this.zombie.getTarget();
        if (target != null) {
            double distance = this.zombie.distanceToSqr(target);
            boolean canSee = this.zombie.getSensing().hasLineOfSight(target);
            double distanceSqrt = Math.sqrt(distance);
            if (canSee) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }

            int attackInterval = this.zombie.getRandom().nextInt(this.attackIntervalMax - this.attackIntervalMin + 1) + this.attackIntervalMin;
            int drawStartTime = attackInterval / 3;
            if (distanceSqrt < (double)5.0F) {
                this.zombie.getNavigation().stop();
                ++this.attackTime;
                Vec3 targetPos = target.position();
                Vec3 zombiePos = this.zombie.position();
                double dx = zombiePos.x - targetPos.x;
                double dz = zombiePos.z - targetPos.z;
                double length = Math.sqrt(dx * dx + dz * dz);
                if (length > (double)0.0F) {
                    dx /= length;
                    dz /= length;
                }

                double retreatDistance = (double)8.0F + this.zombie.getRandom().nextDouble() * (double)4.0F;
                double targetX = zombiePos.x + dx * retreatDistance;
                double targetZ = zombiePos.z + dz * retreatDistance;
                this.zombie.getNavigation().moveTo(targetX, zombiePos.y, targetZ, this.speedModifier * 1.2);
                this.zombie.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else if (!(distance > (double)256.0F) && this.seeTime >= 5) {
                this.zombie.getNavigation().stop();
                ++this.attackTime;
                this.zombie.getLookControl().setLookAt(target, 30.0F, 30.0F);
                this.strafeAroundTarget(target);
            } else {
                this.zombie.getNavigation().moveTo(target, this.speedModifier);
                this.attackTime = -1;
            }

            if (this.attackTime >= attackInterval) {
                ItemStack projectile = this.zombie.getProjectile(this.zombie.getMainHandItem());
                if (!projectile.isEmpty()) {
                    this.performRangedAttack(target, 1.0F);
                    this.attackTime = -attackInterval;
                }
            } else if (this.attackTime >= drawStartTime) {
                this.zombie.startUsingItem(InteractionHand.MAIN_HAND);
            } else {
                this.zombie.stopUsingItem();
            }

        }
    }

    private boolean isHoldingBow() {
        return this.zombie.getMainHandItem().getItem() == Items.BOW;
    }

    private void strafeAroundTarget(LivingEntity target) {
        if (this.strafeTime <= 0) {
            this.strafeTime = 20 + this.zombie.getRandom().nextInt(20);
            this.strafingClockwise = this.zombie.getRandom().nextBoolean();
        } else {
            --this.strafeTime;
        }

        Vec3 targetPos = target.position();
        Vec3 zombiePos = this.zombie.position();
        double dx = targetPos.x - zombiePos.x;
        double dz = targetPos.z - zombiePos.z;
        double strafeX = -dz;
        double strafeZ = dx;
        if (!this.strafingClockwise) {
            strafeX = -strafeX;
            strafeZ = -dx;
        }

        double length = Math.sqrt(strafeX * strafeX + strafeZ * strafeZ);
        if (length > (double)0.0F) {
            strafeX /= length;
            strafeZ /= length;
        }

        double strafeDistance = (double)7.0F + this.zombie.getRandom().nextDouble() * (double)7.0F;
        double targetX = zombiePos.x + strafeX * strafeDistance;
        double targetZ = zombiePos.z + strafeZ * strafeDistance;
        double distanceToTarget = Math.sqrt(Math.pow(targetX - targetPos.x, (double)2.0F) + Math.pow(targetZ - targetPos.z, (double)2.0F));
        double minDistance = (double)5.0F;
        double maxDistance = (double)(this.attackRadius * 1.0F);
        if (distanceToTarget < minDistance) {
            double adjustX = (zombiePos.x - targetPos.x) / distanceToTarget * (minDistance - distanceToTarget);
            double adjustZ = (zombiePos.z - targetPos.z) / distanceToTarget * (minDistance - distanceToTarget);
            targetX += adjustX;
            targetZ += adjustZ;
        } else if (distanceToTarget > maxDistance) {
            double adjustX = (targetPos.x - zombiePos.x) / distanceToTarget * (distanceToTarget - maxDistance);
            double adjustZ = (targetPos.z - zombiePos.z) / distanceToTarget * (distanceToTarget - maxDistance);
            targetX += adjustX;
            targetZ += adjustZ;
        }

        BlockPos targetBlockPos = new BlockPos((int)targetX, (int)this.zombie.getY(), (int)targetZ);
        if (this.level.getBlockState(targetBlockPos.below()).isSolid() && !this.level.getBlockState(targetBlockPos).getFluidState().isSource()) {
            this.zombie.getNavigation().moveTo(targetX, this.zombie.getY(), targetZ, this.speedModifier * 0.8);
        }

    }

    private void performRangedAttack(LivingEntity target, float distanceFactor) {
    this.zombie.getLookControl().setLookAt(target, 180.0F, 180.0F);

    Arrow arrow = new Arrow(EntityType.ARROW, this.zombie.level());
    arrow.setOwner(this.zombie);
    arrow.setPos(this.zombie.getX(), this.zombie.getEyeY(), this.zombie.getZ());

    float velocity = 2.4F;
    float inaccuracy = 0F;

    Vec3 targetVelocity = target.getDeltaMovement();

    double dx0 = target.getX() - this.zombie.getX();
    double dz0 = target.getZ() - this.zombie.getZ();
    double horizontalDistance0 = Math.sqrt(dx0 * dx0 + dz0 * dz0);

    double predictTime = horizontalDistance0 / (velocity * 1.15);

    double futureX = target.getX() + targetVelocity.x * predictTime;
    double futureZ = target.getZ() + targetVelocity.z * predictTime;

    double dx = futureX - this.zombie.getX();
    double dz = futureZ - this.zombie.getZ();
    double horizontalDistance = Math.sqrt(dx * dx + dz * dz);

    double dy = target.getEyeY() - arrow.getY();
    double predictedDy = dy + horizontalDistance * 0.065;

    arrow.shoot(dx, predictedDy, dz, velocity, inaccuracy);

    arrow.setBaseDamage(2.0D * distanceFactor);

    this.zombie.level().addFreshEntity(arrow);

    this.zombie.playSound(
            SoundEvents.SKELETON_SHOOT,
            1.0F,
            1.0F / (this.zombie.getRandom().nextFloat() * 0.4F + 0.8F)
    );
    }
}