package net.deadlydiamond98.way.platform;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface PlatformHelper {

    <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>, I extends ArgumentTypeInfo<A, T>> void registerArgType(String id, Class<A> infoClass, I argumentTypeInfo);

    void sendS2CRenderingPacket(ServerPlayer sender, boolean toggle, boolean names, boolean distance, boolean colors, boolean outlines, boolean head, boolean headOutline,
                                boolean colordistance, boolean namePainFlash, boolean namePainGetRedder, int minRender, int maxRender, boolean way$bypassOpt);
    void sendS2CPlayerList(ServerPlayer sender, Player player);
    void sendS2CClearPacket(ServerPlayer sender);



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    @FunctionalInterface
    interface BlockEntityFactory<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state);
    }
}