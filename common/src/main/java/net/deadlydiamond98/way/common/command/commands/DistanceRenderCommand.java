package net.deadlydiamond98.way.common.command.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class DistanceRenderCommand extends AbstractWayCommand {
    private final SetWayPlayerRenderDist setterMethod;

    public DistanceRenderCommand(int permLvl, String type, SetWayPlayerRenderDist setterMethod) {
        super(permLvl, "disableIf" + type);
        this.setterMethod = setterMethod;
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        setterMethod.set(player, IntegerArgumentType.getInteger(context, "distance"));
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return IntegerArgumentType.getInteger(context, "distance");
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.argument("distance", IntegerArgumentType.integer(0, 999999)));
    }

    @FunctionalInterface
    public interface SetWayPlayerRenderDist {
        void set(Player player, int i);
    }
}
