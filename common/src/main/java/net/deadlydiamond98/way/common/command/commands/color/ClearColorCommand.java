package net.deadlydiamond98.way.common.command.commands.color;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ClearColorCommand extends AbstractWayCommand {
    public ClearColorCommand(int permLvl) {
        super(permLvl, "color");
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        ((IWayPlayer) player).way$setColor(0xFFFFFF);
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return "If this is seen, something is wrong in the lang";
    }

    @Override
    protected String getID(CommandContext<CommandSourceStack> context) {
        return "color.reset";
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal("clear"));
    }
}
