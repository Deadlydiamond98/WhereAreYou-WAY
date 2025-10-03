package net.deadlydiamond98.way.common.command.commands.color;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.arguments.HexColorArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class HexColorCommand extends AbstractColorCommand {
    public HexColorCommand(int permLvl) {
        super(permLvl, "hex");
    }

    @Override
    protected int getColor(CommandContext<CommandSourceStack> context) {
        return HexColorArgument.getInt(context, "hex");
    }

    @Override
    protected boolean isColor(CommandContext<CommandSourceStack> context) {
        return true;
    }

    @Override
    protected String getColorName(CommandContext<CommandSourceStack> context) {
        return HexColorArgument.getString(context, "hex") + " ";
    }

    @Override
    protected ArgumentBuilder<CommandSourceStack, ?> getColorArgument() {
        return Commands.argument("hex", HexColorArgument.color());
    }
}
