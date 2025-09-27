package net.deadlydiamond98.findmyfriendsfix.platform;

import com.mojang.blaze3d.platform.InputConstants;
import net.deadlydiamond98.findmyfriendsfix.networking.FindFriendsFabricNetworking;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.InputType;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;
import java.util.function.Supplier;

public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public <T> Supplier<T> register(Registry<? super T> reg, ResourceLocation id, Supplier<T> obj) {
        T object = Registry.register(reg, id, obj.get());
        return () -> object;
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(ResourceLocation id, BlockEntityFactory<T> factory, Supplier<Block[]> blocks) {
        return register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, () -> FabricBlockEntityTypeBuilder.create(
                (pos, state) -> factory.create(pos, state), blocks.get()
        ).build(null));
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeTab(ResourceLocation id, Supplier<ItemStack> icon, String translation, Supplier<Item[]> items) {
        return register(BuiltInRegistries.CREATIVE_MODE_TAB, id, () -> FabricItemGroup.builder().icon(icon)
                .title(Component.translatable(translation)).displayItems((itemDisplayParameters, output) -> {
                    for (Item item : items.get()) {
                        output.accept(item);
                    }
                }).build());
    }

    @Override
    public void sendS2CPlayerList(ServerPlayer sender, Player player) {
        FindFriendsFabricNetworking.Server.sendPlayerList(sender, player);
    }

    @Override
    public void sendClearPacket(ServerPlayer sender) {
        FindFriendsFabricNetworking.Server.sendClearPlayers(sender);
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
