package net.deadlydiamond98.way.networking;

import io.netty.buffer.Unpooled;
import net.deadlydiamond98.way.Way;
import net.deadlydiamond98.way.common.command.WayServerCommands;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class WayForgeNetworking {

    private static int i = 0;

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Way.MOD_ID, "messages"))
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
        CHANNEL.registerMessage(
                i++,
                SendUpdateNameplateRenderS2CPacket.class,
                SendUpdateNameplateRenderS2CPacket::encode,
                SendUpdateNameplateRenderS2CPacket::decode,
                SendUpdateNameplateRenderS2CPacket::handle
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

        buf.writeUUID(player.getUUID());

        buf.writeBoolean(((IWayPlayer) player).way$showPlayer());
        buf.writeInt(((IWayPlayer) player).way$getColor());

        buf.writeInt(player.hurtTime);
        buf.writeFloat(player.getHealth());
        buf.writeFloat(player.getMaxHealth());

        buf.writeBoolean(WayServerCommands.FORCE_OPT.getValue(sender) || ((IWayPlayer) player).way$showPlayer());

        CHANNEL.send(PacketDistributor.PLAYER.with(() -> sender), new SendUpdatePlayerS2CPacket(buf));
    }

    public static void sendRenderValues(ServerPlayer sender, boolean toggle, boolean names, boolean distance, boolean colors, boolean outlines, boolean head, boolean headOutline,
                                        boolean colordistance, boolean namePainFlash, boolean namePainGetRedder, int minRender, int maxRender, boolean bypassOpt) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());

        buf.writeBoolean(toggle);
        buf.writeBoolean(names);
        buf.writeBoolean(distance);
        buf.writeBoolean(colors);
        buf.writeBoolean(outlines);

        buf.writeBoolean(head);
        buf.writeBoolean(headOutline);

        buf.writeBoolean(colordistance);
        buf.writeBoolean(namePainFlash);
        buf.writeBoolean(namePainGetRedder);

        buf.writeInt(minRender);
        buf.writeInt(maxRender);

        buf.writeBoolean(bypassOpt);

        CHANNEL.send(PacketDistributor.PLAYER.with(() -> sender), new SendUpdateNameplateRenderS2CPacket(buf));
    }
}
