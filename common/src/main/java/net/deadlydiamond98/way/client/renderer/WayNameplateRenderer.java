package net.deadlydiamond98.way.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.deadlydiamond98.way.client.WayKeybindings;
import net.deadlydiamond98.way.client.WayRenderTypes;
import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.deadlydiamond98.way.util.ColorUtil;
import net.deadlydiamond98.way.util.PlayerLocation;
import net.deadlydiamond98.way.util.FaceRenderingUtil;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class WayNameplateRenderer {

    // This class is an absolute mess, but that's a problem for future me

    // Future me here, what form of crack was I on?!?!?!? This is now cleaned up

    private static final float[] UV = {0.125f, 0.25f};
    private static final int NAMETAG_RENDER_CUTTOFF = 100;

    public static void render(PoseStack poseStack, MultiBufferSource bufferSource, ClientLevel level, float renderTick) {
        if (WayKeybindings.renderNameOverlay()) {

            Minecraft client = Minecraft.getInstance();
            LocalPlayer user = client.player;

            if (user instanceof IWayPlayer wayPlayer) {
                WayTickingEvent.PLAYER_POS.forEach(playerData -> {
                    if (playerData.getEyePosition().distanceTo(user.getEyePosition(renderTick)) > NAMETAG_RENDER_CUTTOFF) {
                        Vec3 pos = playerData.getPosition().add(0, playerData.nametagY, 0);
                        renderNameplate(poseStack, bufferSource, level, renderTick, wayPlayer, playerData, pos);

                    } else {
                        level.players().forEach(player -> {

//                            boolean thirdPerson = user.equals(player) && !client.getEntityRenderDispatcher().camera.isDetached();
                            boolean thirdPerson = false;
                            boolean invis = player.isDiscrete() || player.isInvisible();
                            boolean isSame = playerData.name.getString().equals(player.getName().getString());

                            if (isSame && !thirdPerson && !invis && player.distanceTo(user) <= NAMETAG_RENDER_CUTTOFF) {
                                Vec3 pos = player.getPosition(renderTick).add(0, player.getNameTagOffsetY(), 0);
                                renderNameplate(poseStack, bufferSource, level, renderTick, wayPlayer, playerData, pos);

                            }
                        });
                    }
                });
            }
        }

        // DATA
//            int nameHex = wayPlayer.way$canSeeColor() ? player.hex : 0xFFFFFF;
//
//            if (Way.colorDistance) {
//                nameHex = ColorUtil.blendHexColors(nameHex, 0xFFFFFF, distance / 2000.0f);
//            }
//
//            int distHex = wayPlayer.way$canSeeColor() ? player.hex : 0x55FFFF;
//            distHex = wayPlayer.way$canSeeName() ? 0xFFFFFF : distHex;


        // REG
//            int nameHex = wayPlayer.way$canSeeColor() ? playerdata.hex : 0xFFFFFF;
//
//            if (Way.colorDistance) {
//                nameHex = ColorUtil.blendHexColors(nameHex, 0xFFFFFF, distance / 2000.0f);
//            }
//
//            if (Way.namePain) {
//                nameHex = ColorUtil.blendHexColors(nameHex, 0xFF0000, player.hurtTime / 10.0f);
//            }
//            nameHex = nameHex  | 0xFF000000;
//
//            int distHex = wayPlayer.way$canSeeColor() ? playerdata.hex : 0x55FFFF;
//            distHex = wayPlayer.way$canSeeName() ? 0xFFFFFF : distHex;
    }
    
    private static void renderNameplate(PoseStack poseStack, MultiBufferSource bufferSource, ClientLevel level, float renderTick, IWayPlayer viewer, PlayerLocation data, Vec3 pos) {
        poseStack.pushPose();

        int distance = getDistance(poseStack, renderTick, pos);
        Component dist = Component.translatable("gui.way.distance", distance);

        float mainScale = 0.0625f;
        float nameScale = 0.5f * mainScale;
        float distScale = 0.25f * mainScale;


//        if (viewer.way$canSeeName()) {
//            int nameHex = viewer.way$canSeeColor() ? data.hex : 0xFFFFFF;
//            int shadowHex = ColorUtil.blendHexColors(nameHex, 0xFF000000, 0.7f);
//
//            renderBackplate(poseStack, bufferSource, data.name, -10.5f, 0, nameScale);
//
//            renderText(poseStack, bufferSource, level, data.name, 0.03f, 0.03f, z, data.nametagY, nameScale, shadowHex);
//            renderText(poseStack, bufferSource, level, data.name, 0, 0, 0, data.nametagY, nameScale, nameHex);
//        }
//
//        if (viewer.way$canSeeDist()) {
//            int distHex = viewer.way$canSeeColor() ? data.hex : 0x55FFFF;
//            int shadowHex = ColorUtil.blendHexColors(distHex, 0xFF000000, 0.7f);
//
//            renderBackplate(poseStack, bufferSource, dist, -2.75f, 0, distScale);
//            renderText(poseStack, bufferSource, level, dist, 0.03f, 0.03f, z, data.nametagY - 7.5f, distScale, shadowHex);
//            renderText(poseStack, bufferSource, level, dist, 0, 0, 0, data.nametagY - 7.5f, distScale, distHex);
//        }

        float z = 0.03f;

        int nameHex = viewer.way$canSeeColor() ? data.hex : 0xFFFFFF;
        int distHex = viewer.way$canSeeColor() ? data.hex : 0x55FFFF;
        int shadowHex = ColorUtil.blendHexColors(distHex, 0xFF000000, 0.7f);

        renderBackplate(poseStack, bufferSource, data.name, -10.5f, 0, nameScale);
        renderBackplate(poseStack, bufferSource, dist, -2.75f, 0, distScale);

        // Back
        renderText(poseStack, bufferSource, level, dist, 0.03f, 0.03f, z, data.nametagY - 7.5f, distScale, shadowHex);
        renderText(poseStack, bufferSource, level, data.name, 0.03f, 0.03f, z, data.nametagY, nameScale, shadowHex);

        // Front
        renderText(poseStack, bufferSource, level, dist, 0, 0, 0, data.nametagY - 7.5f, distScale, distHex);
        renderText(poseStack, bufferSource, level, data.name, 0, 0, 0, data.nametagY, nameScale, nameHex);

        renderPlayerIcon(poseStack, bufferSource, data.uuid, data.hex, 0.25f, 0.5f);

        poseStack.popPose();
    }

    private static void renderText(PoseStack poseStack, MultiBufferSource bufferSource, ClientLevel level, Component name, float x, float y, float z, float yOffset, float scale, int color) {
        Minecraft client = Minecraft.getInstance();
        Camera camera = client.getEntityRenderDispatcher().camera;
        Font font = client.font;

        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        poseStack.mulPose(Axis.YP.rotationDegrees(camera.getYRot()));
        poseStack.mulPose(Axis.XN.rotationDegrees(camera.getXRot()));
        poseStack.translate(x, y, z);
        poseStack.scale(scale, scale, scale);

        font.drawInBatch(name, (float)(-font.width(name) / 2), yOffset + 0.1f, color, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.SEE_THROUGH, 0, level.getMaxLightLevel());

        poseStack.popPose();
    }

    // Backplate thing is created because text has weird layering issues with their background
    private static void renderBackplate(PoseStack poseStack, MultiBufferSource bufferSource, Component text, float y, float z, float scale) {
        Font font = Minecraft.getInstance().font;
        float width = font.width(text) + 2;
        int rgb = 50;
        int alpha = 120;
        VertexConsumer vConsumer = bufferSource.getBuffer(RenderType.debugQuads());
        renderBillboardingFace(poseStack, vConsumer, -width / 2.0f, y, z, width, font.lineHeight, alpha, rgb, rgb, rgb, scale);
    }

    private static void renderPlayerIcon(PoseStack poseStack, MultiBufferSource bufferSource, UUID uuid, int hex, float y, float scale) {
        float sizeOffset = 0.1f;

        float x = -0.5f;
        float bgWidthHeight = 1 + (sizeOffset * 2);

        VertexConsumer vConBG = bufferSource.getBuffer(WayRenderTypes.getFullbrightNoDepthShape());
        renderBillboardingFace(poseStack, vConBG, x - sizeOffset, y - sizeOffset, 0.001f, bgWidthHeight, bgWidthHeight, hex, scale);

        ClientPacketListener connection =  Minecraft.getInstance().getConnection();

        if (connection != null) {
            PlayerInfo info = connection.getPlayerInfo(uuid);
            if (info != null) {
                VertexConsumer vConSkin = bufferSource.getBuffer(WayRenderTypes.getFullbrightNoDepth(info.getSkinLocation()));
                renderBillboardingFace(poseStack, vConSkin, x, y, 0, 1, 1, 255, 255, 255, 255, scale);
            }
        }
    }

    private static int getDistance(PoseStack poseStack, float renderTick, Vec3 tagPos) {
        Minecraft client = Minecraft.getInstance();
        Camera camera = client.getEntityRenderDispatcher().camera;

        Vec3 cameraPos = camera.getPosition();
        poseStack.translate(-cameraPos.x(), -cameraPos.y(), -cameraPos.z());

        Vec3 playerPos = client.player.getPosition(renderTick).add(0, 1.53, 0);

        Vec3 direction = playerPos.subtract(tagPos).normalize();
        double currentDistance = playerPos.distanceTo(tagPos);

        int maxOffsetDistance = 10;
        if (currentDistance >= maxOffsetDistance) {
            tagPos = tagPos.add(direction.scale(currentDistance - maxOffsetDistance));
        }

        poseStack.translate(tagPos.x, tagPos.y, tagPos.z);

        return (int) Math.floor(currentDistance);
    }


    private static void renderBillboardingFace(PoseStack poseStack, VertexConsumer vCon, float x, float y, float z, float width, float height, int hex, float scale) {
        FaceRenderingUtil.renderBillboardingFace(poseStack, vCon, x, y, z, UV[0], UV[1], UV[0], UV[1], width, height, hex, scale);
    }

    private static void renderBillboardingFace(PoseStack poseStack, VertexConsumer vCon, float x, float y, float z, float width, float height, int a, int r, int g, int b, float scale) {
        FaceRenderingUtil.renderBillboardingFace(poseStack, vCon, x, y, z, UV[0], UV[1], UV[0], UV[1], width, height, new int[] {a, r, g, b}, scale);
    }
}
