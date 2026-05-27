package com.zixiao.yrtz.mixin.playerRenderZombie;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin3 {

    @Unique
    private static final ResourceLocation ARM_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    "yrtz",
                    "textures/entity/zombie/player_zombie.png"
            );

    @Redirect(
            method = "renderHand",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/client/player/AbstractClientPlayer;getSkinTextureLocation()Lnet/minecraft/resources/ResourceLocation;"
            )
    )
    private ResourceLocation replaceArmTexture(
            AbstractClientPlayer player
    ) {
        return ARM_TEXTURE;
    }
}
