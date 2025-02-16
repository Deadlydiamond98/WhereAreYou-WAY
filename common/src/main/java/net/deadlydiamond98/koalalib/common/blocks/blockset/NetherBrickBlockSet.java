package net.deadlydiamond98.koalalib.common.blocks.blockset;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class NetherBrickBlockSet extends StoneBlockSet {

    public NetherBrickBlockSet(String modID, String baseName, String typeName, BlockBehaviour.Properties properties) {
        super(modID, baseName, typeName, properties);
    }

    @Override
    protected void registerBlocks() {
        super.registerBlocks();
        this.fence.setLeft(register(this.baseName + "_fence", () -> new WallBlock(this.properties)));
    }

    public final Supplier<Block> getFence() {
        return getOrThrow(this.fence);
    }
}
