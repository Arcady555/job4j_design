package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analizy {
    private static boolean serverOn = true;
    public void unavailable(String source, String target)  {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            read.lines().forEach(s -> {
                String print = "";
                String[] string = s.split(" ");
                if (Objects.equals(string[0], "400") || Objects.equals(string[0], "500")) {
                    if (serverOn) {
                        print = string[1] + ";";
                        out.print(print);
                        serverOn = false;
                    } else {
                        out.print("");
                    }
                } else {
                    if (serverOn) {
                        out.print("");
                    } else {
                        print = string[1];
                        out.printf("%s%n", print);
                        serverOn = true;
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}