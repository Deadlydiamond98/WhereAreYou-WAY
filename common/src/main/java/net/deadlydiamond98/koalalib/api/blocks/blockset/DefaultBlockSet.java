package net.deadlydiamond98.koalalib.api.blocks.blockset;

import net.deadlydiamond98.koalalib.api.blocks.vanilla.StairBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.tuple.MutablePair;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DefaultBlockSet extends AbstractBlockSet {

    // Base
    protected final MutablePair<@Nullable Supplier<Block>, String> base = new MutablePair<>(null, "base");
    protected final MutablePair<@Nullable Supplier<Block>, String> stair = new MutablePair<>(null, "stair");
    protected final MutablePair<@Nullable Supplier<Block>, String> slab = new MutablePair<>(null, "slab");

    // Walls
    protected final MutablePair<@Nullable Supplier<Block>, String> wall = new MutablePair<>(null, "wall");
    protected final MutablePair<@Nullable Supplier<Block>, String> fence = new MutablePair<>(null, "fence");

    // redstone
    protected final MutablePair<@Nullable Supplier<Block>, String> pressurePlate = new MutablePair<>(null, "pressure plate");
    protected final MutablePair<@Nullable Supplier<Block>, String> button = new MutablePair<>(null, "button");

    // Doors
    protected final MutablePair<@Nullable Supplier<Block>, String> door = new MutablePair<>(null, "door");
    protected final MutablePair<@Nullable Supplier<Block>, String> trapdoor = new MutablePair<>(null, "trapdoor");
    protected final MutablePair<@Nullable Supplier<Block>, String> gate = new MutablePair<>(null, "gate");

    public DefaultBlockSet(String modID, String baseName, String typeName, BlockBehaviour.Properties properties) {
        super(modID, baseName, typeName, properties);
    }

    @Override
    protected void registerBlocks() {
        this.base.setLeft(register(this.baseName, () -> new Block(this.properties)));
        this.slab.setLeft(register(this.baseName + "_slab", () -> new SlabBlock(this.properties)));
        this.stair.setLeft(register(this.baseName + "_stairs", () -> new StairBlock(this.properties)));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Getter Methods

    public final Supplier<Block> getBase() {
        return getOrThrow(this.base);
    }

    public final Supplier<Block> getSlab() {
        return getOrThrow(this.slab);
    }

    public final Supplier<Block> getStairs() {
        return getOrThrow(this.stair);
    }
}
