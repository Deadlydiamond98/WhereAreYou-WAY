package net.deadlydiamond98.koalalib.testing.objs;

import net.deadlydiamond98.koalalib.testing.KoalaLibBlockEntities;
import net.deadlydiamond98.koalalib.testing.KoalaLibBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TestBlockEntity extends BlockEntity {
    public TestBlockEntity(BlockPos $$1, BlockState $$2) {
        super(KoalaLibBlockEntities.TEST_B_ENTITY.get(), $$1, $$2);
    }


    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T t) {
        level.players().forEach(player -> {
            player.sendSystemMessage(Component.literal("HELLO"));
        });
    }
}
