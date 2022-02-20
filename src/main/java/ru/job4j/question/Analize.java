package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        int add = info.getAdded();
        int change = info.getChanged();
        int delete = info.getDeleted();
        Map<Integer, String> prevUsers = new HashMap<>();
        for (User user : previous) {
            prevUsers.put(user.getId(), user.getName());
        }
        Map<Integer, String> curUsers = new HashMap<>();
        for (User user : current) {
            curUsers.put(user.getId(), user.getName());
        }
        for (Integer element : curUsers.keySet()) {
            if (prevUsers.get(element) == null) {
                add++;
                info.setAdded(add);
            }
            if (prevUsers.get(element) != null && !prevUsers.get(element).equals(curUsers.get(element))) {
                change++;
                info.setChanged(change);
            }
        }
        for (Integer element : prevUsers.keySet()) {
            if (curUsers.get(element) == null) {
                delete++;
                info.setDeleted(delete);
            }
        }
        return info;
    }
}