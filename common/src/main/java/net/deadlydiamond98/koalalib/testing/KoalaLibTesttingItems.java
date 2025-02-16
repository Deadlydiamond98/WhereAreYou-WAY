package net.deadlydiamond98.koalalib.testing;

import net.deadlydiamond98.koalalib.api.registration.MultiModItemRegistries;
import net.deadlydiamond98.koalalib.testing.objs.TestttingItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.deadlydiamond98.koalalib.KoalaLib;
import java.util.function.Supplier;

public class KoalaLibTesttingItems {

    public static final Supplier<Item> TEST = MultiModItemRegistries.registerItem(new ResourceLocation(KoalaLib.MOD_ID, "test"),
            () -> new TestttingItem(new Item.Properties()));

    public static void register() {
        KoalaLib.LOGGER.info("TEST");
    }
}
