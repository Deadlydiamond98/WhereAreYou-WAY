package net.deadlydiamond98.way.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.deadlydiamond98.way.client.WayKeybindings;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class EntityMixin {

//    @ModifyReturnValue(method = "getTeamColor", at = @At("RETURN"))
//    private int way$getTeamColor(int original) {
//        Entity entity = (Entity) (Object) this;
//        if (WayKeybindings.renderNameOverlay() && entity instanceof IWayPlayer player && player.way$canSeeOutline()) {
//            return player.way$getColor();
//        }
//        return original;
//    }
//
//    @ModifyReturnValue(method = "isCurrentlyGlowing", at = @At("RETURN"))
//    private boolean way$isCurrentlyGlowing(boolean original) {
//        Entity entity = (Entity) (Object) this;
//        if (WayKeybindings.renderNameOverlay() && entity instanceof IWayPlayer player && player.way$canSeeOutline()) {
//            return true;
//        }
//        return original;
//    }

}
