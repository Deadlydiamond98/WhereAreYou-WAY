package net.deadlydiamond98.koalalib.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class KoalaEventFactory {
    private KoalaEventFactory() {}

    public static <T> KoalaEvent<T> createArrayBacked(Class<T> listenerType, Function<T[], T> invokerFactory) {
        return new KoalaEvent<>() {
            private final List<T> listeners = new ArrayList<>();
            private T invoker = null;

            @Override
            public void register(T listener) {
                listeners.add(listener);
                invoker = null;
            }

            @Override
            public T invoker() {
                if (invoker == null) {
                    T[] listenerArray = (T[]) Array.newInstance(listenerType, listeners.size());
                    listenerArray = listeners.toArray(listenerArray);

                    invoker = invokerFactory.apply(listenerArray);
                }
                return invoker;
            }
        };
    }
}
