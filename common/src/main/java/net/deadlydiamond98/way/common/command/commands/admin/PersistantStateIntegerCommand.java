package net.deadlydiamond98.way.common.command.commands.admin;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.common.world.WaySavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.List;

public class PersistantStateIntegerCommand extends AbstractWayCommand {

    private final getPersistantState getter;
    private final setPersistantState setter;

    public PersistantStateIntegerCommand(String type, getPersistantState getter, setPersistantState setter) {
        super(2, type);
        this.getter = getter;
        this.setter = setter;
    }

    public int getValue(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            return getter.get(getWayData(serverPlayer.getServer().overworld()));
        }
        return 4;
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            setter.set(
                    getWayData(serverPlayer.getServer().overworld()),
                    IntegerArgumentType.getInteger(context, type)
            );
        }
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return IntegerArgumentType.getInteger(context, type);
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.argument(type, IntegerArgumentType.integer(1, 20)));
    }

    @Override
    protected boolean multiTarget() {
        return false;
    }

    public static WaySavedData getWayData(ServerLevel world) {
        DimensionDataStorage manager = world.getDataStorage();
        return manager.computeIfAbsent(WaySavedData::fromNbt, WaySavedData::new, "way_saved_data");
    }

    @FunctionalInterface public interface getPersistantState {
        int get(WaySavedData data);
    }
    @FunctionalInterface public interface setPersistantState {
        void set(WaySavedData data, int bool);
    }
}
