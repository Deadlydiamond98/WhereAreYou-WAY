package net.deadlydiamond98.koalalib.api.registration;

import net.deadlydiamond98.koalalib.core.services.KoalaPlatformHelper;
import net.deadlydiamond98.koalalib.core.services.KoalaServices;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class MultiModRegistries {


    @SafeVarargs
    public static Supplier<CreativeModeTab> registerCreativeTab(ResourceLocation id, Supplier<ItemStack> icon, String translation, Supplier<Item>... items) {
        return KoalaServices.PLATFORM.registerCreativeTab(id, icon, translation, () -> Stream.of(items).map(Supplier::get).toArray(Item[]::new));
    }

    @SafeVarargs
    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(
            ResourceLocation id, KoalaPlatformHelper.BlockEntityFactory<T> factory, Supplier<Block>... blocks) {
        return KoalaServices.PLATFORM.registerBlockEntity(id, factory, () -> Stream.of(blocks).map(Supplier::get).toArray(Block[]::new));
    }

    public static <T extends SoundEvent> Supplier<T> registerSound(ResourceLocation id, Supplier<T> item) {
        return register(BuiltInRegistries.SOUND_EVENT, id, item);
    }


    /**
     * Default registration Method, runs on both Fabric and Forge
     * @param reg Where the object will be registered
     * @param id The Identifier for the object
     * @param obj The supplier
     * @return The supplier for the registered object
     */
    public static <T> Supplier<T> register(Registry<? super T> reg, ResourceLocation id, Supplier<T> obj) {
        return KoalaServices.PLATFORM.register(reg, id, obj);
    }


    /**
     * Make sure call this before registering your mod items, blocks, entities, etc...
     * Without running this, forge will refuse to work!!!!!
     */
    public static void push() {
        KoalaServices.PLATFORM.pushRegistries();
    }

    /**
     * Make sure you run this once you're finished registering your mod items, blocks, entities, etc...
     * Without running this, forge will refuse to work!!!!!
     */
    public static void pop() {
        KoalaServices.PLATFORM.popRegistries();
    }
}
