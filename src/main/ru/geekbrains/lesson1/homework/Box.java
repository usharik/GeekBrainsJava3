package ru.geekbrains.lesson1.homework;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> fruits = new ArrayList<>();

    public void addFruit(T fruit, int amount){
        for (int i=0; i < amount; i++){
            fruits.add(fruit);
        }

    }
    public Float getBoxWeight(){
        Float weight = 0f;
        for (Fruit f: fruits) {
            weight += f.getWeight();
        }
        return weight;
    }

    public boolean compair(Box<?> box){
        return Math.abs(this.getBoxWeight() - box.getBoxWeight()) < 0.0001;
    }

    public void moveFruits(Box<T> box){
        fruits.addAll(box.fruits);
        box.fruits.clear();
    }

}
