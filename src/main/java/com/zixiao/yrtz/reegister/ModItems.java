package com.zixiao.yrtz.reegister;

import com.zixiao.yrtz.yrtz;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(
                    ForgeRegistries.ITEMS,
                    yrtz.MODID
            );
    public static final RegistryObject<Item> IAM_BAD_DISC =
            ITEMS.register(
                    "billie_jean_disc",
                    () -> new RecordItem(6,ModSounds.IAM_BAD,
                            new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),
                            3240
                    )
            );
    public static final RegistryObject<Item> FARMER_STEVE_SPAWN_EGG =
            ITEMS.register("farmer_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.FARMER_STEVE,
                            0x6B4F2A,
                            0xF2D16B,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> DEAD_FARMER_STEVE_SPAWN_EGG =
            ITEMS.register("dead_farmer_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DEAD_FARMER_STEVE,
                            0x2B2B2B,
                            0x6B6B6B,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> MINER_STEVE_SPAWN_EGG =
            ITEMS.register("miner_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.MINER_STEVE,
                            0x4B4B4B,
                            0xAAAAAA,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> DEAD_MINER_STEVE_SPAWN_EGG =
            ITEMS.register("dead_miner_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DEAD_MINER_STEVE,
                            0x1A1A1A,
                            0x555555,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> IRON_STEVE_SPAWN_EGG =
            ITEMS.register("iron_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.IRON_STEVE,
                            0xC0C0C0,
                            0x808080,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> DEAD_IRON_STEVE_SPAWN_EGG =
            ITEMS.register("dead_iron_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DEAD_IRON_STEVE,
                            0x3A3A3A,
                            0xA0A0A0,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> DIAMOND_STEVE_SPAWN_EGG =
            ITEMS.register("diamond_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DIAMOND_STEVE,
                            0x00FFFF,
                            0x007777,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> DEAD_DIAMOND_STEVE_SPAWN_EGG =
            ITEMS.register("dead_diamond_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DEAD_DIAMOND_STEVE,
                            0x003333,
                            0x00AAAA,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> RENZHE_STEVE_SPAWN_EGG =
            ITEMS.register("renzhe_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.RENZHE_STEVE,
                            0x8B0000,
                            0xFFD700,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> DEAD_RENZHE_STEVE_SPAWN_EGG =
            ITEMS.register("dead_renzhe_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DEAD_RENZHE_STEVE,
                            0x2F0000,
                            0x666666,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> ARROW_STEVE_SPAWN_EGG =
            ITEMS.register("arrow_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.ARROW_STEVE,
                            0x8B5A2B,
                            0xC2A46B,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> DEAD_ARROW_STEVE_SPAWN_EGG =
            ITEMS.register("dead_arrow_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DEAD_ARROW_STEVE,
                            0x3B2A1A,
                            0x777777,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> SUMMONER_STEVE_SPAWN_EGG =
            ITEMS.register("summoner_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.SUMMONER_STEVE,
                            0xAA00FF,
                            0x440066,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> SUMMONED_STEVE_SPAWN_EGG =
            ITEMS.register("summoned_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.SUMMONED_STEVE,
                            0x5500AA,
                            0x220044,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> DEAD_SUMMONER_STEVE_SPAWN_EGG =
            ITEMS.register("dead_summoner_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DEAD_SUMMONER_STEVE,
                            0x1A001A,
                            0x666666,
                            new Item.Properties()
                    ));

    public static final RegistryObject<Item> DEAD_SUMMONED_STEVE_SPAWN_EGG =
            ITEMS.register("dead_summoned_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DEAD_SUMMONED_STEVE,
                            0x0A0A0A,
                            0x444444,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> FIRE_STEVE_SPAWN_EGG =
            ITEMS.register("fire_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.FIRE_STEVE,
                            0xFF4500,
                            0x8B0000,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> ENDER_SUMMONER_STEVE_SPAWN_EGG =
            ITEMS.register("ender_summoner_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.ENDER_SUMMONER_STEVE,
                            0x2E0854,
                            0x7F00FF,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> DOCTOR_STEVE_SPAWN_EGG =
            ITEMS.register("doctor_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.DOCTOR_STEVE,
                            0xFFFFFF,
                            0x00AA00,
                            new Item.Properties()
                    ));
    public static final RegistryObject<Item> XMAS_STEVE_SPAWN_EGG =
            ITEMS.register("xmas_steve_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.XMAS_STEVE,
                            0xFF0000,
                            0x00FF00,
                            new Item.Properties()
                    ));
}