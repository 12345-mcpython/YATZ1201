package com.zixiao.yrtz.mixin.doctorSteveSpawn;

import com.mojang.datafixers.util.Either;
import com.zixiao.yrtz.register.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;

@Mixin(SinglePoolElement.class)
public class SinglePoolElementMixin {

    @Unique
    private static final Set<BlockPos> SPAWNED_STRUCTURES = new HashSet<>();

    @Shadow
    @Final
    protected Either<ResourceLocation, StructureTemplate> template;

    @Inject(method = "place", at = @At("RETURN"))
    private void onPlace(
            StructureTemplateManager templateManager,
            WorldGenLevel level,
            net.minecraft.world.level.StructureManager structureManager,
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
        if (!path.contains("temple")) return;
        if (SPAWNED_STRUCTURES.contains(origin)) return;
        SPAWNED_STRUCTURES.add(origin);
        BlockPos spawnPos = null;
        int searchRadius = 5;
        for (int x = -searchRadius; x <= searchRadius && spawnPos == null; x++) {
            for (int y = -1; y <= 1 && spawnPos == null; y++) {
                for (int z = -searchRadius; z <= searchRadius && spawnPos == null; z++) {
                    BlockPos pos = origin.offset(x, y, z);
                    if (level.getBlockState(pos).getBlock() == Blocks.BREWING_STAND) {
                        spawnPos = pos.above(); // 实体生成在酿造台上方一格
                    }
                }
            }
        }
        if (spawnPos == null) {
            spawnPos = origin.offset(3, 1, 3);
        }

        Mob mob = ModEntities.DOCTOR_STEVE.get().create(level.getLevel());
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