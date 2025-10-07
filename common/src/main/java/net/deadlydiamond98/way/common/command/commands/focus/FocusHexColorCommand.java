package net.deadlydiamond98.way.common.command.commands.focus;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
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

public class FocusHexColorCommand extends AbstractWayCommand {
    public FocusHexColorCommand(int permLvl) {
        super(permLvl, "focus");
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        int color = HexColorArgument.getInt(context, "hex");
        ((IWayPlayer) player).way$setFocusedPlayerNames(new ArrayList<>());
        ((IWayPlayer) player).way$setFocusedColor(color);
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return HexColorArgument.getString(context, "hex")  + " ";
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal("color"), Commands.literal("hex"), Commands.argument("hex", HexColorArgument.color()));
    }

    @Override
    protected String getID(CommandContext<CommandSourceStack> context, Player player) {
        return super.getID(context, player) + ".color";
    }

    @Override
    protected void sendSuccess(CommandContext<CommandSourceStack> context, MutableComponent base, Player player) {
        base.append(Component.literal("■").setStyle(Style.EMPTY.withColor(HexColorArgument.getInt(context, "hex"))));
        super.sendSuccess(context, base, player);
    }
}
