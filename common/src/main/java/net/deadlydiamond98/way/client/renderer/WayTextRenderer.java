package net.deadlydiamond98.way.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.deadlydiamond98.way.Way;
import net.deadlydiamond98.way.client.WayRenderTypes;
import net.deadlydiamond98.way.util.CharRendererValues;
import net.deadlydiamond98.way.util.FaceRenderingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class WayTextRenderer {

    public static final float TEX_WIDTH = 113.0f;
    public static final float TEX_HEIGHT = 48.0f;

    // 7, 8

    public static void render(PoseStack poseStack, MultiBufferSource bufferSource, Component text, float x, float y, float scale) {
        renderBackplate(poseStack, bufferSource, text, y, 0, scale);
        renderText(poseStack, bufferSource, text, x, y, scale);
    }

    private static void renderText(PoseStack poseStack, MultiBufferSource bufferSource, Component text, float x, float y, float scale) {
        String str = text.getString();
        VertexConsumer vCon = bufferSource.getBuffer(WayRenderTypes.getFullbrightNoDepth(new ResourceLocation(Way.MOD_ID, "textures/nameplate_txt.png")));

        float x2 = x;
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);

            int[] values = CharRendererValues.CHAR_VALUES.get(letter);

            float u1 = values[0] * (7 / TEX_WIDTH);
            float u2 = u1 + (8 / TEX_WIDTH);
            float v1 = values[1] * (8 / TEX_HEIGHT);
            float v2 = v1 + (8 / TEX_HEIGHT);

            renderBillboardingFace(poseStack, vCon, x2, y, u1, u2, v1, v2, 8, 8, 0xFFFFFFFF, scale);
            x2 -= values[2] - 1;
        }
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

    private static void renderBillboardingFace(PoseStack poseStack, VertexConsumer vCon, float x, float y, float u1, float u2, float v1, float v2, float width, float height, int hex, float scale) {
        FaceRenderingUtil.renderBillboardingFace(poseStack, vCon, x, y, 0, u1, u2, v1, v2, width, height, hex, scale);
    }

    private static void renderBillboardingFace(PoseStack poseStack, VertexConsumer vCon, float x, float y, float z, float width, float height, int a, int r, int g, int b, float scale) {
        FaceRenderingUtil.renderBillboardingFace(poseStack, vCon, x, y, z, 0, 1, 0, 1, width, height, new int[] {a, r, g, b}, scale);
    }
}
