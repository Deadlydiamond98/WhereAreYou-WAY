package net.deadlydiamond98.way;

import net.deadlydiamond98.way.common.command.WayServerCommands;
import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.deadlydiamond98.way.networking.WayFabricNetworking;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.world.entity.player.Player;

public class WayFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Way.init();
        ServerTickEvents.END_SERVER_TICK.register(server -> server.getAllLevels().forEach(WayTickingEvent::tick));
        CommandRegistrationCallback.EVENT.register(WayServerCommands::register);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> ((IWayPlayer) sender).way$updateRenderPreferences());
    }
}
