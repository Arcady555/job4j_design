package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenumberIs67() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 67));
        Role result = store.findById("1");
        assertThat(result.getRolenumber(), is(67));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 67));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenumberIs67() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 67));
        store.add(new Role("1", 76));
        Role result = store.findById("1");
        assertThat(result.getRolenumber(), is(67));
    }

    @Test
    public void whenReplaceThenRolenumberIs76() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 67));
        store.replace("1", new Role("1", 76));
        Role result = store.findById("1");
        assertThat(result.getRolenumber(), is(76));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolenumber() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 67));
        store.replace("10", new Role("10", 76));
        Role result = store.findById("1");
        assertThat(result.getRolenumber(), is(67));
    }

    @Test
    public void whenDeleteUserThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 67));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenRolenumberIs67() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 67));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolenumber(), is(67));
    }
}