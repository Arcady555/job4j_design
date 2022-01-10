package ru.job4j.generics;

public class RoleStore implements Store<Role> {
    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role module) {
        store.add(module);
    }

    @Override
    public boolean replace(String id, Role module) {
        return store.replace(id, module);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
}