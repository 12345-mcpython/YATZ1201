package com.zixiao.yrtz.steve.ai;

import com.zixiao.yrtz.steve.baseEntity.SteveEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SteveAttackGoal extends MeleeAttackGoal {

    private final SteveEntity steve;
    private int raiseArmTicks;

    public SteveAttackGoal(SteveEntity steve, double speed, boolean longMemory) {
        super(steve, speed, longMemory);
        this.steve = steve;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.steve.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();

        this.raiseArmTicks++;

        LivingEntity target = this.steve.getTarget();

        if (target == null) {
            this.steve.setAggressive(false);
            return;
        }
        if (this.raiseArmTicks >= 5 &&
                this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.steve.setAggressive(true);
        } else {
            this.steve.setAggressive(false);
        }
    }
}