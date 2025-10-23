package net.deadlydiamond98.way.common.command.commands.color;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.WayServerCommands;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public abstract class AbstractColorCommand extends AbstractWayCommand {

    private final String colorType;

    public AbstractColorCommand(int permLvl, String type) {
        super(permLvl, "color");
        this.colorType = type;
    }

    protected abstract int getColor(CommandContext<CommandSourceStack> context);
    protected abstract boolean isColor(CommandContext<CommandSourceStack> context);
    protected abstract String getColorName(CommandContext<CommandSourceStack> context);
    protected abstract ArgumentBuilder<CommandSourceStack,?> getColorArgument();

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        if (canRun(player)) {
            ((IWayPlayer) player).way$setColor(getColor(context));
            ((IWayPlayer) player).way$setClear(false);
        }
    }

    private boolean canRun(Player player) {
        return !WayServerCommands.LOCK_COLOR.getValue(player) || player.hasPermissions(this.permLvl);
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return getColorName(context);
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal(this.colorType), getColorArgument());
    }

    @Override
    protected String getID(CommandContext<CommandSourceStack> context, Player player) {
        if (!canRun(player)) {
            return "color.fail";
        }
        if (isColor(context)) {
            return super.getID(context, player);
        }
        return "color.reset";
    }

    @Override
    protected void sendSuccess(CommandContext<CommandSourceStack> context, MutableComponent base, Player player) {
        if (isColor(context) && canRun(player)) {
            base.append(Component.literal("■").setStyle(Style.EMPTY.withColor(getColor(context))));
        }
        Style errorColor = canRun(player) ? Style.EMPTY : Style.EMPTY.withColor(ChatFormatting.DARK_RED);
        super.sendSuccess(context, base.withStyle(errorColor), player);
    }
}
