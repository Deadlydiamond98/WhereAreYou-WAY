package net.deadlydiamond98.koalalib;

import net.deadlydiamond98.koalalib.testing.KoalaLibBlockEntities;
import net.deadlydiamond98.koalalib.testing.KoalaLibBlocks;
import net.deadlydiamond98.koalalib.testing.KoalaLibItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KoalaLib {

    public static final String MOD_ID = "koalalib";
    public static final String MOD_NAME = "Koala Lib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        KoalaLibItems.register();
        KoalaLibBlocks.register();
        KoalaLibBlockEntities.register();

        LOGGER.info("Finished Loading " + MOD_NAME);
    }
}