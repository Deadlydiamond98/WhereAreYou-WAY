package net.deadlydiamond98.way;

import net.deadlydiamond98.way.client.WayKeybindings;
import net.deadlydiamond98.way.client.WayNameplateRenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class WayClientForge {

    @Mod.EventBusSubscriber(modid = Way.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void renderNameplate(RenderLevelStageEvent event) {
            Minecraft client = Minecraft.getInstance();
            if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
                return;
            }
            WayNameplateRenderEvent.render(event.getPoseStack(), client.renderBuffers().bufferSource(), client.level, event.getPartialTick());
        }

        @SubscribeEvent
        public static void keybindingInputEvent(InputEvent.Key event) {
            WayKeybindings.tickKeybinding(Minecraft.getInstance());
        }
    }

    @Mod.EventBusSubscriber(modid = Way.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerKeybindings(RegisterKeyMappingsEvent event) {
            event.register(WayKeybindings.TOGGLE_NAMEPLATE);
        }

//        @SubscribeEvent
//        public static void registerCommands(RegisterClientCommandsEvent event) {
//            WayCommandsRegistration.Client.register(event.getDispatcher());
//        }
    }
}
