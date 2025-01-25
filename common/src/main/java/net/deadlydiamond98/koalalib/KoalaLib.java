package net.deadlydiamond98.koalalib;

import net.deadlydiamond98.koalalib.testing.KoalaLibBlockEntities;
import net.deadlydiamond98.koalalib.testing.KoalaLibBlocks;
import net.deadlydiamond98.koalalib.testing.KoalaLibCreativeTabs;
import net.deadlydiamond98.koalalib.testing.KoalaLibItems;
import net.deadlydiamond98.koalalib.testing.objs.TestBlockEntityRenderer;
import net.deadlydiamond98.koalalib.util.registry_tools.MultiModClientRegistries;
import net.deadlydiamond98.koalalib.util.registry_tools.MultiModRegistries;
import net.minecraft.client.renderer.RenderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KoalaLib {

    public static final String MOD_ID = "koalalib";
    public static final String MOD_NAME = "Koala Lib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {

        MultiModRegistries.push();

        KoalaLibItems.register();
        KoalaLibBlocks.register();
        KoalaLibBlockEntities.register();
        KoalaLibCreativeTabs.register();

        MultiModRegistries.pop();

        MultiModClientRegistries.registerBlockRenderLayer(KoalaLibBlocks.TEST, RenderType.cutout());
        MultiModClientRegistries.registerBlockEntityRenderer(KoalaLibBlockEntities.TEST_B_ENTITY, TestBlockEntityRenderer::new);

        LOGGER.info("Finished Loading " + MOD_NAME);
    }
}