package net.deadlydiamond98.koalalib.items.interfaces;

import net.minecraft.sounds.SoundEvent;

/**
 * Any Item that implements this will play a sound when it's picked up. By default, the sound pitch and volume is 1
 */

public interface PickupSound {

    SoundEvent getSound();

    default int getVolume() {
        return 1;
    }
    default int getPitch() {
        return 1;
    }
}