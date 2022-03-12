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
        listGen.add(file);
        set.add(fileProperty);
        if (!set.add(fileProperty)) {
           addFileIfPresent(listRsl, file);
           if (!addFileIfPresent(listRsl, file)) {
               for (Path path : listGen) {
                   if (path.equals(file)) {
                       listRsl.add(path);
                   }
               }
               listRsl.add(file);
           }
        }
        return super.visitFile(file, attrs);
    }

    private FileProperty toFileProp(Path path) {
        return new FileProperty(path.toFile().length(), path.toFile().getName());
    }

    private boolean addFileIfPresent(List<Path> list, Path path) {
        boolean rsl = false;
        FileProperty fp = toFileProp(path);
        for (int i = 0; i < list.size(); i++) {
            if (toFileProp(list.get(i)).equals(fp)) {
                listRsl.add(i, path);
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}