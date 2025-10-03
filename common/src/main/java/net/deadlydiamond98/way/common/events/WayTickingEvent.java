package net.deadlydiamond98.way.common.events;

import net.deadlydiamond98.way.platform.Service;
import net.deadlydiamond98.way.util.PlayerLocation;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class WayTickingEvent {
    public static final List<PlayerLocation> PLAYER_POS = new ArrayList<>();
    private static final int UPDATE_RATE = 5;

    public static void tick(Level level) {
        List<Player> toRender = new ArrayList<>();
        level.players().forEach(player -> {
            if (!player.isDiscrete()) {
                toRender.add(player);
            }
        });
        level.players().forEach(sender -> {
            if (sender.tickCount % UPDATE_RATE == 0) {
                Service.PLATFORM.sendS2CClearPacket((ServerPlayer) sender);
                for (Player player : toRender) {
                    if (canRenderNameplate(sender, player)) {
                        Service.PLATFORM.sendS2CPlayerList((ServerPlayer) sender, player);
                    }
                }
            }
        });
    }

    private static boolean canRenderNameplate(Player sender, Player player) {
        IWayPlayer iWaySender = (IWayPlayer) sender;
        IWayPlayer iWayPlayer = (IWayPlayer) player;

        List<Component> targets = iWaySender.way$getFocusedPlayerNames();
        Integer focusColor = iWaySender.way$getFocusedColor();

        double distance = sender.position().distanceTo(player.position());
        boolean inRange = distance >= iWaySender.way$getMinRender() && distance <= iWaySender.way$getMaxRender();

        if ((sender == player && !iWaySender.way$canSeeSelf()) || !inRange) {
            return false;
        }

        if (focusColor != null) {
            return focusColor == iWayPlayer.way$getColor();
        }
        else if (!targets.isEmpty()) {
            return targets.contains(player.getName());
        }

        return iWayPlayer.way$showPlayer();
    }
}
