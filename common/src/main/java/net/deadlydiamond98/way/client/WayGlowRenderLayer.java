package net.deadlydiamond98.way.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Player;

public class WayGlowRenderLayer<M extends EntityModel<Player>> extends RenderLayer<Player, M> {

    public WayGlowRenderLayer(RenderLayerParent<Player, M> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int var3, Player player, float var5, float var6, float var7, float var8, float var9, float var10) {
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.outline(getTextureLocation(player)));
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
