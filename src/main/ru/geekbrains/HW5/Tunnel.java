package ru.geekbrains.HW5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore narrowness;
    public Tunnel(int numCars) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.narrowness = new Semaphore(numCars);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                narrowness.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                narrowness.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

