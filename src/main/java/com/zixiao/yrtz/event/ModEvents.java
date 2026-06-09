package com.zixiao.yrtz.event;

import com.zixiao.yrtz.register.ModEntities;
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
        event.put(ModEntities.FARMER_STEVE.get(), FarmerSteveEntity.createAttributes().build());
        event.put(ModEntities.DEAD_FARMER_STEVE.get(), DeadFarmerSteveEntity.createAttributes().build());
        event.put(ModEntities.MINER_STEVE.get(), MinerSteveEntity.createAttributes().build());
        event.put(ModEntities.DEAD_MINER_STEVE.get(), DeadMinerSteveEntity.createAttributes().build());
        event.put(ModEntities.IRON_STEVE.get(), IronSteveEntity.createAttributes().build());
        event.put(ModEntities.DIAMOND_STEVE.get(), DiamondSteveEntity.createAttributes().build());
        event.put(ModEntities.DEAD_IRON_STEVE.get(), DeadIronSteveEntity.createAttributes().build());
        event.put(ModEntities.DEAD_DIAMOND_STEVE.get(), DeadDiamondSteveEntity.createAttributes().build());
        event.put(ModEntities.RENZHE_STEVE.get(), NinjaSteveEntity.createAttributes().build());
        event.put(ModEntities.DEAD_RENZHE_STEVE.get(), DeadNinjaSteveEntity.createAttributes().build());
        event.put(ModEntities.ARROW_STEVE.get(), ArrowSteveEntity.createAttributes().build());
        event.put(ModEntities.DEAD_ARROW_STEVE.get(), DeadArrowSteveEntity.createAttributes().build());
        event.put(ModEntities.SUMMONER_STEVE.get(), SummonerSteveEntity.createAttributes().build());
        event.put(ModEntities.SUMMONED_STEVE.get(), SummonedSteveEntity.createAttributes().build());
        event.put(ModEntities.DEAD_SUMMONER_STEVE.get(), DeadSummonerSteveEntity.createAttributes().build());
        event.put(ModEntities.DEAD_SUMMONED_STEVE.get(), DeadSummonedSteveEntity.createAttributes().build());
        event.put(ModEntities.FIRE_STEVE.get(), FireSteveEntity.createAttributes().build());
        event.put(ModEntities.ENDER_SUMMONER_STEVE.get(), EnderSummonerSteveEntity.createAttributes().build());
        event.put(ModEntities.DOCTOR_STEVE.get(), DoctorSteveEntity.createAttributes().build());
        event.put(ModEntities.XMAS_STEVE.get(), XmasSteveEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void entitySpawn(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.ENDER_SUMMONER_STEVE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.XMAS_STEVE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
