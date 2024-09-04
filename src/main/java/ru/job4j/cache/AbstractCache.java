package ru.job4j.cache;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> valueSoftReference = new SoftReference<>(value);
        cache.put(key, valueSoftReference);
    }

    @Nullable
    public final V get(K key) {
        if (!cache.containsKey(key) || cache.get(key).refersTo(null)) {
            V fileEntity = load(key);
            if (fileEntity != null) {
                put(key, fileEntity);
            }
        }
        return cache.getOrDefault(key, new SoftReference<>(null)).get();
    }

    protected abstract V load(K key);
}