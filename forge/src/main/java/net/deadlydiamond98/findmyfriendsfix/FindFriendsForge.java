package net.deadlydiamond98.findmyfriendsfix;

import net.deadlydiamond98.findmyfriendsfix.event.FindFriendsTickingEvent;
import net.deadlydiamond98.findmyfriendsfix.networking.FindFriendsForgeNetworking;
import net.deadlydiamond98.findmyfriendsfix.platform.ForgePlatformHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FindFriends.MOD_ID)
public class FindFriendsForge {
    public FindFriendsForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FindFriends.init();
        ForgePlatformHelper.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        FindFriendsForgeNetworking.register();
    }

    @Mod.EventBusSubscriber(modid = FindFriends.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class TickEvents {
        @SubscribeEvent
        public static void tickEvent(TickEvent.LevelTickEvent event) {
            if (event.phase == TickEvent.Phase.END || event.side == LogicalSide.CLIENT) return;
            Level level = event.level;
            FindFriendsTickingEvent.tick(level);
        }
    }
}