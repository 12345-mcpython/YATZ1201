package com.zixiao.yrtz.mixin.playerBurn;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerSunBurnMixin {
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void injectSunBurn(CallbackInfo ci) {
        Player self = (Player) (Object) this;
        if (self.isAlive()) {
            boolean flag = this.yATZ1201$isSunBurnTick(self);
            if (flag) {
                ItemStack itemstack = self.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + self.getRandom().nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            self.broadcastBreakEvent(EquipmentSlot.HEAD);
                            self.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }
                    flag = false;
                }
                if (flag) {
                    self.setSecondsOnFire(8);
                }
            }
        }
    }

    @Unique
    private boolean yATZ1201$isSunBurnTick(Player self) {
        if (self.level().isDay() && !self.level().isClientSide) {
            float f = self.getLightLevelDependentMagicValue();
            BlockPos blockpos = BlockPos.containing(self.getX(), self.getEyeY(), self.getZ());
            boolean flag = self.isInWaterRainOrBubble() || self.isInPowderSnow || self.wasInPowderSnow;
            return f > 0.5F && self.getRandom().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !flag && self.level().canSeeSky(blockpos);
        }

        return false;
    }
}
