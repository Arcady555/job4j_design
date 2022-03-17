package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validation(String[] args, Path path) {
        ArgsName argsName = ArgsName.of(args);

        if (args.length < 3) {
            throw new IllegalArgumentException("Usage "
                    + "java -jar target/zip.jar ROOT_FOLDER FILE_EXTENSION TARGET_FOLDER");
        }

        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", path));
        }

        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", path));
        }

        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("File-not-for-archive extension not specified");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        Path sourcesFolder = Path.of(argsName.get("d"));
        zip.validation(args, sourcesFolder);
        String extension = argsName.get("e");
        Predicate<Path> predicate = p -> !p.getFileName().endsWith(extension);
        File target = new File(argsName.get("o"));
        List<Path> list = new ArrayList<>(Search.search(sourcesFolder, predicate));
        zip.packFiles(list, target);
    }
}