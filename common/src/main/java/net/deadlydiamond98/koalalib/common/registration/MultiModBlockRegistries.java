package net.deadlydiamond98.koalalib.common.registration;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class MultiModBlockRegistries {
    /**
     * Registers a block with an item
     * @param id The Identifier for the block and item
     * @param block The block supplier
     * @return The supplier for the registered block
     */
    public static <T extends Block> Supplier<T> registerBlock(ResourceLocation id, Supplier<T> block) {
        Supplier<T> registeredBlock = registerBlockNoItem(id, block);
        MultiModItemRegistries.registerItem(id, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
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
     * Default registration Method, runs on both Fabric and Forge
     * @param reg Where the object will be registered
     * @param id The Identifier for the object
     * @param obj The supplier
     * @return The supplier for the registered object
     */
    private static <T> Supplier<T> register(Registry<? super T> reg, ResourceLocation id, Supplier<T> obj) {
        return MultiModRegistries.register(reg, id, obj);
    }
}
