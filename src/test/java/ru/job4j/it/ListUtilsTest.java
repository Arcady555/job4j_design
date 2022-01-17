package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void removeIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> filter = s -> s == 1;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(0, 2)));
    }

    @Test
    public void replaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> filter = s -> s == 1;
        ListUtils.replaceIf(input, filter, 5);
        assertThat(input, is(Arrays.asList(0, 5, 2)));
    }

    @Test
    public void removeAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(0, 4, 5)));
    }
}