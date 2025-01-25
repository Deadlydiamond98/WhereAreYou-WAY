package net.deadlydiamond98.koalalib;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.deadlydiamond98.koalalib.testing.KoalaLibBlockEntities;
import net.deadlydiamond98.koalalib.testing.KoalaLibBlocks;
import net.deadlydiamond98.koalalib.testing.KoalaLibItems;
import net.deadlydiamond98.koalalib.testing.objs.TestBlockEntityRenderer;
import net.deadlydiamond98.koalalib.util.registry_tools.MultiModClientRegistries;
import net.deadlydiamond98.koalalib.util.registry_tools.MultiModRegistries;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Supplier;

public class KoalaLib {

    // Entity Renderers
    public static final Map<Supplier<? extends EntityType<? extends Entity>>, EntityRendererProvider<? extends Entity>> ENTITY_RENDERERS = new Reference2ObjectOpenHashMap<>();
    // Block Entity Renderers
    public static final Map<Supplier<? extends BlockEntityType<? extends BlockEntity>>, BlockEntityRendererProvider<?>> BLOCK_ENTITY_RENDERERS = new Reference2ObjectOpenHashMap<>();

    public static final String MOD_ID = "koalalib";
    public static final String MOD_NAME = "Koala Lib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {

        MultiModRegistries.push();

        KoalaLibItems.register();
        KoalaLibBlocks.register();
        KoalaLibBlockEntities.register();

        MultiModRegistries.pop();

        MultiModClientRegistries.addBlockEntityRenderer(KoalaLibBlockEntities.TEST_B_ENTITY, TestBlockEntityRenderer::new);

        LOGGER.info("Finished Loading " + MOD_NAME);
    }
}