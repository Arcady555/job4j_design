package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Set<FileProperty> set = new HashSet<>();
    List<Path> listGen = new ArrayList<>();
    List<Path> listRsl = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = toFileProp(file);
        if (set.add(fileProperty)) {
            listGen.add(file);
        } else {
            listRsl.add(file);
        }
        return super.visitFile(file, attrs);
    }

    private FileProperty toFileProp(Path path) {
        return new FileProperty(path.toFile().length(), path.toFile().getName());
    }

    public void printResult() {
        for (Path value : listGen) {
            for (int j = 0; j < listRsl.size(); j++) {
                if (toFileProp(listRsl.get(j)).equals(toFileProp(value))) {
                    listRsl.add(value);
                    break;
                }
            }
        }
        for (Path path : listRsl) {
            System.out.println(path);
        }
    }
}