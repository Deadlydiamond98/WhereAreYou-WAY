package net.deadlydiamond98.findmyfriendsfix.networking;

import net.deadlydiamond98.findmyfriendsfix.event.FindFriendsTickingEvent;
import net.deadlydiamond98.findmyfriendsfix.util.PlayerLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendUpdatePlayerS2CPacket {
    private final FriendlyByteBuf buffer;

    public SendUpdatePlayerS2CPacket(FriendlyByteBuf buffer) {
        this.buffer = buffer;
    }

    public static void encode(SendUpdatePlayerS2CPacket packet, FriendlyByteBuf buf) {
        buf.writeBytes(packet.buffer);
    }

    public static SendUpdatePlayerS2CPacket decode(FriendlyByteBuf buf) {
        return new SendUpdatePlayerS2CPacket(buf);
    }

    public static void handle(SendUpdatePlayerS2CPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                FriendlyByteBuf buf = packet.buffer;
                FindFriendsTickingEvent.PLAYER_POS.add(new PlayerLocation(
                        buf.readComponent(), buf.readFloat(),
                        buf.readDouble(), buf.readDouble(), buf.readDouble(),
                        buf.readDouble()
                ));
            }
        });
        context.setPacketHandled(true);
    }
}
