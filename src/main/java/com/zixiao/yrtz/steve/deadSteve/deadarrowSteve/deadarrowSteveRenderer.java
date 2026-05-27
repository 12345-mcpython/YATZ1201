package com.zixiao.yrtz.steve.deadSteve.deadarrowSteve;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BowItem;

public class deadarrowSteveRenderer extends MobRenderer<
        deadarrowSteveEntity,
        HumanoidModel<deadarrowSteveEntity>> {

    public deadarrowSteveRenderer(EntityRendererProvider.Context context) {

        super(
                context,
                new HumanoidModel<>(context.bakeLayer(ModelLayers.ZOMBIE)),
                0.5F
        );
        this.addLayer(new HumanoidArmorLayer<>(
                this,
                new HumanoidModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new HumanoidModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)),
                context.getModelManager()
        ));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(deadarrowSteveEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(
                "yrtz",
                "textures/entity/steve/deadsteve.png"
        );
    }

    @Override
    public void render(deadarrowSteveEntity entity,
                       float entityYaw,
                       float partialTicks,
                       PoseStack poseStack,
                       MultiBufferSource buffer,
                       int packedLight) {
        HumanoidModel<deadarrowSteveEntity> model = this.getModel();
        model.rightArmPose = HumanoidModel.ArmPose.EMPTY;
        model.leftArmPose = HumanoidModel.ArmPose.EMPTY;
        if (entity.isUsingItem()
                && entity.getUseItem().getItem() instanceof BowItem) {
            if (entity.getUsedItemHand() == InteractionHand.MAIN_HAND) {
                model.rightArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
            } else {
                model.leftArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
            }
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}