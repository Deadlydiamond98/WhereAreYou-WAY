package net.deadlydiamond98.koalalib.testing;

import net.deadlydiamond98.koalalib.KoalaLib;
import net.deadlydiamond98.koalalib.common.registration.MultiModBlockRegistries;
import net.deadlydiamond98.koalalib.testing.objs.TestingBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class KoalaLibBlocks {

    public static final Supplier<Block> TEST = MultiModBlockRegistries.registerBlock(new ResourceLocation(KoalaLib.MOD_ID, "test_block"),
            () -> new TestingBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));

    public static void register() {
        KoalaLib.LOGGER.info("TEST");
    }
}
