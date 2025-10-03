package net.deadlydiamond98.way.common.events;

import net.deadlydiamond98.way.platform.Service;
import net.deadlydiamond98.way.util.PlayerLocation;
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
                Service.PLATFORM.sendClearPacket((ServerPlayer) sender);
                for (Player player : toRender) {
                    if (sender != player) {
                        Service.PLATFORM.sendS2CPlayerList((ServerPlayer) sender, player);
                    }
                }
            }
        });
    }
}
