package net.deadlydiamond98.koalalib.util.registries.services;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public interface KoalaPlatformHelper {

    <T> Supplier<T> register(Registry<? super T> reg, ResourceLocation id, Supplier<T> obj);

    <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(ResourceLocation id, BlockEntityFactory<T> factory, Supplier<Block[]> blocks);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    @FunctionalInterface
    interface BlockEntityFactory<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state);
    }
}