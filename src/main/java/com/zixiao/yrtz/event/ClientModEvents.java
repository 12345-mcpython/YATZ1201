package com.zixiao.yrtz.event;

import com.zixiao.yrtz.register.ModEntities;
import com.zixiao.yrtz.steve.spSteve.xmasSteve.XmasSteveRenderer;
import com.zixiao.yrtz.steve.spSteve.doctorSteve.DoctorSteveRenderer;
import com.zixiao.yrtz.steve.spSteve.endersummonerSteve.EnderSummonerSteveRenderer;
import com.zixiao.yrtz.steve.spSteve.fireSteve.FireSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadsummonerSteve.deadsummonedSteve.DeadSummonedSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadsummonerSteve.DeadSummonerSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.summonerSteve.summonedSteve.SummonedSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.summonerSteve.SummonerSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadarrowSteve.DeadArrowSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.arrowSteve.ArrowSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadninjaSteve.DeadNinjaSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.ninjaSteve.NinjaSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.diamondSteve.DiamondSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deaddiamondSteve.DeadDiamondSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.ironSteve.IronSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadironSteve.DeadIronSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.minerSteve.MinerSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadminerSteve.DeadMinerSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadfarmerSteve.DeadFarmerSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.farmerSteve.FarmerSteveRenderer;
import com.zixiao.yrtz.YRTZMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YRTZMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(
                ModEntities.FARMER_STEVE.get(),
                FarmerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_FARMER_STEVE.get(),
                DeadFarmerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.MINER_STEVE.get(),
                MinerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_MINER_STEVE.get(),
                DeadMinerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.IRON_STEVE.get(),
                IronSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DIAMOND_STEVE.get(),
                DiamondSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_IRON_STEVE.get(),
                DeadIronSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_DIAMOND_STEVE.get(),
                DeadDiamondSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.RENZHE_STEVE.get(),
                NinjaSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_RENZHE_STEVE.get(),
                DeadNinjaSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.ARROW_STEVE.get(),
                ArrowSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_ARROW_STEVE.get(),
                DeadArrowSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.SUMMONER_STEVE.get(),
                SummonerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.SUMMONED_STEVE.get(),
                SummonedSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_SUMMONER_STEVE.get(),
                DeadSummonerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_SUMMONED_STEVE.get(),
                DeadSummonedSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.FIRE_STEVE.get(),
                FireSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.ENDER_SUMMONER_STEVE.get(),
                EnderSummonerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DOCTOR_STEVE.get(),
                DoctorSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.XMAS_STEVE.get(),
                XmasSteveRenderer::new
        );

    }
}
