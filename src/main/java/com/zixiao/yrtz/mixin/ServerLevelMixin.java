package com.zixiao.yrtz.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {

    @Inject(
            method = "tickNonPassenger",
            at = @At("HEAD"),
            cancellable = true
    )
    private void freezeEntity(Entity p_8648_, CallbackInfo ci) {
        if (shouldFreeze(p_8648_)) {
            p_8648_.setDeltaMovement(Vec3.ZERO);
            p_8648_.hurtMarked = true;
            p_8648_.hasImpulse = false;
            ci.cancel();
        }
    }

    private boolean shouldFreeze(Entity entity) {

        
        return entity.getType().toString().contains("creeper")
                || entity.getType().toString().contains("zombie")
                || entity.getType().toString().contains("enderman");
    }
}