package com.zixiao.yrtz.steve.baseEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SteveEntity extends modMonster {

    public SteveEntity(EntityType<? extends modMonster> type, Level level) {
        super(type, level);
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