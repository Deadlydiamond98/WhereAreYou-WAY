package net.deadlydiamond98.way.common.command.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.WayServerCommands;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
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
        if (canRun(player)) {
            setterMethod.set(player, BoolArgumentType.getBool(context, "show" + seeType));
        }
    }

    private boolean canRun(Player player) {
        return !WayServerCommands.LOCK_SEE.getValue(player) || player.hasPermissions(this.permLvl);
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return BoolArgumentType.getBool(context, "show" + seeType);
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal(seeType.toLowerCase()), Commands.argument("show" + seeType, BoolArgumentType.bool()));
    }

    @Override
    protected String getID(CommandContext<CommandSourceStack> context, Player player) {
        if (!canRun(player)) {
            return "see.fail";
        }
        return super.getID(context, player) + "." + this.seeType;
    }

    @Override
    protected void sendSuccess(CommandContext<CommandSourceStack> context, MutableComponent base, Player player) {
        Style errorColor = canRun(player) ? Style.EMPTY : Style.EMPTY.withColor(ChatFormatting.DARK_RED);
        super.sendSuccess(context, base.withStyle(errorColor), player);
    }

    @FunctionalInterface
    public interface SetWayPlayerBool {
        void set(Player player, boolean bool);
    }
}
