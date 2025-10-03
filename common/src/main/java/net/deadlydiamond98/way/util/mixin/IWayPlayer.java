package net.deadlydiamond98.way.util.mixin;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IWayPlayer {
    void way$setShowing(boolean show);
    boolean way$showPlayer();

    void way$setColor(int hex);
    int way$getColor();

    void way$setFocusedPlayerNames(List<Component> players);
    List<Component> way$getFocusedPlayerNames();

    void way$setFocusedColor(@Nullable Integer color);
    @Nullable Integer way$getFocusedColor();
}
