package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. File extension not specified. "
                    + "Usage java -jar dir.jar ROOT_FOLDER, FILE_EXTENSION");
        }

        if (args.length == 1) {
            throw new IllegalArgumentException("File extension not specified. "
                    + "Usage java -jar dir.jar ROOT_FOLDER, FILE_EXTENSION");
        }
        File file = new File(args[0]);
        String string = args[1];
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            if (subfile.getAbsolutePath().endsWith(string)) {
                System.out.println(subfile);
            }
        }
    }
}