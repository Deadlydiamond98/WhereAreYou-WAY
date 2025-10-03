package net.deadlydiamond98.way.common.command.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.awt.*;
import java.util.List;

public class TestCommand extends AbstractWayCommand {
    public TestCommand(int permLvl) {
        super(permLvl, "test");
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        try {
            String hex = StringArgumentType.getString(context, "hex");
            Color.decode(hex);
            player.sendSystemMessage(Component.literal("That is a Hex"));
            player.sendSystemMessage(Component.literal("The length is " + hex.length()));
            player.sendSystemMessage(Component.literal("The Color is " + hex));
        } catch (Exception e) {
            player.sendSystemMessage(Component.literal("Not a Hex"));
        }
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return false;
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.argument("hex", StringArgumentType.string()));
    }
}
