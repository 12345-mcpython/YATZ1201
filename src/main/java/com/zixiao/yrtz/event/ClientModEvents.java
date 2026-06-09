package com.zixiao.yrtz.event;

import com.zixiao.yrtz.register.ModEntities;
import com.zixiao.yrtz.steve.spSteve.xmasSteve.xmasSteveRenderer;
import com.zixiao.yrtz.steve.spSteve.doctorSteve.doctorSteveRenderer;
import com.zixiao.yrtz.steve.spSteve.endersummonerSteve.endersummonerSteveRenderer;
import com.zixiao.yrtz.steve.spSteve.fireSteve.fireSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadsummonerSteve.deadsummonedSteve.deadsummonedSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadsummonerSteve.deadsummonerSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.summonerSteve.summonedSteve.summonedSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.summonerSteve.summonerSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadarrowSteve.deadarrowSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.arrowSteve.arrowSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadrenzheSteve.deadrenzheSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.renzheSteve.renzheSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.diamondSteve.diamondSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deaddiamondSteve.deaddiamondSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.ironSteve.ironSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadironSteve.deadironSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.minerSteve.minerSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadminerSteve.deadminerSteveRenderer;
import com.zixiao.yrtz.steve.deadSteve.deadfarmerSteve.deadFarmerSteveRenderer;
import com.zixiao.yrtz.steve.normalSteve.farmerSteve.FarmerSteveRenderer;
import com.zixiao.yrtz.yrtz;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = yrtz.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(
                ModEntities.FARMER_STEVE.get(),
                FarmerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_FARMER_STEVE.get(),
                deadFarmerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.MINER_STEVE.get(),
                minerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_MINER_STEVE.get(),
                deadminerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.IRON_STEVE.get(),
                ironSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DIAMOND_STEVE.get(),
                diamondSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_IRON_STEVE.get(),
                deadironSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_DIAMOND_STEVE.get(),
                deaddiamondSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.RENZHE_STEVE.get(),
                renzheSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_RENZHE_STEVE.get(),
                deadrenzheSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.ARROW_STEVE.get(),
                arrowSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_ARROW_STEVE.get(),
                deadarrowSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.SUMMONER_STEVE.get(),
                summonerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.SUMMONED_STEVE.get(),
                summonedSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_SUMMONER_STEVE.get(),
                deadsummonerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DEAD_SUMMONED_STEVE.get(),
                deadsummonedSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.FIRE_STEVE.get(),
                fireSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.ENDER_SUMMONER_STEVE.get(),
                endersummonerSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.DOCTOR_STEVE.get(),
                doctorSteveRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.XMAS_STEVE.get(),
                xmasSteveRenderer::new
        );

    }
}
