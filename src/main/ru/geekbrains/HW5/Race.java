package ru.geekbrains.HW5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Race {
    private ArrayList<Stage> stages;
    private CountDownLatch countDownReady;
    private CountDownLatch countDownFinish;
    private String winnerName = null;
    private Lock lockWinnerName = new ReentrantLock();

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(int numCars, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.countDownReady = new CountDownLatch(numCars);
        this.countDownFinish = new CountDownLatch(numCars);
    }

    public void informReady(){
        countDownReady.countDown();
    }

    public void awaitAllReady() throws InterruptedException {
        countDownReady.await();
    }

    public void informFinish(){
        countDownFinish.countDown();
    }

    public void awaitAllFinish() throws InterruptedException {
        countDownFinish.await();
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        if (lockWinnerName.tryLock()) {
            this.winnerName = winnerName;
        }
    }
}
