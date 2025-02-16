package net.deadlydiamond98.koalalib.common.blocks.blockset;

import net.deadlydiamond98.koalalib.common.blocks.vanilla.DoorBlock;
import net.deadlydiamond98.koalalib.common.blocks.vanilla.TrapDoorBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.function.Supplier;

public class WoodBlockSet extends IncludesRedstoneBlockSet {
    public WoodBlockSet(String modID, String baseName, String typeName, BlockBehaviour.Properties properties) {
        super(modID, baseName, typeName, properties, true);
    }

    @Override
    protected void registerBlocks() {
        super.registerBlocks();
        this.door.setLeft(register(this.baseName + "_door", () -> new DoorBlock(this.properties, BlockSetType.OAK)));
        this.trapdoor.setLeft(register(this.baseName + "_trapdoor", () -> new TrapDoorBlock(this.properties, BlockSetType.OAK)));
        this.fence.setLeft(register(this.baseName + "_fence", () -> new FenceBlock(this.properties)));
        this.gate.setLeft(register(this.baseName + "_fence_gate", () -> new FenceGateBlock(this.properties, WoodType.OAK)));
    }

    public final Supplier<Block> getDoor() {
        return getOrThrow(this.door);
    }

    public final Supplier<Block> getTrapdoor() {
        return getOrThrow(this.trapdoor);
    }

    public final Supplier<Block> getFence() {
        return getOrThrow(this.fence);
    }

    public final Supplier<Block> getGate() {
        return getOrThrow(this.gate);
    }
}
