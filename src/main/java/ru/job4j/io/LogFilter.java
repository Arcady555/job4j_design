package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            Predicate<String> predicate = s -> {
                String[] strings = s.split(" ");
                return (strings[strings.length - 2].equals("404"));
            };
            list = bufferedReader.lines().filter(predicate).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}