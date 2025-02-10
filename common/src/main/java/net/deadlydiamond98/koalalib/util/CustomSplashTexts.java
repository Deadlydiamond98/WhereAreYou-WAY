package net.deadlydiamond98.koalalib.util;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class CustomSplashTexts {

    public static final List<String> NEW_SPLASH_TEXT = new ArrayList<>();

    public static void addCustomSplashText(String text) {
        NEW_SPLASH_TEXT.add(text);
    }

    public static void addCustomSplashText(Component text) {
        addCustomSplashText(text.getString());
    }
}
