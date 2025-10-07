package net.deadlydiamond98.way.platform;

import com.mojang.brigadier.arguments.ArgumentType;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.deadlydiamond98.way.Way;
import net.deadlydiamond98.way.networking.WayForgeNetworking;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Supplier;

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
    public void sendS2CRenderingPacket(ServerPlayer sender, boolean toggle, boolean names, boolean distance, boolean colors, boolean outlines, boolean colordistance, boolean namePain) {
        WayForgeNetworking.sendRenderValues(sender, toggle, names, distance, colors, outlines, colordistance, namePain);
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