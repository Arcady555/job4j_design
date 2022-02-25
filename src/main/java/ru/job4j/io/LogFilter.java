package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LogFilter {

    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        final String string = "404";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            Predicate<String> predicate = s -> {
                String[] strings = s.split(" ");
                return (string.equals(strings[strings.length - 2]));
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
        log.forEach(System.out :: println);
    }
}