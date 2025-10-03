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
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class DyeColorArgument implements ArgumentType<DyeColor> {

    private static final String[] COLORS = new String[] {
            "white", "orange", "magenta", "light_blue",
            "yellow", "lime", "pink", "gray", "light_gray",
            "cyan", "purple", "blue", "brown", "green",
            "red", "black"
    };

    public static DyeColorArgument color() {
        return new DyeColorArgument();
    }

    public static DyeColor getColor(CommandContext<CommandSourceStack> context, String color) {
        return context.getArgument(color, DyeColor.class);
    }

    public static String getName(CommandContext<CommandSourceStack> context, String color) {
        DyeColor dyeColor = getColor(context, color);
        String[] nameParts = dyeColor.getName().split("_");
        StringBuilder name = new StringBuilder();
        for (String namePart : nameParts) {
            name.append(namePart.substring(0, 1).toUpperCase()).append(namePart.substring(1)).append(" ");
        }
        return name.toString();
    }

    @Override
    public DyeColor parse(StringReader reader) throws CommandSyntaxException {
        String string = reader.readUnquotedString();
        DyeColor color = DyeColor.CODEC.byName(string);

        if (color != null) {
            return color;
        }
        throw invalidArgException(string);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(COLORS, builder);
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.asList("red", "green");
    }

    public static CommandSyntaxException invalidArgException(String string) {
        return new DynamicCommandExceptionType((obj) -> Component.translatable("way.argument.dye.invalid", obj)).create(string);
    }
}
