package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String[] filters = argsName.get("filter").split(",");
        List<Integer> indexes = new ArrayList<>();
        List<String> rsl = new ArrayList<>();
        try (var scanner = new Scanner(new BufferedReader(new FileReader(argsName.get("path"))))) {
            String[] firstLine = scanner.nextLine().split(argsName.get("delimiter"));
            for (int i = 0; i < firstLine.length; i++) {
                for (String filter : filters) {
                    if (firstLine[i].equals(filter)) {
                        rsl.add(firstLine[i]);
                        rsl.add(";");
                        indexes.add(i);
                    }
                }
            }
            rsl.remove(rsl.size() - 1);
            rsl.add(System.lineSeparator());
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(";");
                for (int index = 0; index < indexes.size(); index++) {
                    rsl.add(line[index]);
                    if (index < indexes.size() - 1) {
                        rsl.add(";");
                    } else {
                        rsl.add(System.lineSeparator());
                    }
                }
            }
            if (!"stdout".equals(argsName.get("out"))) {
                PrintStream printStream = new PrintStream(argsName.get("out"));
                for (String string : rsl) {
                    printStream.print(string);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("stdout".equals(argsName.get("out"))) {
            for (String string : rsl) {
                System.out.print(string);
            }
        }
    }

    private static void validation(String[] args) throws FileNotFoundException {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Usage java -jar dir.jar -path=FILE -delimiter=SYMBOL"
                    + "  -out=FILE -filter=PARAMETER");
        }
        if (!new File(argsName.get("Path")).exists()) {
            throw new FileNotFoundException("File with specified path name does not exist");
        }
        if (!(argsName.get("Path")).endsWith(".csv")) {
            throw new IllegalArgumentException("File must have CSV-extension");
        }
    }

    public static void main(String[] args) throws Exception {
        validation(args);
        CSVReader.handle(ArgsName.of(args));
    }
}