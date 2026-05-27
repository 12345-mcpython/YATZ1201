package com.zixiao.yrtz.reegister;

import com.zixiao.yrtz.steve.deadSteve.deadsummonerSteve.deadsummonedSteve.deadsummonedSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadsummonerSteve.deadsummonerSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.arrowSteve.arrowSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadarrowSteve.deadarrowSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deaddiamondSteve.deaddiamondSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadfarmerSteve.deadFarmerSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadironSteve.deadironSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadminerSteve.deadminerSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadrenzheSteve.deadrenzheSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.diamondSteve.diamondSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.farmerSteve.FarmerSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.ironSteve.ironSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.minerSteve.minerSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.renzheSteve.renzheSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.summonerSteve.summonedSteve.summonedSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.summonerSteve.summonerSteveEntity;
import com.zixiao.yrtz.steve.spSteve.doctorSteve.doctorSteveEntity;
import com.zixiao.yrtz.steve.spSteve.endersummonerSteve.endersummonerSteveEntity;
import com.zixiao.yrtz.steve.spSteve.fireSteve.fireSteveEntity;
import com.zixiao.yrtz.steve.spSteve.xmasSteve.xmasSteveEntity;
import com.zixiao.yrtz.yrtz;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, yrtz.MODID);

    public static final RegistryObject<EntityType<FarmerSteveEntity>> FARMER_STEVE =
            ENTITY_TYPES.register("farmer_steve",
                    () -> EntityType.Builder.of(FarmerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("farmer_steve"));

    public static final RegistryObject<EntityType<deadFarmerSteveEntity>> DEAD_FARMER_STEVE =
            ENTITY_TYPES.register("dead_farmer_steve",
                    () -> EntityType.Builder.of(deadFarmerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_farmer_steve"));

    public static final RegistryObject<EntityType<minerSteveEntity>> MINER_STEVE =
            ENTITY_TYPES.register("miner_steve",
                    () -> EntityType.Builder.of(minerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("miner_steve"));

    public static final RegistryObject<EntityType<deadminerSteveEntity>> DEAD_MINER_STEVE =
            ENTITY_TYPES.register("dead_miner_steve",
                    () -> EntityType.Builder.of(deadminerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_miner_steve"));

    public static final RegistryObject<EntityType<ironSteveEntity>> IRON_STEVE =
            ENTITY_TYPES.register("iron_steve",
                    () -> EntityType.Builder.of(ironSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("iron_steve"));

    public static final RegistryObject<EntityType<diamondSteveEntity>> DIAMOND_STEVE =
            ENTITY_TYPES.register("diamond_steve",
                    () -> EntityType.Builder.of(diamondSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("diamond_steve"));

    public static final RegistryObject<EntityType<deadironSteveEntity>> DEAD_IRON_STEVE =
            ENTITY_TYPES.register("dead_iron_steve",
                    () -> EntityType.Builder.of(deadironSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_iron_steve"));

    public static final RegistryObject<EntityType<deaddiamondSteveEntity>> DEAD_DIAMOND_STEVE =
            ENTITY_TYPES.register("dead_diamond_steve",
                    () -> EntityType.Builder.of(deaddiamondSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_diamond_steve"));

    public static final RegistryObject<EntityType<renzheSteveEntity>> RENZHE_STEVE =
            ENTITY_TYPES.register("renzhe_steve",
                    () -> EntityType.Builder.of(renzheSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("renzhe_steve"));

    public static final RegistryObject<EntityType<deadrenzheSteveEntity>> DEAD_RENZHE_STEVE =
            ENTITY_TYPES.register("dead_renzhe_steve",
                    () -> EntityType.Builder.of(deadrenzheSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_renzhe_steve"));

    public static final RegistryObject<EntityType<arrowSteveEntity>> ARROW_STEVE =
            ENTITY_TYPES.register("arrow_steve",
                    () -> EntityType.Builder.of(arrowSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("arrow_steve"));

    public static final RegistryObject<EntityType<deadarrowSteveEntity>> DEAD_ARROW_STEVE =
            ENTITY_TYPES.register("dead_arrow_steve",
                    () -> EntityType.Builder.of(deadarrowSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_arrow_steve"));

    public static final RegistryObject<EntityType<summonerSteveEntity>> SUMMONER_STEVE =
            ENTITY_TYPES.register("summoner_steve",
                    () -> EntityType.Builder.of(summonerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("summoner_steve"));

    public static final RegistryObject<EntityType<summonedSteveEntity>> SUMMONED_STEVE =
            ENTITY_TYPES.register("summoned_steve",
                    () -> EntityType.Builder.of(summonedSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("summoned_steve"));

    public static final RegistryObject<EntityType<deadsummonerSteveEntity>> DEAD_SUMMONER_STEVE =
            ENTITY_TYPES.register("dead_summoner_steve",
                    () -> EntityType.Builder.of(deadsummonerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_summoner_steve"));

    public static final RegistryObject<EntityType<deadsummonedSteveEntity>> DEAD_SUMMONED_STEVE =
            ENTITY_TYPES.register("dead_summoned_steve",
                    () -> EntityType.Builder.of(deadsummonedSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_summoned_steve"));

    public static final RegistryObject<EntityType<fireSteveEntity>> FIRE_STEVE =
            ENTITY_TYPES.register("fire_steve",
                    () -> EntityType.Builder.of(fireSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("fire_steve"));

    public static final RegistryObject<EntityType<endersummonerSteveEntity>> ENDER_SUMMONER_STEVE =
            ENTITY_TYPES.register("ender_summoner_steve",
                    () -> EntityType.Builder.of(endersummonerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("ender_summoner_steve"));

    public static final RegistryObject<EntityType<doctorSteveEntity>> DOCTOR_STEVE =
            ENTITY_TYPES.register("doctor_steve",
                    () -> EntityType.Builder.of(doctorSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("doctor_steve"));

    public static final RegistryObject<EntityType<xmasSteveEntity>> XMAS_STEVE =
            ENTITY_TYPES.register("xmas_steve",
                    () -> EntityType.Builder.of(xmasSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("xmas_steve"));
}
