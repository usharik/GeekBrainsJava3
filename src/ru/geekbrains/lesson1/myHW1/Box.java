package ru.geekbrains.lesson1.myHW1;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {

    private List<T> fruits = new ArrayList<>();

    public void addFruits(T fruit, int amount) {
        for (int i = 0; i < amount; i++) {
            fruits.add(fruit);
        }
    }

    public double getBoxWeight(){
        if (fruits.size() != 0) return fruits.size() * fruits.get(0).getWeight();
        else return 0;
    }

    public boolean compare(Box<? /*extends Fruit*/> anotherBox){
        return Math.abs(getBoxWeight() - anotherBox.getBoxWeight()) < 0.0001;
        //return getBoxWeight() == anotherBox.getBoxWeight();
    }

    public void fromBox(Box<T> fromBox){
       fruits.addAll(fromBox.fruits);
       fromBox.fruits.clear();
    }
}
