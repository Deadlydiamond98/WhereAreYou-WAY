package net.deadlydiamond98.way.common.command.commands.focus;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.arguments.DyeColorArgument;
import net.deadlydiamond98.way.common.command.arguments.HexColorArgument;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class FocusDyeColorCommand extends AbstractWayCommand {
    public FocusDyeColorCommand(int permLvl) {
        super(permLvl, "focus");
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        int color = DyeColorArgument.getColor(context, "color").getTextColor();
        ((IWayPlayer) player).way$setFocusedPlayerNames(new ArrayList<>());
        ((IWayPlayer) player).way$setFocusedColor(color);
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return DyeColorArgument.getName(context, "color");
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal("color"), Commands.literal("default"), Commands.argument("color", DyeColorArgument.color()));
    }

    @Override
    protected String getID(CommandContext<CommandSourceStack> context) {
        return super.getID(context) + ".color";
    }

    @Override
    protected void sendSuccess(CommandContext<CommandSourceStack> context, MutableComponent base) {
        base.append(Component.literal("■").setStyle(Style.EMPTY.withColor(DyeColorArgument.getColor(context, "color").getTextColor())));
        super.sendSuccess(context, base);
    }
}
