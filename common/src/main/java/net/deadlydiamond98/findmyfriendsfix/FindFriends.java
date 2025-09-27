package net.deadlydiamond98.findmyfriendsfix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindFriends {

    public static final String MOD_ID = "findmyfriendsfix";
    public static final String MOD_NAME = "Find My Friends Fix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        LOGGER.info("Finished Loading " + MOD_NAME);
    }
}