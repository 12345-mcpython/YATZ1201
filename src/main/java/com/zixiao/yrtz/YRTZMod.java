package com.zixiao.yrtz;

import com.mojang.logging.LogUtils;
import com.zixiao.yrtz.register.ModEntities;
import com.zixiao.yrtz.register.ModItems;
import com.zixiao.yrtz.register.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
@Mod(YRTZMod.MODID)
public class YRTZMod
{
    public static final String MODID = "yrtz";
    private static final Logger LOGGER = LogUtils.getLogger();

    public YRTZMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        ModEntities.ENTITY_TYPES.register(modEventBus);

        ModSounds.register(modEventBus);

        ModItems.ITEMS.register(modEventBus);

        LOGGER.warn("yrtz initialized");
    }
}
