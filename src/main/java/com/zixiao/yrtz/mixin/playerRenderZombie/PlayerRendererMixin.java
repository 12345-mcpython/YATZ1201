package com.zixiao.yrtz.mixin.playerRenderZombie;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

    @Inject(
            method = "getTextureLocation(Lnet/minecraft/client/player/AbstractClientPlayer;)Lnet/minecraft/resources/ResourceLocation;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void zombieSkin(
            AbstractClientPlayer player,
            CallbackInfoReturnable<ResourceLocation> cir
    ) {

        cir.setReturnValue(
                new ResourceLocation("yrtz", "textures/entity/zombie/player_zombie.png")
        );
    }
}
