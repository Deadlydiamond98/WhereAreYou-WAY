package net.deadlydiamond98.way.common.command.commands.color;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.arguments.DyeColorArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.item.DyeColor;

public class DyeColorCommmand extends AbstractColorCommand {

    public DyeColorCommmand(int permLvl) {
        super(permLvl, "default");
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected int getColor(CommandContext<CommandSourceStack> context) {
        return isColor(context) ? DyeColorArgument.getColor(context, "color").getTextColor() : 0xFFFFFF;
    }

    @Override
    protected boolean isColor(CommandContext<CommandSourceStack> context) {
        return true;
    }

    @Override
    protected String getColorName(CommandContext<CommandSourceStack> context) {
        return DyeColorArgument.getName(context, "color");
    }

    @Override
    protected ArgumentBuilder<CommandSourceStack, ?> getColorArgument() {
        return Commands.argument("color", DyeColorArgument.color());
    }
}
