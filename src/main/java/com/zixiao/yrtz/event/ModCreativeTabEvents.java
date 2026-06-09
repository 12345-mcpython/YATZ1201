package com.zixiao.yrtz.event;

import com.zixiao.yrtz.register.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "yrtz", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModCreativeTabEvents {

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {

        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {

            event.accept(ModItems.FARMER_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.MINER_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.IRON_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DIAMOND_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.RENZHE_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.ARROW_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.SUMMONER_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.SUMMONED_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.FIRE_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.ENDER_SUMMONER_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DOCTOR_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.XMAS_STEVE_SPAWN_EGG.get());

            event.accept(ModItems.DEAD_FARMER_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DEAD_MINER_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DEAD_IRON_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DEAD_DIAMOND_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DEAD_RENZHE_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DEAD_ARROW_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DEAD_SUMMONER_STEVE_SPAWN_EGG.get());
            event.accept(ModItems.DEAD_SUMMONED_STEVE_SPAWN_EGG.get());
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.IAM_BAD_DISC.get());
        }
    }
}