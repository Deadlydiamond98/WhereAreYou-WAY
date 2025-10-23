package net.deadlydiamond98.way.platform;

import com.mojang.brigadier.arguments.ArgumentType;
import net.deadlydiamond98.way.Way;
import net.deadlydiamond98.way.networking.WayFabricNetworking;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>, I extends ArgumentTypeInfo<A, T>> void registerArgType(String id, Class<A> infoClass, I argumentTypeInfo) {
        ArgumentTypeRegistry.registerArgumentType(new ResourceLocation(Way.MOD_ID, id), infoClass, argumentTypeInfo);
    }

    @Override
    public void sendS2CPlayerList(ServerPlayer sender, Player player) {
        WayFabricNetworking.Server.sendPlayerList(sender, player);
    }

    @Override
    public void sendS2CClearPacket(ServerPlayer sender) {
        WayFabricNetworking.Server.sendClearPlayers(sender);
    }

    @Override
    public void sendS2CRenderingPacket(ServerPlayer sender, boolean toggle, boolean names, boolean distance, boolean colors, boolean outlines, boolean head, boolean headOutline,
                                       boolean colordistance, boolean namePainFlash, boolean namePainGetRedder, int minRender, int maxRender) {
        WayFabricNetworking.Server.sendRenderValues(sender, toggle, names, distance, colors, outlines, head, headOutline,
                colordistance, namePainFlash, namePainGetRedder, minRender, maxRender);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
