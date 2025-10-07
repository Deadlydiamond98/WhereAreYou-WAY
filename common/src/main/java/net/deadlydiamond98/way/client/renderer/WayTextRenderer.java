package net.deadlydiamond98.way.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.deadlydiamond98.way.util.FaceRenderingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;

public class WayTextRenderer {

    public static void render(PoseStack poseStack, MultiBufferSource bufferSource, Component text, float scale) {
        renderBackplate(poseStack, bufferSource, text, -10.5f, 0, scale);
    }

    private static void renderText() {

    }

    private static void renderBackplate(PoseStack poseStack, MultiBufferSource bufferSource, Component text, float y, float z, float scale) {
        float width = font().width(text) + 2;
        int rgb = 50;
        int alpha = 120;
        VertexConsumer vConsumer = bufferSource.getBuffer(RenderType.debugQuads());
        renderBillboardingFace(poseStack, vConsumer, -width / 2.0f, y, z, width, font().lineHeight, alpha, rgb, rgb, rgb, scale);
    }

    private static Font font() {
        return Minecraft.getInstance().font;
    }

    private static void renderBillboardingFace(PoseStack poseStack, VertexConsumer vCon, float x, float y, float z, float width, float height, int hex, float scale) {
        FaceRenderingUtil.renderBillboardingFace(poseStack, vCon, x, y, z, 0, 1, 0, 1, width, height, hex, scale);
    }

    private static void renderBillboardingFace(PoseStack poseStack, VertexConsumer vCon, float x, float y, float z, float width, float height, int a, int r, int g, int b, float scale) {
        FaceRenderingUtil.renderBillboardingFace(poseStack, vCon, x, y, z, 0, 1, 0, 1, width, height, new int[] {a, r, g, b}, scale);
    }
}
