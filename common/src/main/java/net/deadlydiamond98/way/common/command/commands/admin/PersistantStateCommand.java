package net.deadlydiamond98.way.common.command.commands.admin;

import com.mojang.brigadier.arguments.BoolArgumentType;
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

public class PersistantStateCommand extends AbstractWayCommand {

    private final getPersistantState getter;
    private final setPersistantState setter;

    public PersistantStateCommand(String type, getPersistantState getter, setPersistantState setter) {
        super(2, type);
        this.getter = getter;
        this.setter = setter;
    }

    public boolean getValue(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            return getter.get(getWayData(serverPlayer.getServer().overworld()));
        }
        return false;
    }

    @Override
    protected void execute(CommandContext<CommandSourceStack> context, Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            setter.set(
                    getWayData(serverPlayer.getServer().overworld()),
                    BoolArgumentType.getBool(context, type)
            );
        }
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return BoolArgumentType.getBool(context, type);
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.argument(type, BoolArgumentType.bool()));
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
        boolean get(WaySavedData data);
    }
    @FunctionalInterface public interface setPersistantState {
        void set(WaySavedData data, boolean bool);
    }
}
