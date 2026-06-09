package com.zixiao.yrtz.steve.ai;

import com.zixiao.yrtz.steve.spSteve.xmasSteve.XmasSteveEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class XmasSteveAttackGoal extends MeleeAttackGoal {

    private final XmasSteveEntity steve;
    private int raiseArmTicks;

    public XmasSteveAttackGoal(XmasSteveEntity steve, double speed, boolean longMemory) {
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