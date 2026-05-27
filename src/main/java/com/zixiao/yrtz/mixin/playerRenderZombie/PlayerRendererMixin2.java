package com.zixiao.yrtz.mixin.playerRenderZombie;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin2 {

    @ModifyVariable(
            method = "<init>",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private static boolean forceSteveModel(boolean slim) {
        return false;
    }
}
