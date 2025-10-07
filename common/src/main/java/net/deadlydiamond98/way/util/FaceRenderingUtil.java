package net.deadlydiamond98.way.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class FaceRenderingUtil {

    public static void renderBillboardingFace(PoseStack poseStack, VertexConsumer vConsumer, float x, float y, float z, float u1, float u2, float v1, float v2, float width, float height, int hex, float scale) {
        renderBillboardingFace(poseStack, vConsumer, x, y, z, u1, u2, v1, v2, width, height, ColorUtil.HexToARGB(hex), scale);
    }

    public static void renderBillboardingFace(PoseStack poseStack, VertexConsumer vConsumer, float x, float y, float z, float u1, float u2, float v1, float v2, float width, float height, int[] argb, float scale) {
        Minecraft client = Minecraft.getInstance();
        Camera camera = client.getEntityRenderDispatcher().camera;

        poseStack.pushPose();

        poseStack.mulPose(Axis.YN.rotationDegrees(camera.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(camera.getXRot()));
        poseStack.scale(scale, scale, scale);

        renderFace(poseStack, vConsumer, x, x + width, y, y + height, z, u1, u2, v1, v2, argb[1], argb[2], argb[3], argb[0]);

        poseStack.popPose();
    }

    public static void renderFace(PoseStack poseStack, VertexConsumer vertexConsumer, float x1, float x2, float y1, float y2, float z, float u1, float u2, float v1, float v2, int r, int g, int b, int a) {
        PoseStack.Pose pose = poseStack.last();
        Matrix4f m4f = pose.pose();
        Matrix3f m3f = pose.normal();

        vertex(vertexConsumer, m4f, m3f, x2, y1, u1, v2, z, r, g, b, a);
        vertex(vertexConsumer, m4f, m3f, x1, y1, u2, v2, z, r, g, b, a);
        vertex(vertexConsumer, m4f, m3f, x1, y2, u2, v1, z, r, g, b, a);
        vertex(vertexConsumer, m4f, m3f, x2, y2, u1, v1, z, r, g, b, a);
    }

    public static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, float x, float y, float u, float v, float z, int r, int g, int b, int a) {
        vertexConsumer.vertex(matrix4f, x, y, z).color(r, g, b, a).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(matrix3f, 0, 1, 0).endVertex();
    }
}
