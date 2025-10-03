package net.deadlydiamond98.way.common.command.commands.focus;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FocusPlayersCommand extends AbstractWayCommand {
    public FocusPlayersCommand(int permLvl) {
        super(permLvl, "focus");
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        try {
            Collection<? extends Player> targets = EntityArgument.getPlayers(context, "targets");
            List<Component> names = new ArrayList<>();
            targets.forEach(target -> names.add(target.getName()));
            ((IWayPlayer) player).way$setFocusedPlayerNames(names);
            ((IWayPlayer) player).way$setFocusedColor(null);
        } catch (Exception ignored) {}
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        try {
            Collection<? extends Player> targets = EntityArgument.getPlayers(context, "targets");
            if (targets.size() > 1) {
                return targets.iterator().next().getName();
            } else {
                return targets.size();
            }
        } catch (Exception ignored) {}
        return 0;
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal("player"), Commands.argument("targets", EntityArgument.players()));
    }
}
