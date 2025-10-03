package net.deadlydiamond98.way.common.command;

import com.mojang.brigadier.arguments.ArgumentType;
import net.deadlydiamond98.way.common.command.arguments.DyeColorArgument;
import net.deadlydiamond98.way.common.command.arguments.HexColorArgument;
import net.deadlydiamond98.way.platform.Service;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;

import java.util.HexFormat;

public class WayCommandArgTypes {

    public static void register() {
        register("dye_color", DyeColorArgument.class, SingletonArgumentInfo.contextFree(DyeColorArgument::new));
        register("hex_color", HexColorArgument.class, SingletonArgumentInfo.contextFree(HexColorArgument::new));
    }

    public static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>, I extends ArgumentTypeInfo<A, T>> void register(String id, Class<A> infoClass, I argumentTypeInfo) {
        Service.PLATFORM.registerArgType(id, infoClass, argumentTypeInfo);
    }
}
