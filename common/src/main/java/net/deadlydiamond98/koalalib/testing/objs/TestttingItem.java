package net.deadlydiamond98.koalalib.testing.objs;

import net.deadlydiamond98.koalalib.common.content.KoalaLibSounds;
import net.deadlydiamond98.koalalib.common.items.properties.Glowing;
import net.deadlydiamond98.koalalib.common.items.properties.NoGravity;
import net.deadlydiamond98.koalalib.common.items.properties.PickupSound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public class TestttingItem extends Item implements NoGravity, PickupSound, Glowing {
    public TestttingItem(Properties $$0) {
        super($$0);
    }

    @Override
    public SoundEvent getSound() {
        return KoalaLibSounds.BAGEL.get();
    }
}
