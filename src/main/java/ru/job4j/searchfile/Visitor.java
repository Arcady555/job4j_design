package ru.job4j.searchfile;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Visitor extends SimpleFileVisitor<Path> {

    private final Predicate<Path> condition;
    private final List<Path> list = new ArrayList<>();

    public Visitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            list.add(file.getFileName());
        }
        return super.visitFile(file, attrs);
    }
}