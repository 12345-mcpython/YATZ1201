package com.zixiao.yrtz.mixin.playerTypeUndead;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "getMobType", at = @At("HEAD"), cancellable = true)
    private void injectMobType(CallbackInfoReturnable<MobType> cir) {

        if ((Object)this instanceof Player) {
            cir.setReturnValue(MobType.UNDEAD);
        }
    }
}
