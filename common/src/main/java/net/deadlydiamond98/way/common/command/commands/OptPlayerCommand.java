package net.deadlydiamond98.way.common.command.commands;

import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Player;

public class OptPlayerCommand extends AbstractWayCommand {

    private final boolean showPlayer;

    public OptPlayerCommand(int permissionLvl, boolean showPlayer) {
        super(permissionLvl, "opt-" + (showPlayer ? "in" : "out"));
        this.showPlayer = showPlayer;
    }

    @Override
    public void execute(CommandContext<CommandSourceStack> context, Player player) {
        ((IWayPlayer) player).way$setShowing(this.showPlayer);
    }

    @Override
    protected String getID(CommandContext<CommandSourceStack> context, Player player) {
        return "opt";
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return this.showPlayer;
    }
}
