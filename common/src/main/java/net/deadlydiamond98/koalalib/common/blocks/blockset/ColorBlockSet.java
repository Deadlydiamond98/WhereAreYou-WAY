package net.deadlydiamond98.koalalib.common.blocks.blockset;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.tuple.MutablePair;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ColorBlockSet extends AbstractBlockSet {

    protected final MutablePair<@Nullable Supplier<Block>, String> red = new MutablePair<>(null, "red");
    protected final MutablePair<@Nullable Supplier<Block>, String> orange = new MutablePair<>(null, "orange");
    protected final MutablePair<@Nullable Supplier<Block>, String> yellow = new MutablePair<>(null, "yellow");
    protected final MutablePair<@Nullable Supplier<Block>, String> lime = new MutablePair<>(null, "lime");
    protected final MutablePair<@Nullable Supplier<Block>, String> green = new MutablePair<>(null, "green");
    protected final MutablePair<@Nullable Supplier<Block>, String> cyan = new MutablePair<>(null, "cyan");
    protected final MutablePair<@Nullable Supplier<Block>, String> lightBlue = new MutablePair<>(null, "light blue");
    protected final MutablePair<@Nullable Supplier<Block>, String> blue = new MutablePair<>(null, "blue");
    protected final MutablePair<@Nullable Supplier<Block>, String> purple = new MutablePair<>(null, "purple");
    protected final MutablePair<@Nullable Supplier<Block>, String> magenta = new MutablePair<>(null, "magenta");
    protected final MutablePair<@Nullable Supplier<Block>, String> pink = new MutablePair<>(null, "pink");
    protected final MutablePair<@Nullable Supplier<Block>, String> white = new MutablePair<>(null, "white");
    protected final MutablePair<@Nullable Supplier<Block>, String> lightGray = new MutablePair<>(null, "light gray");
    protected final MutablePair<@Nullable Supplier<Block>, String> gray = new MutablePair<>(null, "gray");
    protected final MutablePair<@Nullable Supplier<Block>, String> black = new MutablePair<>(null, "black");
    protected final MutablePair<@Nullable Supplier<Block>, String> brown = new MutablePair<>(null, "brown");

    public ColorBlockSet(String modID, String baseName, String typeName, BlockBehaviour.Properties properties) {
        super(modID, baseName, typeName, properties);
    }

    @Override
    protected void registerBlocks() {
        addColoredBlock(this.red);
        addColoredBlock(this.orange);
        addColoredBlock(this.yellow);
        addColoredBlock(this.lime);
        addColoredBlock(this.green);
        addColoredBlock(this.cyan);
        addColoredBlock(this.lightBlue);
        addColoredBlock(this.blue);
        addColoredBlock(this.purple);
        addColoredBlock(this.magenta);
        addColoredBlock(this.pink);
        addColoredBlock(this.white);
        addColoredBlock(this.lightGray);
        addColoredBlock(this.gray);
        addColoredBlock(this.black);
        addColoredBlock(this.brown);
    }

    private void addColoredBlock(MutablePair<Supplier<Block>, String> block) {
        block.setLeft(register(block.getRight().replace(" ", "_") + "_" + this.baseName, () -> new Block(this.properties)));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Getter Methods

    public final Supplier<Block> getRed() {
        return getOrThrow(this.red);
    }
    public final Supplier<Block> getOrange() {
        return getOrThrow(this.orange);
    }
    public final Supplier<Block> getYellow() {
        return getOrThrow(this.yellow);
    }
    public final Supplier<Block> getLime() {
        return getOrThrow(this.lime);
    }
    public final Supplier<Block> getGreen() {
        return getOrThrow(this.green);
    }
    public final Supplier<Block> getCyan() {
        return getOrThrow(this.cyan);
    }
    public final Supplier<Block> getLightBlue() {
        return getOrThrow(this.lightBlue);
    }
    public final Supplier<Block> getBlue() {
        return getOrThrow(this.blue);
    }
    public final Supplier<Block> getPurple() {
        return getOrThrow(this.purple);
    }
    public final Supplier<Block> getMagenta() {
        return getOrThrow(this.magenta);
    }
    public final Supplier<Block> getPink() {
        return getOrThrow(this.pink);
    }
    public final Supplier<Block> getWhite() {
        return getOrThrow(this.white);
    }
    public final Supplier<Block> getLightGray() {
        return getOrThrow(this.lightGray);
    }
    public final Supplier<Block> getGray() {
        return getOrThrow(this.gray);
    }
    public final Supplier<Block> getBlack() {
        return getOrThrow(this.black);
    }
    public final Supplier<Block> getBrown() {
        return getOrThrow(this.brown);
    }
}
