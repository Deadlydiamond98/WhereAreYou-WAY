package net.deadlydiamond98.way.common.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWayCommand {
    public static final List<AbstractWayCommand> COMMANDS = new ArrayList<>();
    protected static final String LANG_PREFIX = "commands.way.";

    protected final int permLvl;
    protected final String type;

    public AbstractWayCommand(int permLvl, String type) {
        this.permLvl = permLvl;
        this.type = type;
        COMMANDS.add(this);
    }

    // Build and Register Command

    public final void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("way").requires(source -> source.hasPermission(this.permLvl)).then(buildCommandStart()));
    }

    private ArgumentBuilder<CommandSourceStack,?> buildCommandStart() {
        List<ArgumentBuilder<CommandSourceStack,?>> commandParts = new ArrayList<>();

        if (isOP()) {
            commandParts.add(Commands.literal("admin"));
            if (multiTarget()) {
                commandParts.add(getPrePlayerArg());
                commandParts.add(Commands.argument("players", EntityArgument.players()));
            }
        }

        commandParts.add(Commands.literal(this.type));
        commandParts.addAll(getExtraCommandParts());

        int last = commandParts.size() - 1;
        ArgumentBuilder<CommandSourceStack,?> lastPart = commandParts.get(last).executes(this::execute);
        commandParts.remove(last);

        return buildCommand(lastPart, commandParts);
    }

    protected ArgumentBuilder<CommandSourceStack,?> getPrePlayerArg() {
        return Commands.literal("modify");
    }

    private ArgumentBuilder<CommandSourceStack,?> buildCommand(ArgumentBuilder<CommandSourceStack, ?> command, List<ArgumentBuilder<CommandSourceStack,?>> commandParts) {
        if (!commandParts.isEmpty()) {
            int last = commandParts.size() - 1;
            ArgumentBuilder<CommandSourceStack,?> commandPart = commandParts.get(last);
            commandParts.remove(last);
            return buildCommand(commandPart.then(command), commandParts);
        }
        return command;
    }

    protected List<ArgumentBuilder<CommandSourceStack,?>> getExtraCommandParts() {
        return new ArrayList<>();
    }

    // Execute Command

    protected final int execute(CommandContext<CommandSourceStack> context) {
        Optional<List<? extends Player>> optional = getPlayers(context);

        if (optional.isPresent()) {
            List<? extends Player> players = optional.get();
            players.forEach(player -> execute(context, player));
            successMSG(context, players);
            return players.size();
        }
        return 0;
    }

    protected abstract void execute(CommandContext<CommandSourceStack> context, Player player);

    protected boolean isOP() {
        return this.permLvl > 1;
    }

    // Player Getting

    protected Optional<List<? extends Player>> getPlayers(CommandContext<CommandSourceStack> context) {
        if (multiTarget()) {
            try {
                List<? extends Player> players = EntityArgument.getOptionalPlayers(context, "players").stream().toList();
                return Optional.of(players);
            } catch (Exception ignored) {}
        }

        Player player = context.getSource().getPlayer();
        if (player != null) {
            return Optional.of(List.of(player));
        }
        noEntityErrorMSG(context);
        return Optional.empty();
    }

    protected boolean multiTarget() {
        return isOP();
    }

    // Chat Message Results

    private void successMSG(CommandContext<CommandSourceStack> context, Collection<? extends Player> players) {
        boolean bl = players.size() > 1;
        MutableComponent base = Component.translatable(
                LANG_PREFIX + getID(context, players.iterator().next()) + (bl ? ".multi" : ""),
                bl ? players.size() : players.iterator().next().getDisplayName(),
                getNewValue(context)
        );
        sendSuccess(context, base, players.iterator().next());
    }

    protected String getID(CommandContext<CommandSourceStack> context, Player player) {
        return this.type;
    }

    protected void sendSuccess(CommandContext<CommandSourceStack> context, MutableComponent base, Player player) {
        context.getSource().sendSuccess(() -> base, isOP());
    }

    protected abstract Object getNewValue(CommandContext<CommandSourceStack> context);

    protected void noEntityErrorMSG(CommandContext<CommandSourceStack> context) {
        context.getSource().sendFailure(Component.translatable(LANG_PREFIX + "no_player"));
    }
}
