package net.deadlydiamond98.findmyfriendsfix;

import net.deadlydiamond98.findmyfriendsfix.event.FindFriendsTickingEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class FindFriendsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        FindFriends.init();
        ServerTickEvents.END_SERVER_TICK.register(server -> server.getAllLevels().forEach(FindFriendsTickingEvent::tick));
    }
}
