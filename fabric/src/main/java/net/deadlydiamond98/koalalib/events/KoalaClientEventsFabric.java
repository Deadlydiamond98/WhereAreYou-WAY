package net.deadlydiamond98.koalalib.events;

import net.deadlydiamond98.koalalib.common.events.events.KoalaClientTickEvents;
import net.deadlydiamond98.koalalib.core.KoalaRegistrationLists;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Environment(EnvType.CLIENT)
public class KoalaClientEventsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Block Render Layers
        KoalaRegistrationLists.registerAndEmpty(KoalaRegistrationLists.BLOCK_RENDER_LAYERS, (block, regtype) -> BlockRenderLayerMap.INSTANCE.putBlock(block.get(), regtype));
        // Entity Renderers
        KoalaRegistrationLists.registerAndEmpty(KoalaRegistrationLists.ENTITY_RENDERERS, (entity, provider) -> EntityRendererRegistry.register(entity.get(), provider));
        // Block Entity Renderers
        KoalaRegistrationLists.registerAndEmpty(KoalaRegistrationLists.BLOCK_ENTITY_RENDERERS, (bentity, provider) -> BlockEntityRenderers.register(bentity.get(), provider));
//        // Item Model Predicates
//        KoalaRegistrationLists.ITEM_MODEL_PREDICATES.forEach(modelPredicate -> {
//            ItemProperties.register(modelPredicate.item().get(), modelPredicate.resourceLocation(), modelPredicate.function());
//        });
//        KoalaRegistrationLists.ITEM_MODEL_PREDICATES.clear();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // EVENTS

        // Client Tick Events
        ClientTickEvents.END_CLIENT_TICK.register(client -> KoalaClientTickEvents.END_CLIENT_TICK.invoker().onEndTick(client));
        ClientTickEvents.START_CLIENT_TICK.register(client -> KoalaClientTickEvents.START_CLIENT_TICK.invoker().onStartTick(client));
        ClientTickEvents.END_WORLD_TICK.register(world -> KoalaClientTickEvents.END_WORLD_TICK.invoker().onEndWorldTick(world));
        ClientTickEvents.START_WORLD_TICK.register(world -> KoalaClientTickEvents.START_WORLD_TICK.invoker().onStartWorldTick(world));
    }
}
