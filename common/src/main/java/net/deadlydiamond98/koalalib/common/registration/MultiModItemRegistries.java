package net.deadlydiamond98.koalalib.common.registration;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class MultiModItemRegistries {

    /**
     * Registers an item
     * @param id The Identifier for the block and item
     * @param item The item supplier
     * @return The supplier for the registered item
     */
    public static <T extends Item> Supplier<T> registerItem(ResourceLocation id, Supplier<T> item) {
        return register(BuiltInRegistries.ITEM, id, item);
    }

    private static <T> Supplier<T> register(Registry<? super T> reg, ResourceLocation id, Supplier<T> obj) {
        return MultiModRegistries.register(reg, id, obj);
    }
}
