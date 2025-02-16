package net.deadlydiamond98.koalalib;

import net.deadlydiamond98.koalalib.api.blocks.KoalaBlockConversions;
import net.deadlydiamond98.koalalib.api.content.KoalaLibSounds;
import net.deadlydiamond98.koalalib.testing.KoalaLibBlockEntities;
import net.deadlydiamond98.koalalib.testing.KoalaLibBlocks;
import net.deadlydiamond98.koalalib.testing.KoalaLibCreativeTabs;
import net.deadlydiamond98.koalalib.testing.KoalaLibTesttingItems;
import net.deadlydiamond98.koalalib.testing.objs.TestBlockEntityRenderer;
import net.deadlydiamond98.koalalib.api.registration.MultiModClientRegistries;
import net.deadlydiamond98.koalalib.api.registration.MultiModRegistries;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KoalaLib {

    public static final String MOD_ID = "koalalib";
    public static final String MOD_NAME = "Koala Lib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {

        MultiModRegistries.push();

        KoalaLibSounds.register();

        KoalaLibTesttingItems.register();
        KoalaLibBlocks.register();
        KoalaLibBlockEntities.register();
        KoalaLibCreativeTabs.register();

        MultiModRegistries.pop();

        MultiModClientRegistries.registerBlockRenderLayer(KoalaLibBlocks.TEST, RenderType.cutout());
        MultiModClientRegistries.registerBlockEntityRenderer(KoalaLibBlockEntities.TEST_B_ENTITY, TestBlockEntityRenderer::new);

        KoalaBlockConversions.addBlockToStrippables(Blocks.ANDESITE, Blocks.POLISHED_ANDESITE);
        KoalaBlockConversions.addBlockToWaxables(Blocks.DIAMOND_BLOCK, Blocks.IRON_BLOCK);

        LOGGER.info("Finished Loading " + MOD_NAME);
    }
}