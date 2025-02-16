package net.deadlydiamond98.koalalib.testing;

import net.deadlydiamond98.koalalib.KoalaLib;
import net.deadlydiamond98.koalalib.api.registration.MultiModRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class KoalaLibCreativeTabs {
    public static final Supplier<CreativeModeTab> TEST_TAB = MultiModRegistries.registerCreativeTab(new ResourceLocation(KoalaLib.MOD_ID, "test_tab"),
            () -> Blocks.AMETHYST_BLOCK.asItem().getDefaultInstance(), "test.test.test", () -> Items.AMETHYST_BLOCK);

    public static void register() {
    }
}
