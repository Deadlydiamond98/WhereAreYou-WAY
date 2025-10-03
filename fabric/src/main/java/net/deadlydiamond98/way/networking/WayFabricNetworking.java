package net.deadlydiamond98.way.networking;

import net.deadlydiamond98.way.Way;
import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.deadlydiamond98.way.util.PlayerLocation;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class WayFabricNetworking {
    public static final ResourceLocation UPDATE_PLAYER_PACKET = new ResourceLocation(Way.MOD_ID, "update_players");
    public static final ResourceLocation CLEAR_PLAYERS_PACKET = new ResourceLocation(Way.MOD_ID, "clear_players");

    public static class Client {
        public static void registerS2CPackets() {
            ClientPlayNetworking.registerGlobalReceiver(UPDATE_PLAYER_PACKET, WayFabricNetworking.Client::recievePlayerList);
            ClientPlayNetworking.registerGlobalReceiver(CLEAR_PLAYERS_PACKET, WayFabricNetworking.Client::recieveClearPlayers);
        }

        private static void recieveClearPlayers(Minecraft minecraft, ClientPacketListener clientPacketListener, FriendlyByteBuf buf, PacketSender packetSender) {
            WayTickingEvent.PLAYER_POS.clear();
        }

        private static void recievePlayerList(Minecraft minecraft, ClientPacketListener clientPacketListener, FriendlyByteBuf buf, PacketSender packetSender) {
            WayTickingEvent.PLAYER_POS.add(
                    new PlayerLocation(
                            buf.readComponent(), buf.readFloat(),
                            buf.readDouble(), buf.readDouble(), buf.readDouble(),
                            buf.readDouble(),
                            buf.readBoolean(), buf.readInt()
            ));
        }
    }

    public static class Server {

        public static void sendClearPlayers(ServerPlayer sender) {
            FriendlyByteBuf buf = PacketByteBufs.create();
            ServerPlayNetworking.send(sender, CLEAR_PLAYERS_PACKET, buf);
        }

        public static void sendPlayerList(ServerPlayer sender, Player player) {
            FriendlyByteBuf buf = PacketByteBufs.create();

            buf.writeComponent(player.getName());
            buf.writeFloat(player.getNameTagOffsetY());
            buf.writeDouble(player.getX());
            buf.writeDouble(player.getY());
            buf.writeDouble(player.getZ());
            buf.writeDouble(player.getEyeHeight());

            buf.writeBoolean(((IWayPlayer) player).way$showPlayer());
            buf.writeInt(((IWayPlayer) player).way$getColor());

            ServerPlayNetworking.send(sender, UPDATE_PLAYER_PACKET, buf);
        }
    }
}
