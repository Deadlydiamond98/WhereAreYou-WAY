package net.deadlydiamond98.way.common.command.commands.admin;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SeeAllCommand extends AbstractWayCommand {
    public SeeAllCommand() {
        super(2, "seeAll");
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        if (player instanceof ServerPlayer) {
            ((IWayPlayer) player).way$setBypassOpt(BoolArgumentType.getBool(context, type));
            player.getCommandSenderWorld().players().forEach(player1 -> ((IWayPlayer) player1).way$updateRenderPreferences());
        }
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return BoolArgumentType.getBool(context, type);
    }

    @Override
    protected ArgumentBuilder<CommandSourceStack, ?> getPrePlayerArg() {
        return Commands.literal("seeAll");
    }

    @Override
    protected boolean useRegularPrefixAfterPlayers() {
        return false;
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.argument(type, BoolArgumentType.bool()));
    }
}
