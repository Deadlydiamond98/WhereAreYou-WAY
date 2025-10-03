package net.deadlydiamond98.way.common.command.commands.color;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ColorArgument;

public class ChatColorCommand extends AbstractColorCommand {
    public ChatColorCommand(int permLvl) {
        super(permLvl, "chat");
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected int getColor(CommandContext<CommandSourceStack> context) {
        return isColor(context) ? ColorArgument.getColor(context, "color").getColor() : 0xFFFFFF;
    }

    @Override
    protected boolean isColor(CommandContext<CommandSourceStack> context) {
        return ColorArgument.getColor(context, "color").isColor();
    }

    @Override
    protected String getColorName(CommandContext<CommandSourceStack> context) {
        ChatFormatting color = ColorArgument.getColor(context, "color");
        String[] nameParts = color.getName().split("_");
        StringBuilder name = new StringBuilder();
        for (String namePart : nameParts) {
            name.append(namePart.substring(0, 1).toUpperCase()).append(namePart.substring(1)).append(" ");
        }
        return name.toString();
    }

    @Override
    protected ArgumentBuilder<CommandSourceStack, ?> getColorArgument() {
        return Commands.argument("color", ColorArgument.color());
    }
}
