package com.zixiao.yrtz.steve.ai;

import com.zixiao.yrtz.steve.normalSteve.renzheSteve.renzheSteveEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.phys.Vec3;

public class fireRandomStrollGoal extends WaterAvoidingRandomStrollGoal {

    private int cooldown = 0;
    private boolean firstUse = true;

    public fireRandomStrollGoal(PathfinderMob mob, double speed) {
        super(mob, speed);
    }

    @Override
    public boolean canUse() {
        if (!(this.mob instanceof renzheSteveEntity)) {
            return super.canUse(); // 其他生物保持原逻辑
        }

        if (!firstUse && cooldown > 0) {
            cooldown--;
            return false;
        }

        Vec3 pos = this.getPosition();
        if (pos == null) return false;

        this.wantedX = pos.x;
        this.wantedY = pos.y;
        this.wantedZ = pos.z;

        if (firstUse) {
            firstUse = false;
        } else {
            cooldown = 50*20 + this.mob.getRandom().nextInt(21*20); // 50*20 = 50秒，21*20=21秒
        }

        return true;
    }
}
