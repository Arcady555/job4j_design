package ru.job4j.collection.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements MyMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int hashCode = key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        float loadFactor = capacity / count;
        if (loadFactor == LOAD_FACTOR) {
            expand();
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash(hash) % (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[table.length * 2];
        for (MapEntry<K, V> element : table) {
            newTable[indexFor(hash(element.key.hashCode()))] = new MapEntry<>(element.key, element.value);
        }
        table = newTable;

    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key.hashCode()));
        modCount++;
        return table[i].value;

    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(key.hashCode()));
        table[i] = null;
        count--;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length - 1;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value
                    + '}';
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(table);
    }
}