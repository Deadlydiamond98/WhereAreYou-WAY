package net.deadlydiamond98.way.common.command;

import com.mojang.brigadier.CommandDispatcher;
import net.deadlydiamond98.way.common.command.commands.*;
import net.deadlydiamond98.way.common.command.commands.PersistantStateCommand;
import net.deadlydiamond98.way.common.command.commands.color.ClearColorCommand;
import net.deadlydiamond98.way.common.command.commands.color.DyeColorCommmand;
import net.deadlydiamond98.way.common.command.commands.color.HexColorCommand;
import net.deadlydiamond98.way.common.command.commands.focus.ClearFocusCommand;
import net.deadlydiamond98.way.common.command.commands.focus.FocusDyeColorCommand;
import net.deadlydiamond98.way.common.command.commands.focus.FocusHexColorCommand;
import net.deadlydiamond98.way.common.command.commands.focus.FocusPlayersCommand;
import net.deadlydiamond98.way.common.world.WaySavedData;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class WayServerCommands {

    // REGULAR COMMANDS ////////////////////////////////////////////////////////////////////////////////////////////////

    // util
    private static final AbstractWayCommand TOGGLE = new ToggleCommand(0);

    // opt
    private static final AbstractWayCommand OPT_IN = new OptPlayerCommand(0, true);
    private static final AbstractWayCommand OPT_OUT = new OptPlayerCommand(0, false);

    // color
    private static final AbstractWayCommand SET_COLOR = new DyeColorCommmand(0);
    private static final AbstractWayCommand SET_COLOR_HEX = new HexColorCommand(0);
    private static final AbstractWayCommand CLEAR_COLOR = new ClearColorCommand(0);

    // focus
    private static final AbstractWayCommand FOCUS_PLAYERS = new FocusPlayersCommand(0);
    private static final AbstractWayCommand FOCUS_COLOR = new FocusDyeColorCommand(0);
    private static final AbstractWayCommand FOCUS_COLOR_HEX = new FocusHexColorCommand(0);
    private static final AbstractWayCommand CLEAR_FOCUS = new ClearFocusCommand(0);

    // see
    private static final AbstractWayCommand SEE_NAME = new SeeCommand(0, "name",
            (player, bool) -> ((IWayPlayer) player).way$setSeeName(bool)
    );
    private static final AbstractWayCommand SEE_DIST = new SeeCommand(0, "distance",
            (player, bool) -> ((IWayPlayer) player).way$setSeeDist(bool)
    );
    private static final AbstractWayCommand SEE_COLOR = new SeeCommand(0, "color",
            (player, bool) -> ((IWayPlayer) player).way$setSeeColor(bool)
    );
    private static final AbstractWayCommand SEE_OUTLINE = new SeeCommand(0, "outline",
            (player, bool) -> ((IWayPlayer) player).way$setSeeOutline(bool)
    );
    private static final AbstractWayCommand SEE_SELF = new SeeCommand(0, "self",
            (player, bool) -> ((IWayPlayer) player).way$setSeeSelf(bool)
    );

    // dist
    private static final AbstractWayCommand MIN_DIST = new DistanceRenderCommand(0, "Within",
            (player, i) -> ((IWayPlayer) player).way$setMinRender(i));
    private static final AbstractWayCommand MAX_DIST = new DistanceRenderCommand(0, "FurtherThan",
            (player, i) -> ((IWayPlayer) player).way$setMaxRender(i));

    // ADMIN COMMANDS //////////////////////////////////////////////////////////////////////////////////////////////////

    // opt
    private static final AbstractWayCommand OPT_IN_ADMIN = new OptPlayerCommand(2, true);
    private static final AbstractWayCommand OPT_OUT_ADMIN = new OptPlayerCommand(2, false);

    // color
    private static final AbstractWayCommand SET_COLOR_ADMIN = new DyeColorCommmand(2);
    private static final AbstractWayCommand SET_COLOR_HEX_ADMIN = new HexColorCommand(2);
    private static final AbstractWayCommand CLEAR_COLOR_ADMIN = new ClearColorCommand(2);

    // focus
    private static final AbstractWayCommand FOCUS_PLAYERS_ADMIN = new FocusPlayersCommand(2);
    private static final AbstractWayCommand FOCUS_COLOR_ADMIN = new FocusDyeColorCommand(2);
    private static final AbstractWayCommand FOCUS_COLOR_HEX_ADMIN = new FocusHexColorCommand(2);
    private static final AbstractWayCommand CLEAR_FOCUS_ADMIN = new ClearFocusCommand(2);

    // see
    private static final AbstractWayCommand SEE_NAME_ADMIN = new SeeCommand(2, "name",
            (player, bool) -> ((IWayPlayer) player).way$setSeeName(bool)
    );
    private static final AbstractWayCommand SEE_DIST_ADMIN = new SeeCommand(2, "distance",
            (player, bool) -> ((IWayPlayer) player).way$setSeeDist(bool)
    );
    private static final AbstractWayCommand SEE_COLOR_ADMIN = new SeeCommand(2, "color",
            (player, bool) -> ((IWayPlayer) player).way$setSeeColor(bool)
    );
    private static final AbstractWayCommand SEE_OUTLINE_ADMIN = new SeeCommand(2, "outline",
            (player, bool) -> ((IWayPlayer) player).way$setSeeOutline(bool)
    );
    private static final AbstractWayCommand SEE_SELF_ADMIN = new SeeCommand(2, "self",
            (player, bool) -> ((IWayPlayer) player).way$setSeeSelf(bool)
    );


    // admin exclusive
    public static final PersistantStateCommand COLOR_DISTANCE = new PersistantStateCommand("colourDistance",
            WaySavedData::colorDistance, WaySavedData::setColorDistance
    );
    public static final PersistantStateCommand NAME_PAIN = new PersistantStateCommand("namePain",
            WaySavedData::namePain, WaySavedData::setNamePain
    );
    public static final PersistantStateCommand FORCE_OPT = new PersistantStateCommand("forceOpt-in",
            WaySavedData::forceOptIn, WaySavedData::setForceOptIn
    );
    public static final PersistantStateCommand LOCK_COLOR = new PersistantStateCommand("lockColour",
            WaySavedData::lockColor, WaySavedData::setLockColor
    );
    public static final PersistantStateCommand SEE_TEAM_ONLY = new PersistantStateCommand("seeTeamColourOnly",
            WaySavedData::seeTeamColorOnly, WaySavedData::setSeeTeamColorOnly
    );
    public static final PersistantStateCommand SEE_ALL = new PersistantStateCommand("seeAll",
            WaySavedData::seeAll, WaySavedData::setSeeAll
    );
    public static final PersistantStateCommand NO_FRIENDLY_FIRE = new PersistantStateCommand("teamColourNoFriendlyFire",
            WaySavedData::teamColourNoFriendlyFire, WaySavedData::setTeamColourNoFriendlyFire
    );


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess, Commands.CommandSelection environment) {
        AbstractWayCommand.COMMANDS.forEach(wayCommand -> wayCommand.register(dispatcher));
    }
}
