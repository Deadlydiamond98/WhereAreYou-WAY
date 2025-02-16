package net.deadlydiamond98.koalalib.testing;

import net.deadlydiamond98.koalalib.KoalaLib;
import net.deadlydiamond98.koalalib.api.registration.MultiModRegistries;
import net.deadlydiamond98.koalalib.testing.objs.TestBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class KoalaLibBlockEntities {

    public static final Supplier<BlockEntityType<TestBlockEntity>> TEST_B_ENTITY = MultiModRegistries.registerBlockEntityType(
            new ResourceLocation(KoalaLib.MOD_ID, "test_block_entity"), TestBlockEntity::new, KoalaLibBlocks.TEST
    );

    public static void register() {
    }
}
