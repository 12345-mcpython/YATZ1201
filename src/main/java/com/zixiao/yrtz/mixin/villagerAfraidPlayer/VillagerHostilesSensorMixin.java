package com.zixiao.yrtz.mixin.villagerAfraidPlayer;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerHostilesSensor.class)
public class VillagerHostilesSensorMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"
            )
    )
    private static ImmutableMap<EntityType<?>, Float> modifyHostileDistances(ImmutableMap.Builder<EntityType<?>, Float> builder) {
        builder.put(EntityType.PLAYER, 8.0F);
        return builder.build();
    }
}
