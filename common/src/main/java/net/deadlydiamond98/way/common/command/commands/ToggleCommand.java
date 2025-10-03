package net.deadlydiamond98.way.common.command.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ToggleCommand extends AbstractWayCommand {
    public ToggleCommand(int permLvl) {
        super(permLvl, "toggle");
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        ((IWayPlayer) player).way$setToggle(BoolArgumentType.getBool(context, "toggle"));
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return BoolArgumentType.getBool(context, "toggle");
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.argument("toggle", BoolArgumentType.bool()));
    }
}
