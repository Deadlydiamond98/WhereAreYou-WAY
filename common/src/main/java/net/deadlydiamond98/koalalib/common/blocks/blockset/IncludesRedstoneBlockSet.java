package net.deadlydiamond98.koalalib.common.blocks.blockset;

import net.deadlydiamond98.koalalib.common.blocks.vanilla.ButtonBlock;
import net.deadlydiamond98.koalalib.common.blocks.vanilla.PressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.function.Supplier;

public class IncludesRedstoneBlockSet extends DefaultBlockSet {

    private final boolean isWood;

    public IncludesRedstoneBlockSet(String modID, String baseName, String typeName, BlockBehaviour.Properties properties, boolean isWood) {
        super(modID, baseName, typeName, properties);
        this.isWood = isWood;
    }

    public IncludesRedstoneBlockSet(String modID, String baseName, String typeName, BlockBehaviour.Properties properties) {
        this(modID, baseName, typeName, properties, false);
    }

    @Override
    protected void registerBlocks() {
        super.registerBlocks();
        createRedstoneBlocks();
    }
    protected void createRedstoneBlocks() {
        if (this.isWood) {
            this.button.setLeft(register(this.baseName + "_button", () -> new ButtonBlock(this.properties, BlockSetType.OAK, 30, true)));
            this.pressurePlate.setLeft(register(this.baseName + "_pressure_plate", () ->
                    new PressurePlateBlock(true, this.properties, BlockSetType.OAK)));
        }
        this.button.setLeft(register(this.baseName + "_button", () -> new ButtonBlock(this.properties, BlockSetType.STONE , 20, false)));
        this.pressurePlate.setLeft(register(this.baseName + "_pressure_plate", () ->
                new PressurePlateBlock(false, this.properties, BlockSetType.STONE)));
    }

    public final Supplier<Block> getPressurePlate() {
        return getOrThrow(this.pressurePlate);
    }

    public final Supplier<Block> getButton() {
        return getOrThrow(this.button);
    }
}
