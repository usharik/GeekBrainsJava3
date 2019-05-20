package ru.geekbrains.lesson4;

public class DeadlockDemo {

    private static final Object res1 = new Object();
    private static final Object res2 = new Object();

    public static void main(String[] args) {
        new Thread(DeadlockDemo::process1).start();
        new Thread(DeadlockDemo::process2).start();
    }

    private static void process1() {
        synchronized (res1) {
            System.out.println("Процес 1 захватил ресурс 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Процес 1 пробует захватить ресурс 2");
            synchronized (res2) {
                System.out.println("Процес 1 захватил ресурс 2");
            }
        }
    }

    private static void process2() {
        synchronized (res2) {
            System.out.println("Процес 2 захватил ресурс 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Процес 2 пробует захватить ресурс 1");
            synchronized (res1) {
                System.out.println("Процес 2 захватил ресурс 1");
            }
        }
    }
}
