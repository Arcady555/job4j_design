package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.Predicate;

public class LogFilter {
    public void filter(String file) {
        final String string = "404";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            Predicate<String> predicate = s -> {
                String[] strings = s.split(" ");
                return (string.equals(strings[strings.length - 2]));
            };
            bufferedReader.lines().filter(predicate).forEach(System.out :: println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        logFilter.filter("log.txt");
    }
}