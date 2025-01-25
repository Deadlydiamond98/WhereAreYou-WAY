package net.deadlydiamond98.koalalib.events;

import net.deadlydiamond98.koalalib.util.registry_tools.services.KoalaRegistryMaps;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class KoalaCommonEventsFabric {
    public static void addCreativeTabItems() {
//        KoalaRegistryMaps.registerAndEmpty(KoalaRegistryMaps.CREATIVE_TAB_ENTIRIES, (supplier, creativeModeTab) -> {
//            ResourceKey<CreativeModeTab> tabKey = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(creativeModeTab)
//                    .orElseThrow(() -> new IllegalStateException("Failed to get ResourceKey for CreativeModeTab: " + creativeModeTab));
//
//            ItemGroupEvents.modifyEntriesEvent(tabKey).register(entries -> entries.accept(supplier.get()));
//        });
    }
}
