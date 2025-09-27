package net.deadlydiamond98.findmyfriendsfix.platform;

import com.mojang.blaze3d.platform.InputConstants;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.deadlydiamond98.findmyfriendsfix.networking.FindFriendsForgeNetworking;
import net.minecraft.client.KeyMapping;
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

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ForgePlatformHelper implements PlatformHelper {

    private static final Map<ResourceKey<?>, DeferredRegister> DEFERRED_REGISTERIES = new Reference2ObjectOpenHashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> Supplier<T> register(Registry<? super T> reg, ResourceLocation id, Supplier<T> obj) {
        return DEFERRED_REGISTERIES.computeIfAbsent(reg.key(),
                resourceKey -> DeferredRegister.create(reg.key().location(), id.getNamespace())).register(id.getPath(), obj);
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(ResourceLocation id, BlockEntityFactory<T> factory, Supplier<Block[]> blocks) {
        return register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, () -> BlockEntityType.Builder.of((blockPos, blockState)
                -> factory.create(blockPos, blockState), blocks.get()).build(null));
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeTab(ResourceLocation id, Supplier<ItemStack> icon, String translation, Supplier<Item[]> items) {
        return register(BuiltInRegistries.CREATIVE_MODE_TAB, id, () -> CreativeModeTab.builder().icon(icon)
                .title(Component.translatable(translation)).displayItems((itemDisplayParameters, output) -> {
                    for (Item item : items.get()) {
                        output.accept(item);
                    }
                }).build());
    }

    @Override
    public void sendS2CPlayerList(ServerPlayer sender, Player player) {
        FindFriendsForgeNetworking.sendPlayerList(sender, player);
    }

    @Override
    public void sendClearPacket(ServerPlayer sender) {
        FindFriendsForgeNetworking.sendClearPlayers(sender);
    }

    public static void register(IEventBus eventBus) {
        DEFERRED_REGISTERIES.values().forEach(deferredRegister -> deferredRegister.register(eventBus));
        DEFERRED_REGISTERIES.clear();
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