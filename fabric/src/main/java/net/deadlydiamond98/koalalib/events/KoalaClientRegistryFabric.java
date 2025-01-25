package net.deadlydiamond98.koalalib.events;

import net.deadlydiamond98.koalalib.KoalaLib;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;

@Environment(EnvType.CLIENT)
public class KoalaClientRegistryFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Register Entity Renderers
        KoalaLib.ENTITY_RENDERERS.forEach((type, provider) -> EntityRendererRegistry.register(type.get(), (EntityRendererProvider<Entity>) provider));

        // Register Block Entity Renderers
        KoalaLib.BLOCK_ENTITY_RENDERERS.forEach((type, provider) -> BlockEntityRenderers.register(type.get(), (BlockEntityRendererProvider<BlockEntity>) provider));
    }
}
