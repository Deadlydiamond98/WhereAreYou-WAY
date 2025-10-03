package net.deadlydiamond98.way.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;

public class WayFabricRenderType extends RenderType {

    public WayFabricRenderType(String string, VertexFormat vertexFormat, VertexFormat.Mode mode, int i, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
        super(string, vertexFormat, mode, i, bl, bl2, runnable, runnable2);
    }

    public static void register() {
        WayRenderTypes.FULLBRIGHT_NO_DEPTH_RENDER_LAYER = Util.memoize((texture) -> {
            RenderType.CompositeState multiPhaseParameters = RenderType.CompositeState.builder()
                    .setShaderState(RenderStateShard.RENDERTYPE_BEACON_BEAM_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                    .setDepthTestState(NO_DEPTH_TEST)
                    .setLightmapState(LIGHTMAP)
                    .setOutputState(OUTLINE_TARGET)
                    .createCompositeState(true);

            return RenderType.create(
                    "way:fullbright_nocull",
                    DefaultVertexFormat.POSITION_COLOR_TEX,
                    VertexFormat.Mode.QUADS,
                    256,
                    true,
                    true,
                    multiPhaseParameters
            );
        });

        WayRenderTypes.FULLBRIGHT_NO_DEPTH_SHAPE_RENDERLAYER = Util.memoize(resourceLocation -> {
            RenderType.CompositeState multiPhaseParameters = RenderType.CompositeState.builder()
                    .setShaderState(POSITION_COLOR_SHADER)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setCullState(NO_CULL)
                    .setDepthTestState(NO_DEPTH_TEST)
                    .setOutputState(OUTLINE_TARGET)
                    .createCompositeState(false);

            return RenderType.create(
                    "way:fullbright_nocull_shape",
                    DefaultVertexFormat.POSITION_COLOR,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    multiPhaseParameters
            );
        });
    }
}
