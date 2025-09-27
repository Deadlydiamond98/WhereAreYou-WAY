package net.deadlydiamond98.findmyfriendsfix;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class FindFriendsKeybindings {
    public static final KeyMapping TOGGLE_NAMEPLATE = register("toggle", GLFW.GLFW_KEY_M);

    public static boolean renderNameOverlay = true;
    private static boolean hasFired = false;

    public static void tickKeybinding(Minecraft client) {
        if (client.player != null) {
            if (TOGGLE_NAMEPLATE.isDown()) {
                if (!hasFired) {
                    hasFired = true;
                    renderNameOverlay = !renderNameOverlay;
                    FindFriends.LOGGER.info("KeyBinding Pressed, the overlay is now: {}", renderNameOverlay);
                }
            } else {
                hasFired = false;
            }
        }
    }

    private static KeyMapping register(String toggle, int glfw) {
        String key = "key." + FindFriends.MOD_ID + ".";
        return new KeyMapping(key + toggle, InputConstants.Type.KEYSYM, glfw, key + ".category");
    }
}
