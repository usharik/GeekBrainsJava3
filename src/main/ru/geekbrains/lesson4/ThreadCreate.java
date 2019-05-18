package ru.geekbrains.lesson4;

public class ThreadCreate {

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hi from Thread");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hi from Thread");
            }
        }).start();

        new Thread(() -> System.out.println("Hi from Thread")).start();
    }
}
