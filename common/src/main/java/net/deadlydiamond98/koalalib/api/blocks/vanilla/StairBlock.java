package net.deadlydiamond98.koalalib.api.blocks.vanilla;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * This only exists since this is protected when outside of forge or fabric
 */

public class StairBlock extends net.minecraft.world.level.block.StairBlock {

    public StairBlock(Properties properties) {
        super(Blocks.OAK_PLANKS.defaultBlockState(), properties);
    }

    public StairBlock(BlockState defaultState, Properties properties) {
        super(defaultState, properties);
    }
}
