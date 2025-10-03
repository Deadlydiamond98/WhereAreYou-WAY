package net.deadlydiamond98.way.mixin;

import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Player.class)
public class PlayerMixin implements IWayPlayer {

    @Unique private boolean way$showPlayer = true;
    @Unique private int way$color = 0xFFFFFF;
    @Unique private List<Component> way$players;
    @Unique @Nullable private Integer way$focusedColor;

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void way$readAdditionalSaveData(CompoundTag nbt, CallbackInfo ci) {
        this.way$showPlayer = nbt.getBoolean("showPlayerWAY");
        this.way$color = nbt.getInt("colorWAY");
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void way$addAdditionalSaveData(CompoundTag nbt, CallbackInfo ci) {
        nbt.putBoolean("showPlayerWAY", way$showPlayer);
        nbt.putInt("colorWAY", way$color);
    }

    @Override
    public void way$setShowing(boolean show) {
        this.way$showPlayer = show;
    }

    @Override
    public boolean way$showPlayer() {
        return this.way$showPlayer;
    }

    @Override
    public void way$setColor(int hex) {
        this.way$color = hex;
    }

    @Override
    public int way$getColor() {
        return this.way$color;
    }

    @Override
    public void way$setFocusedPlayerNames(List<Component> players) {
        this.way$players = players;
    }

    @Override
    public List<Component> way$getFocusedPlayerNames() {
        return this.way$players;
    }

    @Override
    public void way$setFocusedColor(@Nullable Integer color) {
        this.way$focusedColor = color;
    }

    @Override
    public @Nullable Integer way$getFocusedColor() {
        return this.way$focusedColor;
    }
}
