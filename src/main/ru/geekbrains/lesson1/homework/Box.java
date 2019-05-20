package ru.geekbrains.lesson1.homework;

import ru.geekbrains.lesson1.homework.fruit.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public double getWeight() {
        double sum=0;
        for (Fruit fr : fruits) {
            sum += fr.getWeight();
        }
        return sum;
    }

    public boolean compare(Box<? extends Fruit> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    public void moveFromBox(Box<T> box) {
        fruits.addAll(box.fruits);
        box.fruits.clear();
    }
}
