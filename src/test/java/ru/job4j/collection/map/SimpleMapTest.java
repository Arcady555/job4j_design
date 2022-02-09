package ru.job4j.collection.map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleMapTest {

    @Test
    public void put() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(4556, "Arcady");
        simpleMap.put(3426, "Masha");
        simpleMap.put(32456, "Sasha");
        simpleMap.put(4556, "Arcady1");
        simpleMap.put(76565, "Pasha");
        String simpleMapString = simpleMap.toString();
        String simpleMapString1 = "[null, null, null, ru.job4j.collection.map.SimpleMap$MapEntry@2471cca7,"
                + " ru.job4j.collection.map.SimpleMap$MapEntry@5fe5c6f, null,"
                + " ru.job4j.collection.map.SimpleMap$MapEntry@6979e8cb, null]";
        Assert.assertEquals(simpleMapString, simpleMapString1);
    }

    @Test
    public void get() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(4556, "Arcady");
        simpleMap.put(3426, "Masha");
        simpleMap.put(32456, "Sasha");
        String getValue = simpleMap.get(4556);
        Assert.assertEquals(getValue, "Arcady");
    }

    @Test
    public void remove() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(4556, "Arcady");
        simpleMap.put(3426, "Masha");
        simpleMap.put(32456, "Sasha");
        simpleMap.put(76565, "Pasha");
        simpleMap.remove(4556);
        String simpleMapString = simpleMap.toString();
        String simpleMapString1 = "[null, null, null, ru.job4j.collection.map.SimpleMap$MapEntry@2471cca7,"
                + " ru.job4j.collection.map.SimpleMap$MapEntry@5fe5c6f, null,"
                + " null, null]";
        Assert.assertEquals(simpleMapString, simpleMapString1);
    }

    @Test
    public void iterator() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(4556, "Arcady");
        simpleMap.put(3426, "Masha");
        simpleMap.put(32456, "Sasha");
        Iterator<Integer> first = simpleMap.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(3426));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(32456));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(4556));
        assertThat(first.hasNext(), Is.is(false));
    }
}