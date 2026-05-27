package com.zixiao.yrtz.mixin.mobdonotAttackSteve;

import com.zixiao.yrtz.reegister.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IronGolem.class)
public class IronGolemMixin {

    @Inject(method = "canAttackType", at = @At("HEAD"), cancellable = true)
    private void canAttackType(EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
        if (type == ModEntities.XMAS_STEVE.get()
                || type == ModEntities.ENDER_SUMMONER_STEVE.get()) {
            cir.setReturnValue(false);
        }

    }
}