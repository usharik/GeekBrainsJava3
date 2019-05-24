package ru.geekbrains.lesson5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

    public static void main(String[] args) {
        // Потокобезопасная очередь из неограниченного числа элементов
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

        // Потокобезопасная очередь из неболее чем 100 элементов
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(100);

        // добавляет элемент в очередь
        // если места нет, то возникает исключение IllegalStateException
        blockingQueue.add("asdasd");

        // возвращает true, если элемент удалось добавить (в очереди было место)
        boolean isAdded = blockingQueue.offer("fsdfsdfsdf");

        // добавляет элемент в очередь, если очередь заполнена, то поток блокируется
        // до освобождения места или истечения указанного времени ожидания
        try {
            blockingQueue.offer("asdfsadf", 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // добавляет новый элемент в очередь, если очередь заполнена, то поток блокируется
        // до освобождения места
        try {
            blockingQueue.put("dasfsdf");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // если очередь пуста, то блокирует поток, пока в очереди не появится хотябы один элемент
        try {
            String val = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // если очередь пуста, то блокирует поток на заданное время
        try {
            String val = blockingQueue.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // возвращает null если очередь пуста
        String poll = blockingQueue.poll();
    }
}
