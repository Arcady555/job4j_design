package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] numbers = text.toString().split(System.lineSeparator());
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                if (num % 2 == 0) {
                    System.out.println(num + " is even");
                } else {
                    System.out.println(num + " isn't even");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}