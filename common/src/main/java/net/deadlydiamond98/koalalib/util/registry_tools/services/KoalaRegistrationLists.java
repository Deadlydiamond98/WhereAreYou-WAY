package net.deadlydiamond98.koalalib.util.registry_tools.services;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.deadlydiamond98.koalalib.util.registry_tools.MultiModClientRegistries;
import net.deadlydiamond98.koalalib.util.registry_tools.MultiModRegistries;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * This class intended for internal use only and should not be accessed directly.
 *
 * Instead of using this class directly, refer to the MultiModRegistry classes such as
 * {@link MultiModClientRegistries} or {@link MultiModRegistries} for adding things to
 * these lists.
 */

public class KoalaRegistrationLists {
    // Block Render Layers
    public static final Map<Supplier<? extends Block>, RenderType> BLOCK_RENDER_LAYERS = new Reference2ObjectOpenHashMap<>();
    // Entity Renderers
    public static final Map<Supplier<? extends EntityType<? extends Entity>>, EntityRendererProvider<? super Entity>> ENTITY_RENDERERS = new Reference2ObjectOpenHashMap<>();
    // Block Entity Renderers
    public static final Map<Supplier<? extends BlockEntityType<? extends BlockEntity>>, BlockEntityRendererProvider<? super BlockEntity>> BLOCK_ENTITY_RENDERERS = new Reference2ObjectOpenHashMap<>();
    // Item Model Predicates
//    public static final ArrayList<ItemModelPredicates> ITEM_MODEL_PREDICATES = new ArrayList<>();

    /**
     * Registers what the registryMap contains, and then clears it
     *
     * @param registryMap The Map to register
     * @param action The Register action
     */
    public static <K, V> void registerAndEmpty(Map<K, V> registryMap, BiConsumer<K, V> action) {
        registryMap.forEach(action);
        registryMap.clear();
    }

    public record ItemModelPredicates(Supplier<Item> item, ResourceLocation resourceLocation, ClampedItemPropertyFunction function) {}
}
