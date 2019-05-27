package ru.geekbrains.lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SyncObjectsDemo {

    public static Semaphore semaphore = new Semaphore(5);

    public static CountDownLatch latch = new CountDownLatch(5);

    public static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        for (int i=0; i<20; i++) {
            new Thread(SyncObjectsDemo::useCyclicBarrier).start();
        }
    }

    public static void useSemaphore() {
        try {
            System.out.printf("Поток %d ожидает доступа%n", Thread.currentThread().getId());
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.printf("Поток %d завершен%n", Thread.currentThread().getId());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void useLatch() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.printf("Поток %d завершен%n", Thread.currentThread().getId());
    }

    public static void useCyclicBarrier() {
        System.out.printf("Поток %d ожидает доступа%n", Thread.currentThread().getId());
        try {
            Thread.sleep(100 + (int) (3000 * Math.random()));
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.printf("Поток %d завершен%n", Thread.currentThread().getId());
    }
}
