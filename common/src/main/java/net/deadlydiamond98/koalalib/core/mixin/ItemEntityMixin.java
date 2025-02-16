package net.deadlydiamond98.koalalib.core.mixin;

import net.deadlydiamond98.koalalib.api.items.properties.Glowing;
import net.deadlydiamond98.koalalib.api.items.properties.NoGravity;
import net.deadlydiamond98.koalalib.api.items.properties.PickupSound;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Shadow
    @Nullable
    public abstract Entity getOwner();

    // FLOATING ITEM

    @Inject(at = @At("HEAD"), method = "tick()V")
    private void tick(CallbackInfo info) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        if (itemEntity.getItem().getItem() instanceof NoGravity) {
            itemEntity.setNoGravity(true);
        }
        if (itemEntity.getItem().getItem() instanceof Glowing) {
            itemEntity.setGlowingTag(true);
        }
    }

    // SOUND ITEM STUFFS

    @Unique
    private static final Map<Player, Long> lastPickupTimeMap = new ConcurrentHashMap<>();

    @Inject(
            method = "playerTouch",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;onItemPickup(Lnet/minecraft/world/entity/item/ItemEntity;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void onEmeraldItemPickup(Player player, CallbackInfo info) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;

        if (!player.level().isClientSide()) {

            if (itemEntity.getItem().getItem() instanceof PickupSound item) {

                long currentTime = System.currentTimeMillis();
                long lastPickupTime = lastPickupTimeMap.getOrDefault(player, 0L);

                if (currentTime - lastPickupTime > 500) {

                    player.level().playSound(
                            null, player.getOnPos(), item.getSound(), SoundSource.PLAYERS,
                            item.getVolume(), item.getPitch()
                    );

                    lastPickupTimeMap.put(player, currentTime);
                }
            }
        }
    }
}
