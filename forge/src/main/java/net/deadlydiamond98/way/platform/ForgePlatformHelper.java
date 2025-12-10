package net.deadlydiamond98.way.platform;

import com.mojang.brigadier.arguments.ArgumentType;
import net.deadlydiamond98.way.Way;
import net.deadlydiamond98.way.networking.WayForgeNetworking;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ForgePlatformHelper implements PlatformHelper {

    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> COMMAND_ARGUMENT_TYPES = DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, Way.MOD_ID);

    @Override
    public <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>, I extends ArgumentTypeInfo<A, T>> void registerArgType(String id, Class<A> infoClass, I argumentTypeInfo) {
        COMMAND_ARGUMENT_TYPES.register(id, () -> ArgumentTypeInfos.registerByClass(infoClass, argumentTypeInfo));
    }

    public static void registerArgTypes(IEventBus modEventBus) {
        COMMAND_ARGUMENT_TYPES.register(modEventBus);
    }

    @Override
    public void sendS2CPlayerList(ServerPlayer sender, Player player) {
        WayForgeNetworking.sendPlayerList(sender, player);
    }

    @Override
    public void sendS2CClearPacket(ServerPlayer sender) {
        WayForgeNetworking.sendClearPlayers(sender);
    }

    @Override
    public void sendS2CRenderingPacket(ServerPlayer sender, boolean toggle, boolean names, boolean distance, boolean colors, boolean outlines, boolean head, boolean headOutline,
                                       boolean colordistance, boolean namePainFlash, boolean namePainGetRedder, int minRender, int maxRender, boolean bypassOpt) {
        WayForgeNetworking.sendRenderValues(sender, toggle, names, distance, colors, outlines, head, headOutline,
                colordistance, namePainFlash, namePainGetRedder, minRender, maxRender, bypassOpt);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }
}