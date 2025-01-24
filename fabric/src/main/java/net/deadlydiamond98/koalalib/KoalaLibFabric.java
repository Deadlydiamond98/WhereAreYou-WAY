package net.deadlydiamond98.koalalib;

import net.fabricmc.api.ModInitializer;

public class KoalaLibFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        KoalaLib.init();
    }
}
