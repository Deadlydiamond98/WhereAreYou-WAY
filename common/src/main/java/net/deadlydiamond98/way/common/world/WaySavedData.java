package net.deadlydiamond98.way.common.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class WaySavedData extends SavedData {

    private boolean colorDistance = false;
    private boolean namePain = true;
    private boolean forceOptIn = false;
    private boolean lockColor = false;
    private boolean seeTeamColorOnly = false;
    private boolean seeAll = false;
    private boolean teamColourNoFriendlyFire = false;

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putBoolean("colorDistanceWay", this.colorDistance);
        nbt.putBoolean("namePainWay", this.namePain);
        nbt.putBoolean("forceOptInWay", this.forceOptIn);
        nbt.putBoolean("lockColorWay", this.lockColor);
        nbt.putBoolean("seeTeamColorOnlyWay", this.seeTeamColorOnly);
        nbt.putBoolean("seeAllWay", this.seeAll);
        nbt.putBoolean("teamColourNoFriendlyFireWay", this.teamColourNoFriendlyFire);
        return nbt;
    }

    public static WaySavedData fromNbt(CompoundTag nbt) {
        WaySavedData data = new WaySavedData();
        data.colorDistance = nbt.getBoolean("colorDistanceWay");
        data.namePain = nbt.getBoolean("namePainWay");
        data.forceOptIn = nbt.getBoolean("forceOptInWay");
        data.lockColor = nbt.getBoolean("lockColorWay");
        data.seeTeamColorOnly = nbt.getBoolean("seeTeamColorOnlyWay");
        data.seeAll = nbt.getBoolean("seeAllWay");
        data.teamColourNoFriendlyFire = nbt.getBoolean("teamColourNoFriendlyFireWay");
        return data;
    }

    // GETTERS

    public boolean colorDistance() {
        return this.colorDistance;
    }

    public boolean namePain() {
        return this.namePain;
    }

    public boolean forceOptIn() {
        return this.forceOptIn;
    }

    public boolean lockColor() {
        return this.lockColor;
    }

    public boolean seeTeamColorOnly() {
        return this.seeTeamColorOnly;
    }

    public boolean seeAll() {
        return this.seeAll;
    }

    public boolean teamColourNoFriendlyFire() {
        return this.teamColourNoFriendlyFire;
    }

    // SETTERS

    public void setColorDistance(boolean colorDistance) {
        this.colorDistance = colorDistance;
        this.setDirty();
    }

    public void setNamePain(boolean namePain) {
        this.namePain = namePain;
        this.setDirty();
    }

    public void setForceOptIn(boolean forceOptIn) {
        this.forceOptIn = forceOptIn;
        this.setDirty();
    }

    public void setLockColor(boolean lockColor) {
        this.lockColor = lockColor;
        this.setDirty();
    }

    public void setSeeTeamColorOnly(boolean seeTeamColorOnly) {
        this.seeTeamColorOnly = seeTeamColorOnly;
        this.setDirty();
    }

    public void setSeeAll(boolean seeAll) {
        this.seeAll = seeAll;
        this.setDirty();
    }

    public void setTeamColourNoFriendlyFire(boolean teamColourNoFriendlyFire) {
        this.teamColourNoFriendlyFire = teamColourNoFriendlyFire;
        this.setDirty();
    }
}
