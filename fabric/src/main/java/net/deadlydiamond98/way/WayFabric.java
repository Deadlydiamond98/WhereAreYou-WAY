package net.deadlydiamond98.way;

import net.deadlydiamond98.way.common.command.WayServerCommands;
import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class WayFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Way.init();
        ServerTickEvents.END_SERVER_TICK.register(server -> server.getAllLevels().forEach(WayTickingEvent::tick));
        CommandRegistrationCallback.EVENT.register(WayServerCommands::register);
    }
}
