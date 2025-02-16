package net.deadlydiamond98.koalalib.common.registration;

import net.deadlydiamond98.koalalib.core.KoalaRegistrationLists;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class MultiModClientRegistries {

    public static void registerBlockRenderLayer(Supplier<Block> block, RenderType renderType) {
        KoalaRegistrationLists.BLOCK_RENDER_LAYERS.put(block, renderType);
    }

    public static <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> type, EntityRendererProvider<? super Entity> provider) {
        KoalaRegistrationLists.ENTITY_RENDERERS.put(type, provider);
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererProvider<? super BlockEntity> provider) {
        KoalaRegistrationLists.BLOCK_ENTITY_RENDERERS.put(type, provider);
    }
}
