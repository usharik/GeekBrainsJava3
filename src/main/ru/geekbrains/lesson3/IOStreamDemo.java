package ru.geekbrains.lesson3;

import java.io.*;

public class IOStreamDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // считываем файл побайтно с буферизацией
        try (InputStream in = new BufferedInputStream(
                new FileInputStream("data.txt"))) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.println(x);
            }
        }

        // записываем в файл строки и значения примитивных типов
        File dataFile = new File("data_stream.dat");
        dataFile.createNewFile();
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            out.writeUTF("Строка");
            out.writeInt(11);
            out.writeDouble(1.245);
        }

        // считываем ранее записанное
        // важно, что записанное через DataOutputStream может быть считанно только через DataInputStream
        try (DataInputStream out = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            System.out.println(out.readUTF());
            System.out.println(out.readInt());
            System.out.println(out.readDouble());
        }

        // Сериализация. Сохранение в файл произвольного класса
        // ВАЖНО! Сохраняемый класс должен обязательно реализовывать интерфейс Serializable
        // Иначе возникнет NotSerializableException
        File serDataFile = new File("ser_data_stream.dat");
        serDataFile.createNewFile();
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            out.writeObject(new DataClass(12, "Str", 1.2345, 555));
            out.writeInt(11);
            out.writeDouble(1.245);
        }

        try (ObjectInputStream out = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            System.out.println(out.readObject());
        }

        byte byteArr[] = {1, 2, 3, 4, 5, 7};
        try (InputStream in = new ByteArrayInputStream(byteArr)) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.println(x);
            }
        }

        // Только класс RandomAccessFile позволяет читать данные из файла не последовательно
        // Все остальные потоки читают данные ТОЛЬКО ПОСЛЕДОВАТЕЛЬНО
        try (RandomAccessFile raf = new RandomAccessFile("data.txt", "r")) {
            raf.seek(2);
            System.out.println(raf.read());
        }
    }
}
