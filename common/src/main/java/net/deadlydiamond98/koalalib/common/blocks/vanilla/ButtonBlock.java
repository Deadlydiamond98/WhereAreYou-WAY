package net.deadlydiamond98.koalalib.common.blocks.vanilla;

import net.minecraft.world.level.block.state.properties.BlockSetType;

/**
 * This only exists since this is protected when outside of forge or fabric
 */

public class ButtonBlock extends net.minecraft.world.level.block.ButtonBlock {
    public ButtonBlock(Properties properties, BlockSetType blockSetType, int pressedTicks, boolean wooden) {
        super(properties, blockSetType, pressedTicks, wooden);
    }
}
