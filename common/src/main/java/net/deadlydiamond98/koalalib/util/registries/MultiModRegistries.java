package net.deadlydiamond98.koalalib.util.registries;

import net.deadlydiamond98.koalalib.util.registries.services.KoalaPlatformHelper;
import net.deadlydiamond98.koalalib.util.registries.services.Services;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Arrays;
import java.util.function.Supplier;

public class MultiModRegistries {

    /**
     * Registers an item
     * @param id The Identifier for the block and item
     * @param item The item supplier
     * @return The supplier for the registered item
     */
    public static <T extends Item> Supplier<T> registerItem(ResourceLocation id, Supplier<T> item) {
        return register(BuiltInRegistries.ITEM, id, item);
    }

    /**
     * Registers a block with an item
     * @param id The Identifier for the block and item
     * @param block The block supplier
     * @return The supplier for the registered block
     */
    public static <T extends Block> Supplier<T> registerBlock(ResourceLocation id, Supplier<T> block) {
        Supplier<T> registeredBlock = registerBlockNoItem(id, block);
        registerItem(id, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }

    /**
     * Registers a block without an item
     * @param id The Identifier for the block
     * @param blockNoItem The block supplier
     * @return The supplier for the registered block
     */
    public static <T extends Block> Supplier<T> registerBlockNoItem(ResourceLocation id, Supplier<T> blockNoItem) {
        return register(BuiltInRegistries.BLOCK, id, blockNoItem);
    }

    /**
     * Registers a block entity type
     * @param id The Identifier for the block entity
     * @param factory The instance of the block entity
     * @param blocks All the blocks that the block entity is associated with
     * @return
     */
    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(
            ResourceLocation id, KoalaPlatformHelper.BlockEntityFactory<T> factory, Supplier<Block>... blocks) {
        return Services.PLATFORM.registerBlockEntity(id, factory, () -> Arrays.stream(blocks).map(Supplier::get).toArray(Block[]::new));
    }

    /**
     * Default Registraion Method, runs on both Fabric and Forge
     * @param reg Where the object will be registered
     * @param id The Identifier for the block
     * @param obj The supplier
     * @return The supplier for the registered block
     */
    public static <T> Supplier<T> register(Registry<? super T> reg, ResourceLocation id, Supplier<T> obj) {
        return Services.PLATFORM.register(reg, id, obj);
    }
}
