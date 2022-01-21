package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserMap {

    public static void main(String[] args) {
        User user = new User("Arcady", 3,
                new GregorianCalendar(1972, Calendar.NOVEMBER, 4));
        User otherUser = new User("Arcady", 3,
                new GregorianCalendar(1972, Calendar.NOVEMBER, 4));

        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(otherUser, new Object());

        System.out.println(map);
    }
}
