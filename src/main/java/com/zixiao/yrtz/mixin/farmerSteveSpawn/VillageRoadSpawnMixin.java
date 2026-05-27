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

@Mixin(net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement.class)
public class VillageRoadSpawnMixin {
    private static final Set<String> SPAWNED_POSITIONS = new HashSet<>();

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
        if (!path.contains("road") && !path.contains("streets")) return;
        if (random.nextInt(30) != 0) return;

        BlockPos spawnPos = null;
        int topY = boundingBox.maxY();
        int exclusionRadius = 1;

        long worldSeed = level.getSeed(); // 这里直接用 level.getSeed()
        outer:
        for (int y = topY; y >= boundingBox.minY(); y--) {
            for (int x = boundingBox.minX(); x <= boundingBox.maxX(); x++) {
                for (int z = boundingBox.minZ(); z <= boundingBox.maxZ(); z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    var state = level.getBlockState(pos);

                    if (state.isSolidRender(level, pos) &&
                            (state.getBlock() == Blocks.DIRT ||
                                    state.getBlock() == Blocks.GRASS_BLOCK ||
                                    state.getBlock() == Blocks.DIRT_PATH)) {

                        boolean canSpawn = true;
                        for (int dx = -exclusionRadius; dx <= exclusionRadius; dx++) {
                            for (int dz = -exclusionRadius; dz <= exclusionRadius; dz++) {
                                BlockPos checkPos = pos.offset(dx, 0, dz);
                                String key = worldSeed + "_" + checkPos.asLong();
                                if (SPAWNED_POSITIONS.contains(key)) {
                                    canSpawn = false;
                                    break;
                                }
                            }
                            if (!canSpawn) break;
                        }

                        if (!canSpawn) continue;

                        BlockPos candidatePos = pos.above();
                        if (!level.getBlockState(candidatePos).isAir()) continue;

                        spawnPos = candidatePos;

                        for (int dx = -exclusionRadius; dx <= exclusionRadius; dx++) {
                            for (int dz = -exclusionRadius; dz <= exclusionRadius; dz++) {
                                BlockPos block = pos.offset(dx, 0, dz);
                                String key = worldSeed + "_" + block.asLong();
                                SPAWNED_POSITIONS.add(key);
                            }
                        }

                        break outer;
                    }
                }
            }
        }

        if (spawnPos == null) return;

        Mob mob = ModEntities.FARMER_STEVE.get().create(level.getLevel());
        if (mob != null) {
            var entityBox = mob.getBoundingBox().move(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
            if (level.getEntities(null, entityBox).isEmpty()) {
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
}