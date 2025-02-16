package net.deadlydiamond98.koalalib;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class KoalaLibFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        KoalaLib.init();
    }
}
