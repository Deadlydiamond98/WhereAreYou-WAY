package net.deadlydiamond98.koalalib.events;

import net.deadlydiamond98.koalalib.common.events.events.KoalaClientTickEvents;
import net.deadlydiamond98.koalalib.core.KoalaRegistrationLists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
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

    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft client = Minecraft.getInstance();
        if (event.phase == TickEvent.Phase.END) {
            KoalaClientTickEvents.END_CLIENT_TICK.invoker().onEndTick(client);
        }

        if (event.phase == TickEvent.Phase.START) {
            KoalaClientTickEvents.START_CLIENT_TICK.invoker().onStartTick(client);
        }
    }
}
