package com.zixiao.yrtz.mixin.farmerSteveSpawn;

import net.minecraft.world.level.block.Rotation;
import com.mojang.datafixers.util.Either;
import com.zixiao.yrtz.reegister.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;

@Mixin(SinglePoolElement.class)
public class VillageFarmSpawnMixin {

    private static final Set<BlockPos> SPAWNED_STRUCTURES = new HashSet<>();

    @Shadow
    @Final
    protected Either<ResourceLocation, StructureTemplate> template;

    @Inject(method = "place", at = @At("RETURN"))
    private void onPlace(
            StructureTemplateManager templateManager,
            WorldGenLevel level,
            StructureManager structureManager,
            ChunkGenerator chunkGenerator,
            BlockPos origin,
            BlockPos p_227307_,
            Rotation rotation,
            BoundingBox boundingBox,
            RandomSource random,
            boolean p_227311_,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue()) return;

        ResourceLocation rl = this.template.left().orElse(null);
        if (rl == null) return;

        String path = rl.getPath();
        if (!path.contains("farm") && !path.contains("village/fields")) return;
        if (SPAWNED_STRUCTURES.contains(origin)) return;
        SPAWNED_STRUCTURES.add(origin);

        BlockPos spawnPos = null;
        int searchRadius = 10; // 农田房屋通常不大
        for (int x = -searchRadius; x <= searchRadius && spawnPos == null; x++) {
            for (int y = -1; y <= 3 && spawnPos == null; y++) {
                for (int z = -searchRadius; z <= searchRadius && spawnPos == null; z++) {
                    BlockPos pos = origin.offset(x, y, z);
                    if (level.getBlockState(pos).getBlock() == Blocks.COMPOSTER) {
                        spawnPos = pos.above();
                    }
                }
            }
        }
        if (spawnPos == null) {
            for (int x = -searchRadius; x <= searchRadius && spawnPos == null; x++) {
                for (int y = -1; y <= 1 && spawnPos == null; y++) {
                    for (int z = -searchRadius; z <= searchRadius && spawnPos == null; z++) {
                        BlockPos pos = origin.offset(x, y, z);
                        if (level.getBlockState(pos).getBlock() == Blocks.FARMLAND) {
                            spawnPos = pos.above();
                        }
                    }
                }
            }
        }
        if (spawnPos == null) {
            int topY = boundingBox.maxY();
            spawnPos = new BlockPos(
                    origin.getX() + (boundingBox.getXSpan() / 2),
                    topY + 1,
                    origin.getZ() + (boundingBox.getZSpan() / 2)
            );
        }
        Mob mob = ModEntities.FARMER_STEVE.get().create(level.getLevel());
        if (mob != null) {
            mob.moveTo(
                    spawnPos.getX() + 0.5,
                    spawnPos.getY(),
                    spawnPos.getZ() + 0.5,
                    random.nextFloat() * 360F,
                    0
            );
            mob.finalizeSpawn(
                    level,
                    level.getCurrentDifficultyAt(spawnPos),
                    MobSpawnType.STRUCTURE,
                    null,
                    null
            );
            level.addFreshEntity(mob);
        }
    }
}