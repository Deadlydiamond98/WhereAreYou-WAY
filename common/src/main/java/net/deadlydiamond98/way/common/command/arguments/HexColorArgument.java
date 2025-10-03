package net.deadlydiamond98.way.common.command.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class HexColorArgument implements ArgumentType<String> {

//    private static final String[] PREFIX = new String[] {
//            "#"
//    };

    public static HexColorArgument color() {
        return new HexColorArgument();
    }

    public static String getString(CommandContext<CommandSourceStack> context, String color) {
        return getFormattedHexString(context.getArgument(color, String.class)).toUpperCase();
    }

    public static int getInt(CommandContext<CommandSourceStack> context, String color) {
        try {
            return Color.decode(getString(context, color)).getRGB();
        } catch (Exception e) {
            return 0xFFFFFF;
        }
    }

    private static String getFormattedHexString(String string) {
        String colorString = stripStart(string, "#", "0x");
        return "#" + colorString;
    }

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        String string = reader.readString();

        String hex = getFormattedHexString(string);
        if (hex.length() == 7) {
            if (hex.replaceAll("[g-z]", "").length() == 7) {
                return string;
            }
        }
        throw invalidArgException(string);
    }

    private static String stripStart(String string, String... prefixes) {
        for (String prefix : prefixes) {
            if (string.startsWith(prefix)) {
                return string.substring(prefix.length());
            }
        }
        return string;
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.asList("#FFFFFF", "#FF0000", "#00FFAA");
    }

    public static CommandSyntaxException invalidArgException(String string) {
        return new DynamicCommandExceptionType((obj) -> Component.translatable("way.argument.hex.invalid", obj)).create(string);
    }
}
