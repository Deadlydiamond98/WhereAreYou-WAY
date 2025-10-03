package net.deadlydiamond98.way;

import net.deadlydiamond98.way.common.command.WayCommandArgTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Scanner;

public class Way {

    public static final String MOD_ID = "way";
    public static final String MOD_NAME = "Where Are You (WAY)";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        WayCommandArgTypes.register();
        LOGGER.info("Finished Loading " + MOD_NAME);
    }
}