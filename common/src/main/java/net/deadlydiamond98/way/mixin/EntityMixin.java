package net.deadlydiamond98.way.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.deadlydiamond98.way.client.WayKeybindings;
import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.deadlydiamond98.way.util.PlayerLocation;
import net.deadlydiamond98.way.util.mixin.IGlowingWayPlayer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class EntityMixin implements IGlowingWayPlayer {

    private boolean render = false;
    private int color = 0xFFFFFFFF;

    @ModifyReturnValue(method = "getTeamColor", at = @At("RETURN"))
    private int way$getTeamColor(int original) {
        Entity entity = (Entity) (Object) this;
        if (entity.level().isClientSide) {
            if (WayKeybindings.renderNameOverlay() && way$canSeePlayer(entity) && this.render) {
                return this.color;
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "isCurrentlyGlowing", at = @At("RETURN"))
    private boolean way$isCurrentlyGlowing(boolean original) {
        Entity entity = (Entity) (Object) this;
        if (entity.level().isClientSide) {
            if (WayKeybindings.renderNameOverlay() && way$canSeePlayer(entity) && this.render) {
                return true;
            }
        }
        return original;
    }

    @Unique
    private boolean way$canSeePlayer(Entity entity) {
        if (entity instanceof Player player) {
            for (PlayerLocation playerData : WayTickingEvent.PLAYER_POS) {
                if (playerData.name.getString().equals(player.getName().getString())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void way$setGlowRendering(boolean bl) {
        this.render = bl;
    }

    @Override
    public boolean way$isGlowRendering() {
        return this.render;
    }

    @Override
    public void way$setOutlineColor(int color) {
        this.color = color;
    }

    @Override
    public int way$getOutlineColor() {
        return this.color;
    }
}
