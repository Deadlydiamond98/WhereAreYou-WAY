package net.deadlydiamond98.koalalib.api.blocks.blockset;

import net.deadlydiamond98.koalalib.api.registration.MultiModBlockRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.tuple.MutablePair;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class AbstractBlockSet {

    private final String modID;
    protected final String baseName;
    protected final String typeName;
    protected final BlockBehaviour.Properties properties;

    /**
     * @param modID Your mod's Mod ID
     * @param baseName The name of the blocks (ex: oak, granite, quartz)
     * @param typeName The name of the type (ex: polished, bricks, tile)
     * @param properties The Properties that the blocks use
     */
    public AbstractBlockSet(String modID, String baseName, String typeName, BlockBehaviour.Properties properties) {
        this.modID = modID;
        this.baseName = baseName;
        this.typeName = typeName;
        this.properties = properties;

        registerBlocks();
    }

    /**
     * This method is used to add blocks to a block Type, such as the default base, slab, and stairs. An example of how
     * this is used can be found in {@link DefaultBlockSet}
     */
    protected abstract void registerBlocks();


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets a block, or throws an error if the block isn't present in the blockType, this shouldn't never thrown an
     * error under normal circumstances, but this is just in case
     * @param block The Block that you're trying to get
     * @return The Block you're trying to get
     */
    protected final Supplier<Block> getOrThrow(MutablePair<@Nullable Supplier<Block>, String> block) {
        if (block.left != null) {
            return block.left;
        }
        throw new IllegalArgumentException("The " + this.typeName + " blockset type doesn't include a " + block.right);
    }

    // Registration Methods

    protected final <T extends Block> Supplier<T> register(String suffix, Supplier<T> block) {
        return register("", suffix, block);
    }
    protected final <T extends Block> Supplier<T> register(String prefix, String suffix, Supplier<T> block) {
        return MultiModBlockRegistries.registerBlock(new ResourceLocation(this.modID, prefix + this.baseName + suffix), block);
    }
}
