package com.zixiao.yrtz.mixin.sleepToNight;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(Player.class)
public class PlayerMixin2 {

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/event/ForgeEventFactory;fireSleepingTimeCheck(Lnet/minecraft/world/entity/player/Player;Ljava/util/Optional;)Z"
            )
    )
    private boolean flipSleepCheck(Player player, Optional<BlockPos> sleepingLocation) {

        return !ForgeEventFactory.fireSleepingTimeCheck(player, sleepingLocation);
    }
}
