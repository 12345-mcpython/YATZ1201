package com.zixiao.yrtz.mixin.playerTransVillager;

import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerKillMixin extends LivingEntity {

    protected PlayerKillMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Inject(
            method = "killedEntity",
            at = @At("RETURN"),
            cancellable = true
    )
    private void onKilledEntity(ServerLevel level, LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if (level.getDifficulty() != Difficulty.NORMAL &&
                level.getDifficulty() != Difficulty.HARD) {
            ((Player)(Object)this).giveExperiencePoints(5);
            return;
        }
        if (!(target instanceof Villager villager)) {
            return;
        }
        if (level.getDifficulty() != Difficulty.HARD &&
                this.getRandom().nextBoolean()) {
            ((Player)(Object)this).giveExperiencePoints(5);
            return;
        }

        ZombieVillager zombieVillager =
                villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);

        if (zombieVillager != null) {

            zombieVillager.finalizeSpawn(
                    level,
                    level.getCurrentDifficultyAt(zombieVillager.blockPosition()),
                    MobSpawnType.CONVERSION,
                    new Zombie.ZombieGroupData(false, true),
                    null
            );

            zombieVillager.setVillagerData(villager.getVillagerData());
            zombieVillager.setGossips(
                    villager.getGossips().store(NbtOps.INSTANCE)
            );

            zombieVillager.setTradeOffers(
                    villager.getOffers().createTag()
            );

            zombieVillager.setVillagerXp(
                    villager.getVillagerXp()
            );

            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(
                    target,
                    zombieVillager
            );

            ((Player)(Object)this).getFoodData().eat(5, 0.0F);
            if (!this.isSilent()) {
                level.levelEvent(
                        null,
                        1026,
                        this.blockPosition(),
                        0
                );
            }
            cir.setReturnValue(false);
        }
    }
}
