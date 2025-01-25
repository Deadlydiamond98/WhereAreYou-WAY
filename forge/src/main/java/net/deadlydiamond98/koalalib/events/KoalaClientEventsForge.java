package net.deadlydiamond98.koalalib.events;

import net.deadlydiamond98.koalalib.util.registry_tools.services.KoalaRegistryMaps;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class KoalaClientEventsForge {

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Block Render Layers
            KoalaRegistryMaps.registerAndEmpty(KoalaRegistryMaps.BLOCK_RENDER_LAYERS, (block, regtype) -> ItemBlockRenderTypes.setRenderLayer(block.get(), regtype));
        });
    }



    public static void registerRenderLayers(EntityRenderersEvent.RegisterRenderers event) {
        // Entity Renderers
        KoalaRegistryMaps.registerAndEmpty(KoalaRegistryMaps.ENTITY_RENDERERS, (entity, provider) -> event.registerEntityRenderer(entity.get(), provider));
        // Block Entity Renderers
        KoalaRegistryMaps.registerAndEmpty(KoalaRegistryMaps.BLOCK_ENTITY_RENDERERS, (bentity, provider) -> event.registerBlockEntityRenderer(bentity.get(), provider));
    }
}
