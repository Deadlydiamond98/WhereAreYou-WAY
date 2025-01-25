package net.deadlydiamond98.koalalib.events;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.deadlydiamond98.koalalib.KoalaLib;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = KoalaLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KoalaClientRegistryForge {
    @SubscribeEvent
    public static void registerRenderLayers(EntityRenderersEvent.RegisterRenderers event) {

        // Register Entity Renderers
        KoalaLib.ENTITY_RENDERERS.forEach((type, provider) -> event.registerEntityRenderer(type.get(), (EntityRendererProvider<Entity>) provider));

        // Register Block Entity Renderers
        KoalaLib.BLOCK_ENTITY_RENDERERS.forEach((type, provider) -> event.registerBlockEntityRenderer(type.get(), (BlockEntityRendererProvider<BlockEntity>) provider));
    }
}
