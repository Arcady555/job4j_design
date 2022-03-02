package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Analizy {
    public void unavailable(String source, String target)  {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            ArrayList<String[]> strings = new ArrayList<>();
            StringBuilder print;
            read.lines().forEach(s -> {
                String[] string = s.split(" ");
                strings.add(string);
            });
            for (int i = 0; i < strings.size(); i++) {
                if (Objects.equals(strings.get(i)[0], "400")
                        || Objects.equals(strings.get(i)[0], "500")) {
                    print = new StringBuilder(strings.get(i)[1] + ";");
                    for (int j = i++; j < strings.size(); j++) {
                        if (!Objects.equals(strings.get(j)[0], "400")
                                && !Objects.equals(strings.get(j)[0], "500")) {
                            print.append(strings.get(j)[1]);
                            i = j;
                            break;
                        }
                    }
                    out.println(print);
                }
            }
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
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "target.txt");
    }
}