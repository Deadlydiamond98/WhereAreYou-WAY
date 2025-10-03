package net.deadlydiamond98.way.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.deadlydiamond98.way.util.ColorUtil;
import net.deadlydiamond98.way.util.PlayerLocation;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class WayNameplateRenderEvent {

    // This class is an absolute mess, but that's a problem for future me

    public static void render(PoseStack poseStack, MultiBufferSource bufferSource, ClientLevel level, float renderTick) {
        if (WayKeybindings.renderNameOverlay()) {

            Minecraft client = Minecraft.getInstance();

            if (client.player != null) {

                IWayPlayer wayPlayer = (IWayPlayer) client.player;

                List<Component> names = new ArrayList<>();

                WayTickingEvent.PLAYER_POS.forEach(player -> {

                    names.add(player.name);

                    if (player.getEyePosition().distanceTo(client.player.getPosition(renderTick)) > 100) {
                        poseStack.pushPose();

                        Component name = player.name;
                        int distance = getDistance(poseStack, client, player, renderTick);
                        Component dist = Component.translatable("gui.way.distance", distance);

                        float mainScale = 0.0625f;
                        float nameScale = 0.5f * mainScale;
                        float distScale = 0.25f * mainScale;

                        int nameHex = wayPlayer.way$canSeeColor() ? player.hex : 0xFFFFFF;
                        int distHex = wayPlayer.way$canSeeColor() ? player.hex : 0x55FFFF;

                        if (wayPlayer.way$canSeeName()) {
                            createBackplate(poseStack, bufferSource, name, -10.5f, nameScale);
                            renderText(poseStack, bufferSource, level, name, player.nametagY, nameScale, nameHex);
                        }

                        if (wayPlayer.way$canSeeDist()) {
                            createBackplate(poseStack, bufferSource, dist, -2.75f, distScale);
                            renderText(poseStack, bufferSource, level, dist, player.nametagY - 7.5f, distScale, distHex);
                        }

                        createPlayerPlane(poseStack, bufferSource, player.uuid, player.hex, 0.5f, 0.5f);

                        poseStack.popPose();
                    }
                });

                level.players().forEach(player -> {

                    if (names.contains(player.getName())) {

                        PlayerLocation playerdata = WayTickingEvent.PLAYER_POS.get(names.indexOf(player.getName()));

                        if (player.distanceTo(client.player) <= 100 && !player.isDiscrete()) {
                            poseStack.pushPose();

                            Component name = player.getName();
                            int distance = getDistance(poseStack, client, player, renderTick);
                            Component dist = Component.translatable("gui.way.distance", distance);

                            float mainScale = 0.0625f;
                            float nameScale = 0.5f * mainScale;
                            float distScale = 0.25f * mainScale;

                            int nameHex = wayPlayer.way$canSeeColor() ? playerdata.hex : 0xFFFFFF;
                            int distHex = wayPlayer.way$canSeeColor() ? playerdata.hex : 0x55FFFF;
                            distHex = wayPlayer.way$canSeeName() ? 0xFFFFFF : distHex;

                            if (wayPlayer.way$canSeeName()) {
                                createBackplate(poseStack, bufferSource, name, -10.5f, nameScale);
                                renderText(poseStack, bufferSource, level, name, player.getNameTagOffsetY(), nameScale, nameHex);
                            }

                            if (wayPlayer.way$canSeeDist()) {
                                createBackplate(poseStack, bufferSource, dist, -2.75f, distScale);
                                renderText(poseStack, bufferSource, level, dist, player.getNameTagOffsetY() - 7.5f, distScale, distHex);
                            }

                            createPlayerPlane(poseStack, bufferSource, player.getUUID(), playerdata.hex, 0.5f, 0.5f);

                            poseStack.popPose();
                        }
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
        poseStack.pushPose();

        font.drawInBatch(name, (float)(-font.width(name) / 2), yOffset + 0.1f, color, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.SEE_THROUGH, 0, level.getMaxLightLevel());

        poseStack.popPose();
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
        int rgb = 25;
        renderFace(poseStack, vertexConsumer, x - width, x + width, y, y - height, 0, rgb, rgb, rgb, 120);
        poseStack.popPose();
    }

    private static void createPlayerPlane(PoseStack poseStack, MultiBufferSource bufferSource, UUID uuid, int hex, float scale, float y) {
        Minecraft client = Minecraft.getInstance();
        Camera camera = client.getEntityRenderDispatcher().camera;

        poseStack.pushPose();
        poseStack.mulPose(Axis.YN.rotationDegrees(camera.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(camera.getXRot()));
        poseStack.scale(scale, scale, scale);

        float x = -0.5f;

        VertexConsumer vertexConsumer = bufferSource.getBuffer(WayRenderTypes.getFullbrightNoDepthShape());
        float offset = 0.1f;
        int[] ARGB = ColorUtil.HexToARGB(hex);
        renderFace(poseStack, vertexConsumer, x - offset, x + 1 + offset, y - offset, y + 1 + offset, 0.001f, ARGB[1], ARGB[2], ARGB[3], 255);

        ResourceLocation skin = DefaultPlayerSkin.getDefaultSkin(uuid);
        VertexConsumer vertexConsumer2 = bufferSource.getBuffer(WayRenderTypes.getFullbrightNoDepth(skin));
        renderFace(poseStack, vertexConsumer, x, x + 1, y, y + 1, 0, 255, 255, 255, 255);

        poseStack.popPose();
    }

    private static void renderFace(PoseStack poseStack, VertexConsumer vertexConsumer, float x1, float x2, float y1, float y2, float z, int r, int g, int b, int a) {
        PoseStack.Pose pose = poseStack.last();
        Matrix4f m4f = pose.pose();
        Matrix3f m3f = pose.normal();

        float min = 0.125f;
        float max = 0.25f;

        vertex(vertexConsumer, m4f, m3f, x2, y1, min, max, z, r, g, b, a);
        vertex(vertexConsumer, m4f, m3f, x1, y1, max, max, z, r, g, b, a);
        vertex(vertexConsumer, m4f, m3f, x1, y2, max, min, z, r, g, b, a);
        vertex(vertexConsumer, m4f, m3f, x2, y2, min, min, z, r, g, b, a);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, float x, float y, float u, float v, float z, int r, int g, int b, int a) {
        vertexConsumer.vertex(matrix4f, x, y, z).color(r, g, b, a).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(matrix3f, 0, 1, 0).endVertex();
    }
}
