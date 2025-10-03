package net.deadlydiamond98.way.common.command.commands.color;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
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
        ((IWayPlayer) player).way$setColor(getColor(context));
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
    protected String getID(CommandContext<CommandSourceStack> context) {
        if (isColor(context)) {
            return super.getID(context);
        }
        return "color.reset";
    }

    @Override
    protected void sendSuccess(CommandContext<CommandSourceStack> context, MutableComponent base) {
        if (isColor(context)) {
            base.append(Component.literal("■").setStyle(Style.EMPTY.withColor(getColor(context))));
        }
        super.sendSuccess(context, base);
    }
}
