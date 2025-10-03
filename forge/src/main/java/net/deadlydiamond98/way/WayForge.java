package net.deadlydiamond98.way;

import net.deadlydiamond98.way.common.command.WayServerCommands;
import net.deadlydiamond98.way.common.events.WayTickingEvent;
import net.deadlydiamond98.way.networking.WayForgeNetworking;
import net.deadlydiamond98.way.platform.ForgePlatformHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Way.MOD_ID)
public class WayForge {
    public WayForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Way.init();
        ForgePlatformHelper.registerArgTypes(modEventBus);
        modEventBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        WayForgeNetworking.register();
    }

    @Mod.EventBusSubscriber(modid = Way.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class TickEvents {
        @SubscribeEvent
        public static void tickEvents(TickEvent.LevelTickEvent event) {
            if (event.phase == TickEvent.Phase.END || event.side == LogicalSide.CLIENT) return;
            Level level = event.level;
            WayTickingEvent.tick(level);
        }

        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event) {
            WayServerCommands.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
        }
    }
}