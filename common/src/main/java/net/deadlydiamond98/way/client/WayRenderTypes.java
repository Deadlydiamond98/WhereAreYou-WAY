package net.deadlydiamond98.way.client;

import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class WayRenderTypes extends RenderType {
    // This is assigned a value in forge and fabric due to the common form of RenderType.create() being inaccessible
    public static Function<ResourceLocation, RenderType> FULLBRIGHT_NO_DEPTH_RENDER_LAYER;
    public static Function<String, RenderType> FULLBRIGHT_NO_DEPTH_SHAPE_RENDERLAYER;

    public WayRenderTypes(String name, VertexFormat vertexFormat, VertexFormat.Mode mode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, mode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    public static RenderType getFullbrightNoDepthShape() {
        return FULLBRIGHT_NO_DEPTH_SHAPE_RENDERLAYER.apply("");
    }

    public static RenderType getFullbrightNoDepth(ResourceLocation texture) {
        return FULLBRIGHT_NO_DEPTH_RENDER_LAYER.apply(texture);
    }
}
