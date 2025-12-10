package net.deadlydiamond98.way.common.events;

import net.deadlydiamond98.way.common.command.WayServerCommands;
import net.deadlydiamond98.way.platform.Service;
import net.deadlydiamond98.way.util.PlayerLocation;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class WayTickingEvent {
    public static final List<PlayerLocation> PLAYER_POS = new ArrayList<>();
    private static final int UPDATE_RATE = 5;

    public static void tick(Level level) {
        List<Player> toRender = new ArrayList<>();
        level.players().forEach(player -> {
            if (!player.isInvisible()) {
                toRender.add(player);
            }
        });
        level.players().forEach(sender -> {
            int rate = Math.max(1, WayServerCommands.PACKET_UPDATE_RATE.getValue(sender));
            if (sender.tickCount % rate == 0) {
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
        boolean inRange = distance >= WayServerCommands.MIN_DIST.getValue(sender) && distance <= WayServerCommands.MAX_DIST.getValue(sender);

        if ((sender == player && !iWaySender.way$canSeeSelf()) || (!inRange && sender != player)) {
            return false;
        }

        boolean focus = focusColor != null;

        if (WayServerCommands.SEE_TEAM_ONLY.getValue(sender)) {
            if (iWaySender.way$isClear() && iWayPlayer.way$isClear()) {
                return canRender(sender, player, iWaySender, true);
            }
            return iWayPlayer.way$getColor() == iWaySender.way$getColor();
        } else if (focus) {
            if (iWaySender.way$isClear() && iWayPlayer.way$isClear()) {
                return canRender(sender, player, iWaySender, true);
            }
            return canRender(sender, player, iWaySender, focusColor == iWayPlayer.way$getColor());
        }
        if (!targets.isEmpty()) {
            return canRender(sender, player, iWaySender, targets.contains(player.getName()));
        }

        return canRender(sender, player, iWaySender, true);
    }

    private static boolean canRender(Player sender, Player player, IWayPlayer iWaySender, boolean returnVal) {
        if (sender.distanceTo(player) < 100 && (sender != player) && iWaySender.way$hideIfVisible()) {
            ClipContext context = new ClipContext(sender.getEyePosition(), player.getEyePosition(), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);
            BlockHitResult hitResult = sender.level().clip(context);
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                return returnVal;
            }
            return false;
        }
        return returnVal;
    }
}
