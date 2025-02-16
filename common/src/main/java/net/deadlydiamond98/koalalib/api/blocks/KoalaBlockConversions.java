package net.deadlydiamond98.koalalib.api.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class KoalaBlockConversions {
    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(HashBiMap::create);
    public static final Supplier<BiMap<Block, Block>> STRIPPABLES = Suppliers.memoize(HashBiMap::create);

    public static void addBlockToWaxables(Block input, Block output) {
        WAXABLES.get().put(input, output);
    }

    public static void addBlockToStrippables(Block input, Block output) {
        STRIPPABLES.get().put(input, output);
    }
}
