package net.deadlydiamond98.way.common.command.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SeeCommand extends AbstractWayCommand {
    private final String seeType;
    private final SetWayPlayerBool setterMethod;

    public SeeCommand(int permLvl, String type, SetWayPlayerBool setterMethod) {
        super(permLvl, "see");
        this.seeType = type;
        this.setterMethod = setterMethod;
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        setterMethod.set(player, BoolArgumentType.getBool(context, "show" + seeType));
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return BoolArgumentType.getBool(context, "show" + seeType);
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal(seeType.toLowerCase()), Commands.argument("show" + seeType, BoolArgumentType.bool()));
    }

    @FunctionalInterface
    public interface SetWayPlayerBool {
        void set(Player player, boolean bool);
    }
}
