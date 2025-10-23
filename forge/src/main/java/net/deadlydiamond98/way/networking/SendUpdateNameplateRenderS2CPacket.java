package net.deadlydiamond98.way.networking;

import net.deadlydiamond98.way.Way;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendUpdateNameplateRenderS2CPacket {
    private final FriendlyByteBuf buffer;

    public SendUpdateNameplateRenderS2CPacket(FriendlyByteBuf buffer) {
        this.buffer = buffer;
    }

    public static void encode(SendUpdateNameplateRenderS2CPacket packet, FriendlyByteBuf buf) {
        buf.writeBytes(packet.buffer);
    }

    public static SendUpdateNameplateRenderS2CPacket decode(FriendlyByteBuf buf) {
        return new SendUpdateNameplateRenderS2CPacket(buf);
    }

    public static void handle(SendUpdateNameplateRenderS2CPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                FriendlyByteBuf buf = packet.buffer;

                Minecraft minecraft = Minecraft.getInstance();

                if (minecraft.player != null) {
                    IWayPlayer player = ((IWayPlayer) minecraft.player);

                    player.way$setToggle(buf.readBoolean());
                    player.way$setSeeName(buf.readBoolean());
                    player.way$setSeeDist(buf.readBoolean());
                    player.way$setSeeColor(buf.readBoolean());
                    player.way$setSeeOutline(buf.readBoolean());
                    player.way$setSeeHead(buf.readBoolean());
                    player.way$setSeeHeadOutline(buf.readBoolean());

                    Way.colorDistance = buf.readBoolean();
                    Way.namePainFlash = buf.readBoolean();
                    Way.namePainGetRedder = buf.readBoolean();

                    Way.minRender = buf.readInt();
                    Way.maxRender = buf.readInt();
                }
            }
        });
        context.setPacketHandled(true);
    }
}
