package ru.job4j.collection.set;

import ru.job4j.collection.arraylist.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (checkUnique(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        return !checkUnique(value);
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    private boolean checkUnique(T value) {
        boolean rsl = true;
        for (T element : set) {
            if (Objects.equals(value, element)) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

}
