package ru.geekbrains.lesson5;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

    public static void main(String[] args) {
        AtomicInteger val = new AtomicInteger(0);
        int i = val.incrementAndGet(); // ++i pre increment
        i = val.getAndIncrement(); // i++ post increment

        int j = 0;
        System.out.println(j++ + " " + j);
        j = 0;
        System.out.println(++j + " " + j);
    }
}
