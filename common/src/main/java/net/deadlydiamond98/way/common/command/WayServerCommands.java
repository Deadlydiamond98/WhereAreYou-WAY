package net.deadlydiamond98.way.common.command;

import com.mojang.brigadier.CommandDispatcher;
import net.deadlydiamond98.way.common.command.commands.OptPlayerCommand;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.common.command.commands.color.ClearColorCommand;
import net.deadlydiamond98.way.common.command.commands.color.DyeColorCommmand;
import net.deadlydiamond98.way.common.command.commands.color.HexColorCommand;
import net.deadlydiamond98.way.common.command.commands.focus.ClearFocusCommand;
import net.deadlydiamond98.way.common.command.commands.focus.FocusDyeColorCommand;
import net.deadlydiamond98.way.common.command.commands.focus.FocusHexColorCommand;
import net.deadlydiamond98.way.common.command.commands.focus.FocusPlayersCommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class WayServerCommands {

    // REGULAR COMMANDS

    private static final AbstractWayCommand OPT_IN = new OptPlayerCommand(0, true);
    private static final AbstractWayCommand OPT_OUT = new OptPlayerCommand(0, false);

    private static final AbstractWayCommand SET_COLOR = new DyeColorCommmand(0);
    private static final AbstractWayCommand SET_COLOR_HEX = new HexColorCommand(0);
    private static final AbstractWayCommand CLEAR_COLOR = new ClearColorCommand(0);

    private static final AbstractWayCommand FOCUS_PLAYERS = new FocusPlayersCommand(0);
    private static final AbstractWayCommand FOCUS_COLOR = new FocusDyeColorCommand(0);
    private static final AbstractWayCommand FOCUS_COLOR_HEX = new FocusHexColorCommand(0);
    private static final AbstractWayCommand CLEAR_FOCUS = new ClearFocusCommand(0);

    // ADMIN COMMANDS

    private static final AbstractWayCommand OPT_IN_ADMIN = new OptPlayerCommand(2, true);
    private static final AbstractWayCommand OPT_OUT_ADMIN = new OptPlayerCommand(2, false);

    private static final AbstractWayCommand SET_COLOR_ADMIN = new DyeColorCommmand(2);
    private static final AbstractWayCommand SET_COLOR_HEX_ADMIN = new HexColorCommand(2);
    private static final AbstractWayCommand CLEAR_COLOR_ADMIN = new ClearColorCommand(2);

    private static final AbstractWayCommand FOCUS_PLAYERS_ADMIN = new FocusPlayersCommand(2);
    private static final AbstractWayCommand FOCUS_COLOR_ADMIN = new FocusDyeColorCommand(2);
    private static final AbstractWayCommand FOCUS_COLOR_HEX_ADMIN = new FocusHexColorCommand(2);
    private static final AbstractWayCommand CLEAR_FOCUS_ADMIN = new ClearFocusCommand(2);



    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess, Commands.CommandSelection environment) {
        AbstractWayCommand.COMMANDS.forEach(wayCommand -> wayCommand.register(dispatcher));
    }
}
