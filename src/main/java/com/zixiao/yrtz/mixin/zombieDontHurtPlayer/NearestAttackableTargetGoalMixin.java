package com.zixiao.yrtz.mixin.zombieDontHurtPlayer;


import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NearestAttackableTargetGoal.class)
public abstract class NearestAttackableTargetGoalMixin<T extends LivingEntity> {

    @Shadow
    protected Class<T> targetType;

    @Shadow
    protected LivingEntity target;

    @Inject(method = "findTarget", at = @At("HEAD"), cancellable = true)
    private void injected(CallbackInfo ci) {

        var mob = ((TargetGoalAccessor)this).getMob();

        if (mob instanceof Drowned
                && (this.targetType == Player.class
                || this.targetType == ServerPlayer.class)) {

            this.target = null;
            ci.cancel();
        }
    }
}
