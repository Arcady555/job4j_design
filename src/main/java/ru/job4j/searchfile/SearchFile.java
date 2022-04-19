package ru.job4j.searchfile;

import ru.job4j.io.ArgsName;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.function.Predicate;

public class SearchFile {

    public void search(ArgsName argsName) throws IOException {
        Path path = Path.of(argsName.get("d"));
        Predicate<Path> condition = getCondition(argsName);
        Visitor visitor = new Visitor(condition);
        Files.walkFileTree(path, visitor);
        List<Path> list = visitor.getList();
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("o"))
                ))) {
            for (Path file : list) {
                out.println(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Predicate<Path> getCondition(ArgsName argsName) {
        Predicate<Path> condition;
        if (argsName.get("t").equals("mask")) {
            condition = c -> {
                PathMatcher matcher =
                        FileSystems.getDefault().getPathMatcher("glob:" + argsName.get("n"));
                return matcher.matches(c);
            };
        } else if (argsName.get("t").equals("regex")) {
            condition = c -> {
                PathMatcher matcher =
                        FileSystems.getDefault().getPathMatcher("regex:" + argsName.get("n"));
                return matcher.matches(c);
            };
        } else {
            condition = c -> c.getFileName().toString().equals(argsName.get("n"));
        }
        return condition;
    }

    private void validation(String[] args) {
        ArgsName argsName = ArgsName.of(args);

        if (args.length != 4) {
            throw new IllegalArgumentException("Usage "
                    + "java -jar target/find.jar -d=ROOT_FOLDER -n=SYMBOLS -t=MASK or NAME or REGEX -o=TARGET_FILE");
        }

        if (!(Path.of(argsName.get("d"))).toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", argsName.get("d")));
        }

        if (!(Path.of(argsName.get("d"))).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", argsName.get("d")));
        }

        if (!argsName.get("t").equals("mask") && !argsName.get("t").equals("regex")
                && !argsName.get("t").equals("name")) {
            throw new IllegalArgumentException(String.format("Not correct %s", argsName.get("t")));
        }

        if (!(Path.of(argsName.get("o"))).toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", argsName.get("o")));
        }

        if (!(Path.of(argsName.get("o"))).toFile().isFile()) {
            throw new IllegalArgumentException(String.format("Not file %s", argsName.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        SearchFile searchFile = new SearchFile();
        ArgsName argsName = ArgsName.of(args);
        searchFile.validation(args);
        searchFile.search(argsName);
    }
}