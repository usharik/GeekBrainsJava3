package ru.geekbrains.lesson5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.tryLock();

        lock.lock();

        lock.unlock();
    }
}
