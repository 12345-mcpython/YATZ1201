package com.zixiao.yrtz.event;

import com.zixiao.yrtz.YRTZMod;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YRTZMod.MODID)
public class ServerModEvents {

    @SubscribeEvent
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        if (event.getEntity() instanceof Player) {
            if (event.getEffectInstance().getEffect() == MobEffects.HUNGER) {
                event.setResult(Event.Result.DENY);
            }
        }
    }
}
