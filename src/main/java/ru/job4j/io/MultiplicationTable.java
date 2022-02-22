package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class MultiplicationTable {
    public static void main(String[] args) {
        int[][] data = multiple(9);
        try (FileOutputStream out = new FileOutputStream("MultiplicationTable.txt")) {
            for (int[] datum : data) {
                out.write(Arrays.toString(datum).getBytes());
                out.write(System.lineSeparator().getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[][] multiple(int size) {
        int[][] data = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = (i + 1) * (j + 1);
            }
        }
        return data;
    }
}
