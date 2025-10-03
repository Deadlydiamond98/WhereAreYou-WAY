package net.deadlydiamond98.way.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class PlayerLocation {
    public final Component name;
    public final float nametagY;
    private final double x, y, z;
    private final double eyeHeight;
    public final UUID uuid;
    public final boolean show;
    public final int hex;

    public PlayerLocation(Component name, float nametagY, double x, double y, double z, double eyeHeight, UUID uuid, boolean show, int hex) {
        this.name = name;
        this.nametagY = nametagY;
        this.x = x;
        this.y = y;
        this.z = z;
        this.eyeHeight = eyeHeight;

        this.uuid = uuid;

        this.show = show;
        this.hex = hex;
    }

    public final Vec3 getEyePosition() {
        return getPosition().add(0, this.eyeHeight, 0);
    }

    public final Vec3 getPosition() {
        return new Vec3(this.x, this.y, this.z);
    }

}
