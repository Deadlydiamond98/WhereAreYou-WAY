package net.deadlydiamond98.koalalib.events;

import net.deadlydiamond98.koalalib.common.events.events.KoalaClientTickEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;

public class KoalaCommonEventsForge {

    public static void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
//        KoalaRegistryMaps.registerAndEmpty(KoalaRegistryMaps.CREATIVE_TAB_ENTIRIES, (supplier, creativeModeTab) -> {
//            if (event.getTab() == creativeModeTab) {
//                event.accept(supplier.get());
//            }
//        });
    }

    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        if (event.level.isClientSide) {
            ClientLevel world = (ClientLevel) event.level;
            if (event.phase == TickEvent.Phase.END) {
                KoalaClientTickEvents.END_WORLD_TICK.invoker().onEndWorldTick(world);
            }

            if (event.phase == TickEvent.Phase.START) {
                KoalaClientTickEvents.START_WORLD_TICK.invoker().onStartWorldTick(world);
            }
        }
    }
}
