package ru.geekbrains.lesson1;

import ru.geekbrains.lesson1.hw_classes.Fruit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Box<T extends Fruit> {
    private ArrayList<T> arrayList = new ArrayList<>();

    public float getWeight() {
        if (arrayList.size() > 0) {
            return arrayList.size() * arrayList.get(0).getOneItemWeight();
        } else {
            return 0.0f;
        }
    }

    public void add(T element) {
        arrayList.add(element);
    }

    public boolean compare(Box<? extends Fruit> that) {
        return this.getWeight() == that.getWeight();
    }

    public void emptyBoxTo(Box<T> that) {
        for (T el : arrayList
        ) {
            that.add(el);
        }
        arrayList.clear();
    }
}

