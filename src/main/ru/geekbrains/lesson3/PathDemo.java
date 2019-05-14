package ru.geekbrains.lesson3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data.txt");
        File file = path.toFile();

        if (Files.exists(path)) {
            System.out.printf("Файл data.txt существует! Размер файла %d%n", Files.size(path));
        }

        Path currentDir = Paths.get(".");
        Files.list(currentDir).forEach(System.out::println);
    }
}
