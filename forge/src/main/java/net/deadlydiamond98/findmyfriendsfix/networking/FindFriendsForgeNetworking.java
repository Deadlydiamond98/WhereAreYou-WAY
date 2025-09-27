package net.deadlydiamond98.findmyfriendsfix.networking;

import io.netty.buffer.Unpooled;
import net.deadlydiamond98.findmyfriendsfix.FindFriends;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class FindFriendsForgeNetworking {

    private static int i = 0;

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(FindFriends.MOD_ID, "messages"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    public static void register() {
        CHANNEL.registerMessage(
                i++,
                SendUpdatePlayerS2CPacket.class,
                SendUpdatePlayerS2CPacket::encode,
                SendUpdatePlayerS2CPacket::decode,
                SendUpdatePlayerS2CPacket::handle
        );
        CHANNEL.registerMessage(
                i++,
                SendClearPlayersS2CPacket.class,
                SendClearPlayersS2CPacket::encode,
                SendClearPlayersS2CPacket::decode,
                SendClearPlayersS2CPacket::handle
        );
    }

    public static void sendClearPlayers(ServerPlayer sender) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> sender), new SendClearPlayersS2CPacket(buf));
    }

    public static void sendPlayerList(ServerPlayer sender, Player player) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeComponent(player.getName());
        buf.writeFloat(player.getNameTagOffsetY());

        buf.writeDouble(player.getX());
        buf.writeDouble(player.getY());
        buf.writeDouble(player.getZ());

        buf.writeDouble(player.getEyeHeight());
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> sender), new SendUpdatePlayerS2CPacket(buf));
    }
}
