package net.deadlydiamond98.way.common.command.commands.focus;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ClearFocusCommand extends AbstractWayCommand {
    public ClearFocusCommand(int permLvl) {
        super(permLvl, "focus");
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        ((IWayPlayer) player).way$setFocusedPlayerNames(new ArrayList<>());
        ((IWayPlayer) player).way$setFocusedColor(null);
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return "If this is seen, something is wrong in the lang";
    }

    @Override
    protected String getID(CommandContext<CommandSourceStack> context, Player player) {
        return "focus.reset";
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal("clear"));
    }
}
