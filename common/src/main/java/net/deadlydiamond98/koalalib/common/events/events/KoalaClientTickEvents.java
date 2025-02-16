package net.deadlydiamond98.koalalib.common.events.events;

import net.deadlydiamond98.koalalib.core.KoalaEvent;
import net.deadlydiamond98.koalalib.core.KoalaEventFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

public class KoalaClientTickEvents {
    private KoalaClientTickEvents() {}

    public static final KoalaEvent<StartTick> START_CLIENT_TICK = KoalaEventFactory.createArrayBacked(
            StartTick.class,
            listeners -> client -> {
                for (StartTick listener : listeners) {
                    listener.onStartTick(client);
                }
            }
    );

    public static final KoalaEvent<EndTick> END_CLIENT_TICK = KoalaEventFactory.createArrayBacked(
            EndTick.class,
            listeners -> client -> {
                for (EndTick listener : listeners) {
                    listener.onEndTick(client);
                }
            }
    );

    public static final KoalaEvent<StartWorldTick> START_WORLD_TICK = KoalaEventFactory.createArrayBacked(
            StartWorldTick.class,
            listeners -> world -> {
                for (StartWorldTick listener : listeners) {
                    listener.onStartWorldTick(world);
                }
            }
    );

    public static final KoalaEvent<EndWorldTick> END_WORLD_TICK = KoalaEventFactory.createArrayBacked(
            EndWorldTick.class,
            listeners -> world -> {
                for (EndWorldTick listener : listeners) {
                    listener.onEndWorldTick(world);
                }
            }
    );

    @FunctionalInterface
    public interface StartTick {
        void onStartTick(Minecraft client);
    }

    @FunctionalInterface
    public interface EndTick {
        void onEndTick(Minecraft client);
    }

    @FunctionalInterface
    public interface StartWorldTick {
        void onStartWorldTick(ClientLevel world);
    }

    @FunctionalInterface
    public interface EndWorldTick {
        void onEndWorldTick(ClientLevel world);
    }
}
