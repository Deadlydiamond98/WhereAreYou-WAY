package net.deadlydiamond98.way.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.deadlydiamond98.way.util.PlayerLocation;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;


public class WayNameplateRenderEvent {

    public static void render(PoseStack poseStack, MultiBufferSource bufferSource, ClientLevel level, float renderTick) {
        if (WayKeybindings.renderNameOverlay) {

            Minecraft client = Minecraft.getInstance();

            if (client.player != null) {
                level.players().forEach(player -> {
                    if (player.distanceTo(client.player) <= 100 && player != client.player && !player.isDiscrete()) {
                        poseStack.pushPose();

                        Component name = player.getName();
                        int distance = getDistance(poseStack, client, player, renderTick);
                        Component dist = Component.translatable("gui.findmyfriendsfix.distance", distance);

                        float mainScale = 0.0625f;
                        float nameScale = 0.5f * mainScale;
                        float distScale = 0.25f * mainScale;

                        createBackplate(poseStack, bufferSource, name, -10.5f, nameScale);
                        createBackplate(poseStack, bufferSource, dist, -2.75f, distScale);

                        renderText(poseStack, bufferSource, level, name, player.getNameTagOffsetY(), nameScale, -1);
                        renderText(poseStack, bufferSource, level, dist, player.getNameTagOffsetY() - 7.5f, distScale, 0x55FFFF);

                        poseStack.popPose();
                    }
                });

                WayTickingEvent.PLAYER_POS.forEach(player -> {

                    if (player.getEyePosition().distanceTo(client.player.getPosition(renderTick)) > 100) {
                        poseStack.pushPose();

                        Component name = player.name;
                        int distance = getDistance(poseStack, client, player, renderTick);
                        Component dist = Component.translatable("gui.findmyfriendsfix.distance", distance);

                        float mainScale = 0.0625f;
                        float nameScale = 0.5f * mainScale;
                        float distScale = 0.25f * mainScale;

                        createBackplate(poseStack, bufferSource, name, -10.5f, nameScale);
                        createBackplate(poseStack, bufferSource, dist, -2.75f, distScale);

                        renderText(poseStack, bufferSource, level, name, player.nametagY, nameScale, -1);
                        renderText(poseStack, bufferSource, level, dist, player.nametagY - 7.5f, distScale, 0x55FFFF);

                        poseStack.popPose();
                    }
                });
            }
        }
    }

    private static int getDistance(PoseStack poseStack, Minecraft client, Player player, float renderTick) {
        return getDistance(poseStack, client, renderTick, player.getPosition(renderTick).add(0, player.getNameTagOffsetY(), 0));
    }

    private static int getDistance(PoseStack poseStack, Minecraft client, PlayerLocation player, float renderTick) {
        return getDistance(poseStack, client, renderTick, player.getPosition().add(0, player.nametagY, 0));
    }

    private static int getDistance(PoseStack poseStack, Minecraft client, float renderTick, Vec3 tagPos) {
        Camera camera = client.getEntityRenderDispatcher().camera;

        Vec3 cameraPos = camera.getPosition();
        poseStack.translate(-cameraPos.x(), -cameraPos.y(), -cameraPos.z());

        Vec3 playerPos = client.player.getEyePosition(renderTick);

        Vec3 direction = playerPos.subtract(tagPos).normalize();
        double currentDistance = playerPos.distanceTo(tagPos);

        int maxOffsetDistance = 10;
        if (currentDistance >= maxOffsetDistance) {
            tagPos = tagPos.add(direction.scale(currentDistance - maxOffsetDistance));
        }

        poseStack.translate(tagPos.x, tagPos.y, tagPos.z);

        return (int) Math.floor(currentDistance);
    }

    private static void renderText(PoseStack poseStack, MultiBufferSource bufferSource, ClientLevel level, Component name, float yOffset, float scale, int color) {
        Minecraft client = Minecraft.getInstance();
        Camera camera = client.getEntityRenderDispatcher().camera;
        Font font = client.font;

        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        poseStack.mulPose(Axis.YP.rotationDegrees(camera.getYRot()));
        poseStack.mulPose(Axis.XN.rotationDegrees(camera.getXRot()));

        poseStack.scale(scale, scale, scale);
        font.drawInBatch(name, (float)(-font.width(name) / 2), yOffset, color, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.SEE_THROUGH, 0, level.getMaxLightLevel());

        poseStack.popPose();
    }

    // Backplate thing is created because text has weird layering issues with their background
    private static void createBackplate(PoseStack poseStack, MultiBufferSource bufferSource, Component name, float y, float scale) {
        Minecraft client = Minecraft.getInstance();
        Camera camera = client.getEntityRenderDispatcher().camera;
        Font font = client.font;

        poseStack.pushPose();
        poseStack.mulPose(Axis.YN.rotationDegrees(camera.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(camera.getXRot()));
        poseStack.scale(scale, scale, scale);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.debugQuads());

        float x = 0;

        float width = -(font.width(name) + 2) / 2.0f;
        float height = -font.lineHeight;

        renderFace(poseStack, vertexConsumer, x - width, x + width, y, y - height);
        poseStack.popPose();
    }


    private static void renderFace(PoseStack poseStack, VertexConsumer vertexConsumer, float x1, float x2, float y1, float y2) {
        PoseStack.Pose pose = poseStack.last();
        Matrix4f m4f = pose.pose();
        Matrix3f m3f = pose.normal();

        vertex(vertexConsumer, m4f, m3f, x2, y1, 0, 1);
        vertex(vertexConsumer, m4f, m3f, x1, y1, 1, 1);
        vertex(vertexConsumer, m4f, m3f, x1, y2, 1, 0);
        vertex(vertexConsumer, m4f, m3f, x2, y2, 0, 0);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, float x, float y, float u, float v) {
        int rgb = 75;
        vertexConsumer.vertex(matrix4f, x, y, 0).color(rgb, rgb, rgb, 40).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(matrix3f, 0, 1, 0).endVertex();
    }
}
