package net.deadlydiamond98.findmyfriendsfix.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Supplier;

public interface PlatformHelper {

    <T> Supplier<T> register(Registry<? super T> reg, ResourceLocation id, Supplier<T> obj);

    <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(ResourceLocation id, BlockEntityFactory<T> factory, Supplier<Block[]> blocks);

    Supplier<CreativeModeTab> registerCreativeTab(ResourceLocation id, Supplier<ItemStack> icon, String translation, Supplier<Item[]> items);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    void sendS2CPlayerList(ServerPlayer sender, Player player);

    void sendClearPacket(ServerPlayer sender);

    @FunctionalInterface
    interface BlockEntityFactory<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state);
    }
}