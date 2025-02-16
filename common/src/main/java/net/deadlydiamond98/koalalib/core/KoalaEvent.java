package net.deadlydiamond98.koalalib.core;

public interface KoalaEvent<T> {
    void register(T listener);
    T invoker();
}
