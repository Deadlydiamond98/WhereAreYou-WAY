package net.deadlydiamond98.way.common.command.commands.admin;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.way.common.command.commands.AbstractWayCommand;
import net.deadlydiamond98.way.common.world.WaySavedData;
import net.deadlydiamond98.way.util.mixin.IWayPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.Collection;
import java.util.List;

public class NamePainCommand extends AbstractWayCommand {

    private final getPersistantState getter;
    private final setPersistantState setter;
    private final boolean sendPacket;
    private final String namepainType;

    public NamePainCommand(String type, getPersistantState getter, setPersistantState setter) {
        this(type, getter, setter, false);
    }


    public NamePainCommand(String type, getPersistantState getter, setPersistantState setter, boolean sendPacket) {
        super(2, "namepain");
        this.namepainType = type;
        this.getter = getter;
        this.setter = setter;
        this.sendPacket = sendPacket;
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
            if (serverPlayer instanceof IWayPlayer && this.sendPacket) {
                player.getCommandSenderWorld().players().forEach(player1 -> ((IWayPlayer) player1).way$updateRenderPreferences());
            }
        }
    }

    @Override
    protected Object getNewValue(CommandContext<CommandSourceStack> context) {
        return BoolArgumentType.getBool(context, type);
    }

    @Override
    protected List<ArgumentBuilder<CommandSourceStack, ?>> getExtraCommandParts() {
        return List.of(Commands.literal(this.namepainType), Commands.argument(type, BoolArgumentType.bool()));
    }

    @Override
    protected boolean multiTarget() {
        return false;
    }

    public static WaySavedData getWayData(ServerLevel world) {
        DimensionDataStorage manager = world.getDataStorage();
        return manager.computeIfAbsent(WaySavedData::fromNbt, WaySavedData::new, "way_saved_data");
    }

    @Override
    protected void successMSG(CommandContext<CommandSourceStack> context, Collection<? extends Player> players) {
        MutableComponent base = Component.translatable(LANG_PREFIX + getID(context, players.iterator().next()), getNewValue(context));
        sendSuccess(context, base, players.iterator().next());
    }

    @Override
    protected String getID(CommandContext<CommandSourceStack> context, Player player) {
        return super.getID(context, player) + "." + this.namepainType;
    }

    @FunctionalInterface public interface getPersistantState {
        boolean get(WaySavedData data);
    }
    @FunctionalInterface public interface setPersistantState {
        void set(WaySavedData data, boolean bool);
    }
}
