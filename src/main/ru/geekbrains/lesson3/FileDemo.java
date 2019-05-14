package ru.geekbrains.lesson3;

import java.io.File;

public class FileDemo {

    public static void main(String[] args) {
        File file = new File("data.txt");

        if (file.exists()) {
            System.out.printf("Файл data.txt существует! Размер файла: %d%n", file.length());
        }

        // создаем новую папку внутри текущей папки
        File newDir = new File("./new_dir");
        if (!newDir.exists()) {
            System.out.println("Создаем новую папку new_dir");
            newDir.mkdir();
        }

        System.out.println();
        System.out.println("Список файлов в текущей папке");
        File dir = new File("."); // путь к текущей папке
        for (String fname : dir.list()) {
            System.out.println(fname);
        }
    }
}
