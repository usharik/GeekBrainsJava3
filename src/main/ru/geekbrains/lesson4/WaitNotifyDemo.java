package ru.geekbrains.lesson4;

public class WaitNotifyDemo {

    private static volatile char currentLetter = 'A';

    public static void main(String[] args) {
        new Thread(WaitNotifyDemo::printA).start();
        new Thread(WaitNotifyDemo::printB).start();
    }

    private synchronized static void printA() {
        for (int i = 0; i < 3; i++) {
            try {
                while (currentLetter != 'A') {
                    WaitNotifyDemo.class.wait();
                }
                Thread.sleep(100);
                System.out.println("A");
                currentLetter = 'B';
                WaitNotifyDemo.class.notify();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private synchronized static void printB() {
        for (int i = 0; i < 3; i++) {
            try {
                while (currentLetter != 'B') {
                    WaitNotifyDemo.class.wait();
                }
                Thread.sleep(100);
                System.out.println("B");
                currentLetter = 'A';
                WaitNotifyDemo.class.notify();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
