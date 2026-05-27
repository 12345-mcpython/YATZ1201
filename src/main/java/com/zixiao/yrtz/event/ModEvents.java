package com.zixiao.yrtz.event;

import com.zixiao.yrtz.reegister.ModEntities;
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
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(ModEntities.FARMER_STEVE.get(),
                FarmerSteveEntity.createAttributes().build());

        event.put(ModEntities.DEAD_FARMER_STEVE.get(),
                deadFarmerSteveEntity.createAttributes().build());

        event.put(ModEntities.MINER_STEVE.get(),
                minerSteveEntity.createAttributes().build());

        event.put(ModEntities.DEAD_MINER_STEVE.get(),
                deadminerSteveEntity.createAttributes().build());

        event.put(ModEntities.IRON_STEVE.get(),
                ironSteveEntity.createAttributes().build());

        event.put(ModEntities.DIAMOND_STEVE.get(),
                diamondSteveEntity.createAttributes().build());

        event.put(ModEntities.DEAD_IRON_STEVE.get(),
                deadironSteveEntity.createAttributes().build());

        event.put(ModEntities.DEAD_DIAMOND_STEVE.get(),
                deaddiamondSteveEntity.createAttributes().build());

        event.put(ModEntities.RENZHE_STEVE.get(),
                renzheSteveEntity.createAttributes().build());

        event.put(ModEntities.DEAD_RENZHE_STEVE.get(),
                deadrenzheSteveEntity.createAttributes().build());

        event.put(ModEntities.ARROW_STEVE.get(),
                arrowSteveEntity.createAttributes().build());

        event.put(ModEntities.DEAD_ARROW_STEVE.get(),
                deadarrowSteveEntity.createAttributes().build());

        event.put(ModEntities.SUMMONER_STEVE.get(),
                summonerSteveEntity.createAttributes().build());

        event.put(ModEntities.SUMMONED_STEVE.get(),
                summonedSteveEntity.createAttributes().build());

        event.put(ModEntities.DEAD_SUMMONER_STEVE.get(),
                deadsummonerSteveEntity.createAttributes().build());

        event.put(ModEntities.DEAD_SUMMONED_STEVE.get(),
                deadsummonedSteveEntity.createAttributes().build());

        event.put(ModEntities.FIRE_STEVE.get(),
                fireSteveEntity.createAttributes().build());

        event.put(ModEntities.ENDER_SUMMONER_STEVE.get(),
                endersummonerSteveEntity.createAttributes().build());

        event.put(ModEntities.DOCTOR_STEVE.get(),
                doctorSteveEntity.createAttributes().build());

        event.put(ModEntities.XMAS_STEVE.get(),
                xmasSteveEntity.createAttributes().build());

    }

    @SubscribeEvent
    public static void entitySpawn(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.ENDER_SUMMONER_STEVE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE
        );

        event.register(ModEntities.XMAS_STEVE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE
        );
    }
}
