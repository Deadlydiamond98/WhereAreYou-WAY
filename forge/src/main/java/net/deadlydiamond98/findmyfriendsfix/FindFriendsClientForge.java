package net.deadlydiamond98.findmyfriendsfix;

import net.deadlydiamond98.findmyfriendsfix.event.FindFriendsNameplateRenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FindFriends.MOD_ID, value = Dist.CLIENT)
public class FindFriendsClientForge {

    @SubscribeEvent
    public static void renderNameplate(RenderLevelStageEvent event) {
        Minecraft client = Minecraft.getInstance();
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
            return;
        }
        FindFriendsNameplateRenderEvent.render(event.getPoseStack(), client.renderBuffers().bufferSource(), client.level, event.getPartialTick());
    }

    @SubscribeEvent
    public static void keybindingInputEvent(InputEvent.Key event) {
        FindFriendsKeybindings.tickKeybinding(Minecraft.getInstance());
    }

    @SubscribeEvent
    public static void registerKeybindings(RegisterKeyMappingsEvent event) {
        event.register(FindFriendsKeybindings.TOGGLE_NAMEPLATE);
    }
}
