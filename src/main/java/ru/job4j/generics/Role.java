package ru.job4j.generics;

public class Role extends Base {

    private final int rolenumber;

    public Role(String id, int number) {
        super(id);
        this.rolenumber = number;
    }

    public int getRolenumber() {
        return rolenumber;
    }
}