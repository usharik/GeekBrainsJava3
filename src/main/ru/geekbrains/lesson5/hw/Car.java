package ru.geekbrains.lesson5.hw;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {

    private static int CARS_COUNT = 0;

    private Race race;
    private int speed;
    private CyclicBarrier barrier;
    private AtomicInteger finishCount;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier, AtomicInteger finishCount) {
        this.race = race;
        this.speed = speed;
        this.barrier = barrier;
        this.finishCount = finishCount;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            barrier.await();
            barrier.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            int finishPlace = finishCount.incrementAndGet();
            if (finishPlace == 1) {
                System.out.println(this.name + " ПОБЕДИЛ В ГОНКЕ!!!");
            } else {
                System.out.printf("%s занял %d место%n", this.name, finishPlace);
            }

            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
