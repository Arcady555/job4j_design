package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = container.length != 0
                    ? Arrays.copyOf(container, container.length * 2)
                    : Arrays.copyOf(container, 10);
        }
            container[size] = value;
            size++;
            modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T value = container[index];
        Objects.checkIndex(index, size);
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        T value = container[index];
        Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1,
                    container, index, size - index - 1);
        size--;
        modCount++;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}