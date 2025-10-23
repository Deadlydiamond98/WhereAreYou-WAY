package net.deadlydiamond98.way.util.mixin;

public interface IGlowingWayPlayer {
    void way$setGlowRendering(boolean bl);
    boolean way$isGlowRendering();

    void way$setOutlineColor(int color);
    int way$getOutlineColor();
}
