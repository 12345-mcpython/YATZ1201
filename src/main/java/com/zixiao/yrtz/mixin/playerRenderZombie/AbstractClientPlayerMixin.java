package com.zixiao.yrtz.mixin.playerRenderZombie;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin {

    @Inject(
        method = "getSkinTextureLocation",
        at = @At("HEAD"),
        cancellable = true
    )
    private void yrtz$replaceSkin(CallbackInfoReturnable<ResourceLocation> cir) {
        cir.setReturnValue(
                new ResourceLocation(
                        "yrtz",
                        "textures/entity/zombie/player_zombie.png"
                )
        );
    }
}