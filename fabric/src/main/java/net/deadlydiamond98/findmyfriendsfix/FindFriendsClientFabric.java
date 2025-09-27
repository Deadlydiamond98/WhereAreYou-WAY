package net.deadlydiamond98.findmyfriendsfix;

import net.deadlydiamond98.findmyfriendsfix.event.FindFriendsNameplateRenderEvent;
import net.deadlydiamond98.findmyfriendsfix.networking.FindFriendsFabricNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class FindFriendsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(FindFriendsKeybindings::tickKeybinding);
        KeyBindingHelper.registerKeyBinding(FindFriendsKeybindings.TOGGLE_NAMEPLATE);
        FindFriendsFabricNetworking.Client.registerS2CPackets();
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            FindFriendsNameplateRenderEvent.render(context.matrixStack(), context.consumers(), context.world(), context.tickDelta());
        });
    }
}
