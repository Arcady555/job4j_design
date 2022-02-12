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
        String getValue1 = simpleMap.get(4556);
        Assert.assertEquals(getValue1, "Arcady");
        String getValue2 = simpleMap.get(3426);
        Assert.assertEquals(getValue2, "Masha");
        String getValue3 = simpleMap.get(32456);
        Assert.assertEquals(getValue3, "Sasha");
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
    public void getNull() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(4556, "Arcady");
        simpleMap.put(3426, "Masha");
        simpleMap.put(32456, "Sasha");
        String getValue = simpleMap.get(4656);
        Assert.assertNull(getValue);
    }

    @Test
    public void remove() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(4556, "Arcady");
        simpleMap.put(3426, "Masha");
        simpleMap.put(32456, "Sasha");
        simpleMap.put(76565, "Pasha");
        simpleMap.remove(4556);
        String getValue = simpleMap.get(4556);
        Assert.assertNull(getValue);
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