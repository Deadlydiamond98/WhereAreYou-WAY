package net.deadlydiamond98.findmyfriendsfix.platform;

import net.deadlydiamond98.findmyfriendsfix.FindFriends;

import java.util.ServiceLoader;

public class Service {

    public static final PlatformHelper PLATFORM = load(PlatformHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        FindFriends.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}