package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("MultiplicationTable.txt")) {
            out.write("1 2 3 4 5 6 7 8 9".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("2 4 6 8 10 12 14 16 18").getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("3 6 9 12 15 18 21 24 27").getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("4 8 12 16 20 24 28 32 36").getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("5 10 15 20 25 30 35 40 45").getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("6 12 18 24 30 36 42 48 54").getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("7 14 21 28 35 42 49 56 63").getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("8 16 24 32 40 48 56 64 72").getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("9 18 27 36 45 54 63 72 81").getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
