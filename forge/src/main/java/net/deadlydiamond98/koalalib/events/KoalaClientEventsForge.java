package net.deadlydiamond98.koalalib.events;

import net.deadlydiamond98.koalalib.util.registry_tools.services.KoalaRegistrationLists;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class KoalaClientEventsForge {

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Block Render Layers
            KoalaRegistrationLists.registerAndEmpty(KoalaRegistrationLists.BLOCK_RENDER_LAYERS, (block, regtype) -> ItemBlockRenderTypes.setRenderLayer(block.get(), regtype));
//            // Item Model Predicates
//            KoalaRegistrationLists.ITEM_MODEL_PREDICATES.forEach(modelPredicate -> {
//                ItemProperties.register(modelPredicate.item().get(), modelPredicate.resourceLocation(), modelPredicate.function());
//            });
//            KoalaRegistrationLists.ITEM_MODEL_PREDICATES.clear();
        });
    }



    public static void registerRenderLayers(EntityRenderersEvent.RegisterRenderers event) {
        // Entity Renderers
        KoalaRegistrationLists.registerAndEmpty(KoalaRegistrationLists.ENTITY_RENDERERS, (entity, provider) -> event.registerEntityRenderer(entity.get(), provider));
        // Block Entity Renderers
        KoalaRegistrationLists.registerAndEmpty(KoalaRegistrationLists.BLOCK_ENTITY_RENDERERS, (bentity, provider) -> event.registerBlockEntityRenderer(bentity.get(), provider));
    }
}
