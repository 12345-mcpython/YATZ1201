package com.zixiao.yrtz.steve.baseEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class normalSteveEntity extends SteveEntity {

    public normalSteveEntity(EntityType<? extends modMonster> type, Level level) {
        super(type, level);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.noActionTime = 0;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

}