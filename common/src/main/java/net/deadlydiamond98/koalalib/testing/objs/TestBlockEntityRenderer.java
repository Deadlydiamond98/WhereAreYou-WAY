package net.deadlydiamond98.koalalib.testing.objs;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.deadlydiamond98.koalalib.KoalaLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.joml.Matrix3dStack;

public class TestBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {

    public TestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(BlockEntity var1, float var2, PoseStack var3, MultiBufferSource var4, int var5, int var6) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        var3.pushPose();
        var3.translate(0.5, 0.6, 0.5);

        var3.mulPose(Axis.XP.rotationDegrees(180));

        var3.mulPose(Axis.ZP.rotationDegrees(-45));

        renderer.renderStatic(Items.WHEAT.getDefaultInstance(), ItemDisplayContext.FIXED, getLightLevel(var1.getLevel(), var1.getBlockPos()),
                OverlayTexture.NO_OVERLAY, var3, var4, var1.getLevel(), 1);

        var3.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
