package ru.geekbrains.lesson4;

public class WaitNotifyDemo {

    private static volatile char currentLetter = 'A';

    public static void main(String[] args) {
        new Thread(WaitNotifyDemo::printA).start();
        new Thread(WaitNotifyDemo::printB).start();
        new Thread(WaitNotifyDemo::printC).start();
    }

    private synchronized static void printA() {
        for (int i = 0; i < 5; i++) {
            try {
                while (currentLetter != 'A') {
                    WaitNotifyDemo.class.wait();
                }
                Thread.sleep(30);
                System.out.print("A");
                currentLetter = 'B';
                WaitNotifyDemo.class.notifyAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private synchronized static void printB() {
        for (int i = 0; i < 5; i++) {
            try {
                while (currentLetter != 'B') {
                    WaitNotifyDemo.class.wait();
                }
                Thread.sleep(70);
                System.out.print("B");
                currentLetter = 'C';
                WaitNotifyDemo.class.notifyAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private synchronized static void printC() {
        for (int i = 0; i < 5; i++) {
            try {
                while (currentLetter != 'C') {
                    WaitNotifyDemo.class.wait();
                }
                Thread.sleep(50);
                System.out.print("C");
                currentLetter = 'A';
                WaitNotifyDemo.class.notifyAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
