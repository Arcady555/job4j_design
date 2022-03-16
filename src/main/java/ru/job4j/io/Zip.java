package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. "
                    + "File-not-for-archive extension not specified. Target folder is null."
                    + "Usage java -jar target/zip.jar ROOT_FOLDER FILE_EXTENSION TARGET_FOLDER");
        }

        if (args.length == 1) {
            throw new IllegalArgumentException("File-not-for-archive extension not specified."
                    + " Target folder is null."
                    + "Usage java -jar target/zip.jar ROOT_FOLDER FILE_EXTENSION TARGET_FOLDER");
        }

        if (args.length == 2) {
            throw new IllegalArgumentException(" Target folder is null."
                    + "Usage java -jar target/zip.jar ROOT_FOLDER FILE_EXTENSION TARGET_FOLDER");
        }

        Path sourcesFolder = Path.of(argsName.get("d"));

        if (!sourcesFolder.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", sourcesFolder));
        }
        if (!sourcesFolder.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", sourcesFolder));
        }

        String extension = argsName.get("e");
        Predicate<Path> predicate = p -> !p.getFileName().endsWith(extension);
        File target = new File(argsName.get("o"));
        List<Path> pathList = new ArrayList<>(Search.search(sourcesFolder, predicate));
        List<File> fileList = new ArrayList<>();
        for (Path path : pathList) {
            fileList.add(path.toFile());
        }
        Zip zip = new Zip();
        zip.packFiles(fileList, target);
    }
}