package net.deadlydiamond98.way.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;

public class WayForgeRenderType extends RenderType {
    public WayForgeRenderType(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
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

        WayRenderTypes.FULLBRIGHT_NO_DEPTH_SHAPE_RENDERLAYER = Util.memoize(ignored -> {
            RenderType.CompositeState multiPhaseParameters = RenderType.CompositeState.builder()
                    .setShaderState(POSITION_COLOR_SHADER)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setDepthTestState(NO_DEPTH_TEST)
                    .setOutputState(OUTLINE_TARGET)
                    .createCompositeState(true);

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
