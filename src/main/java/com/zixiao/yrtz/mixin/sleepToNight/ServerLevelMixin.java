package com.zixiao.yrtz.mixin.sleepToNight;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;setDayTime(J)V"
            )
    )
    private void sleepToNight(ServerLevel level, long originalTime) {

        long time = level.getDayTime();
        long base = time - time % 24000L;
        long target = base + 13000L;
        if (time >= target) {
            target += 24000L;
        }

        level.setDayTime(
                ForgeEventFactory.onSleepFinished(
                        level,
                        target,
                        time
                )
        );
    }
}
