package net.deadlydiamond98.way.networking;

import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendClearPlayersS2CPacket {
    private final FriendlyByteBuf buffer;

    public SendClearPlayersS2CPacket(FriendlyByteBuf buffer) {
        this.buffer = buffer;
    }

    public static void encode(SendClearPlayersS2CPacket packet, FriendlyByteBuf buf) {
        buf.writeBytes(packet.buffer);
    }

    public static SendClearPlayersS2CPacket decode(FriendlyByteBuf buf) {
        return new SendClearPlayersS2CPacket(buf);
    }

    public static void handle(SendClearPlayersS2CPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                WayTickingEvent.PLAYER_POS.clear();
            }
        });
        context.setPacketHandled(true);
    }
}
