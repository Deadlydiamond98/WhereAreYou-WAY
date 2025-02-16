package net.deadlydiamond98.koalalib.api.blocks.vanilla;

import net.minecraft.world.level.block.state.properties.BlockSetType;

/**
 * This only exists since this is protected when outside of forge or fabric
 */

public class PressurePlateBlock extends net.minecraft.world.level.block.PressurePlateBlock {

    public PressurePlateBlock(boolean everything, Properties properties, BlockSetType blockSetType) {
        super(getSensitivity(everything), properties, blockSetType);
    }

    private static Sensitivity getSensitivity(boolean everything) {
        return everything ? Sensitivity.EVERYTHING : Sensitivity.MOBS;
    }
}
