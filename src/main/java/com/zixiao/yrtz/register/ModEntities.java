package com.zixiao.yrtz.register;

import com.zixiao.yrtz.steve.deadSteve.deadsummonerSteve.deadsummonedSteve.DeadSummonedSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadsummonerSteve.DeadSummonerSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.arrowSteve.ArrowSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadarrowSteve.DeadArrowSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deaddiamondSteve.DeadDiamondSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadfarmerSteve.DeadFarmerSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadironSteve.DeadIronSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadminerSteve.DeadMinerSteveEntity;
import com.zixiao.yrtz.steve.deadSteve.deadninjaSteve.DeadNinjaSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.diamondSteve.DiamondSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.farmerSteve.FarmerSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.ironSteve.IronSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.minerSteve.MinerSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.ninjaSteve.NinjaSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.summonerSteve.summonedSteve.SummonedSteveEntity;
import com.zixiao.yrtz.steve.normalSteve.summonerSteve.SummonerSteveEntity;
import com.zixiao.yrtz.steve.spSteve.doctorSteve.DoctorSteveEntity;
import com.zixiao.yrtz.steve.spSteve.endersummonerSteve.EnderSummonerSteveEntity;
import com.zixiao.yrtz.steve.spSteve.fireSteve.FireSteveEntity;
import com.zixiao.yrtz.steve.spSteve.xmasSteve.XmasSteveEntity;
import com.zixiao.yrtz.YRTZMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, YRTZMod.MODID);

    public static final RegistryObject<EntityType<FarmerSteveEntity>> FARMER_STEVE =
            ENTITY_TYPES.register("farmer_steve",
                    () -> EntityType.Builder.of(FarmerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("farmer_steve"));

    public static final RegistryObject<EntityType<DeadFarmerSteveEntity>> DEAD_FARMER_STEVE =
            ENTITY_TYPES.register("dead_farmer_steve",
                    () -> EntityType.Builder.of(DeadFarmerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_farmer_steve"));

    public static final RegistryObject<EntityType<MinerSteveEntity>> MINER_STEVE =
            ENTITY_TYPES.register("miner_steve",
                    () -> EntityType.Builder.of(MinerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("miner_steve"));

    public static final RegistryObject<EntityType<DeadMinerSteveEntity>> DEAD_MINER_STEVE =
            ENTITY_TYPES.register("dead_miner_steve",
                    () -> EntityType.Builder.of(DeadMinerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_miner_steve"));

    public static final RegistryObject<EntityType<IronSteveEntity>> IRON_STEVE =
            ENTITY_TYPES.register("iron_steve",
                    () -> EntityType.Builder.of(IronSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("iron_steve"));

    public static final RegistryObject<EntityType<DiamondSteveEntity>> DIAMOND_STEVE =
            ENTITY_TYPES.register("diamond_steve",
                    () -> EntityType.Builder.of(DiamondSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("diamond_steve"));

    public static final RegistryObject<EntityType<DeadIronSteveEntity>> DEAD_IRON_STEVE =
            ENTITY_TYPES.register("dead_iron_steve",
                    () -> EntityType.Builder.of(DeadIronSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_iron_steve"));

    public static final RegistryObject<EntityType<DeadDiamondSteveEntity>> DEAD_DIAMOND_STEVE =
            ENTITY_TYPES.register("dead_diamond_steve",
                    () -> EntityType.Builder.of(DeadDiamondSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_diamond_steve"));

    public static final RegistryObject<EntityType<NinjaSteveEntity>> RENZHE_STEVE =
            ENTITY_TYPES.register("renzhe_steve",
                    () -> EntityType.Builder.of(NinjaSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("renzhe_steve"));

    public static final RegistryObject<EntityType<DeadNinjaSteveEntity>> DEAD_RENZHE_STEVE =
            ENTITY_TYPES.register("dead_renzhe_steve",
                    () -> EntityType.Builder.of(DeadNinjaSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_renzhe_steve"));

    public static final RegistryObject<EntityType<ArrowSteveEntity>> ARROW_STEVE =
            ENTITY_TYPES.register("arrow_steve",
                    () -> EntityType.Builder.of(ArrowSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("arrow_steve"));

    public static final RegistryObject<EntityType<DeadArrowSteveEntity>> DEAD_ARROW_STEVE =
            ENTITY_TYPES.register("dead_arrow_steve",
                    () -> EntityType.Builder.of(DeadArrowSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_arrow_steve"));

    public static final RegistryObject<EntityType<SummonerSteveEntity>> SUMMONER_STEVE =
            ENTITY_TYPES.register("summoner_steve",
                    () -> EntityType.Builder.of(SummonerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("summoner_steve"));

    public static final RegistryObject<EntityType<SummonedSteveEntity>> SUMMONED_STEVE =
            ENTITY_TYPES.register("summoned_steve",
                    () -> EntityType.Builder.of(SummonedSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("summoned_steve"));

    public static final RegistryObject<EntityType<DeadSummonerSteveEntity>> DEAD_SUMMONER_STEVE =
            ENTITY_TYPES.register("dead_summoner_steve",
                    () -> EntityType.Builder.of(DeadSummonerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_summoner_steve"));

    public static final RegistryObject<EntityType<DeadSummonedSteveEntity>> DEAD_SUMMONED_STEVE =
            ENTITY_TYPES.register("dead_summoned_steve",
                    () -> EntityType.Builder.of(DeadSummonedSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("dead_summoned_steve"));

    public static final RegistryObject<EntityType<FireSteveEntity>> FIRE_STEVE =
            ENTITY_TYPES.register("fire_steve",
                    () -> EntityType.Builder.of(FireSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("fire_steve"));

    public static final RegistryObject<EntityType<EnderSummonerSteveEntity>> ENDER_SUMMONER_STEVE =
            ENTITY_TYPES.register("ender_summoner_steve",
                    () -> EntityType.Builder.of(EnderSummonerSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("ender_summoner_steve"));

    public static final RegistryObject<EntityType<DoctorSteveEntity>> DOCTOR_STEVE =
            ENTITY_TYPES.register("doctor_steve",
                    () -> EntityType.Builder.of(DoctorSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("doctor_steve"));

    public static final RegistryObject<EntityType<XmasSteveEntity>> XMAS_STEVE =
            ENTITY_TYPES.register("xmas_steve",
                    () -> EntityType.Builder.of(XmasSteveEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build("xmas_steve"));
}
