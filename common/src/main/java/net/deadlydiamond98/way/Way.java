package net.deadlydiamond98.way;

import net.deadlydiamond98.way.common.command.WayCommandArgTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Way {

    public static boolean namePainFlash = true;
    public static boolean namePainGetRedder = false;
    public static boolean colorDistance = false;
    public static int minRender = 0;
    public static int maxRender = 999999;

    public static final String MOD_ID = "way";
    public static final String MOD_NAME = "Where Are You (WAY)";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        WayCommandArgTypes.register();
        LOGGER.info("Finished Loading " + MOD_NAME);
    }
}