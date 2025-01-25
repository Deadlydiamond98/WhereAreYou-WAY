package net.deadlydiamond98.koalalib.util.registry_tools;

import net.deadlydiamond98.koalalib.KoalaLib;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class MultiModClientRegistries {

    public static <T extends Entity> void addEntityRenderer(Supplier<EntityType<T>> type, EntityRendererProvider<T> provider) {
        KoalaLib.ENTITY_RENDERERS.put(type, provider);
    }

    public static <T extends BlockEntity> void addBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererProvider<T> provider) {
        KoalaLib.BLOCK_ENTITY_RENDERERS.put(type, provider);
    }
}
