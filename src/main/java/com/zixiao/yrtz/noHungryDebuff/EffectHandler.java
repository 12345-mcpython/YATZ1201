package com.zixiao.yrtz.noHungryDebuff;

import com.zixiao.yrtz.yrtz;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = yrtz.MODID)
public class EffectHandler {

    @SubscribeEvent
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        if (event.getEntity() instanceof Player) {
            if (event.getEffectInstance().getEffect() == MobEffects.HUNGER) {
                event.setResult(Event.Result.DENY);
            }
        }
    }
}
