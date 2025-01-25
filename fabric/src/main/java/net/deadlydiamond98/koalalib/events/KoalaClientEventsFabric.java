package net.deadlydiamond98.koalalib.events;

import net.deadlydiamond98.koalalib.util.registry_tools.services.KoalaRegistryMaps;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Environment(EnvType.CLIENT)
public class KoalaClientEventsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Block Render Layers
        KoalaRegistryMaps.registerAndEmpty(KoalaRegistryMaps.BLOCK_RENDER_LAYERS, (block, regtype) -> BlockRenderLayerMap.INSTANCE.putBlock(block.get(), regtype));
        // Entity Renderers
        KoalaRegistryMaps.registerAndEmpty(KoalaRegistryMaps.ENTITY_RENDERERS, (entity, provider) -> EntityRendererRegistry.register(entity.get(), provider));
        // Block Entity Renderers
        KoalaRegistryMaps.registerAndEmpty(KoalaRegistryMaps.BLOCK_ENTITY_RENDERERS, (bentity, provider) -> BlockEntityRenderers.register(bentity.get(), provider));
    }
}
