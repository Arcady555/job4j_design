package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LogFilter {

    public static List<String> filter(String file) {
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

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String string : log) {
                out.printf("%s%n", string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}