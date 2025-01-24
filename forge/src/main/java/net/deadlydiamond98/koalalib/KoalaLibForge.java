package net.deadlydiamond98.koalalib;

import net.deadlydiamond98.koalalib.platform.ForgePlatformHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KoalaLib.MOD_ID)
public class KoalaLibForge {

    public KoalaLibForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        KoalaLib.init();
        ForgePlatformHelper.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
    }
}