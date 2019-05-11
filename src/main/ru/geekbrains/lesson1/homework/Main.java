package ru.geekbrains.lesson1.homework;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // задание 1
        String[] array = {"Ivan" , "Petr", "Julia"};
        List<String> list = arrayToList(array);
        System.out.println(list);
        // задание 2
        changeElements(list,0,2);
        System.out.println(list);

        // задание 3

        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();
        Box<Apple> box3 = new Box<>();

        box1.addFruit(new Apple(), 6);
        box2.addFruit(new Orange(), 4);
        box3.addFruit(new Apple(), 2);

        System.out.println("Коробка 1 весит: " + box1.getBoxWeight());
        System.out.println("Коробка 2 весит: " + box2.getBoxWeight());
        System.out.println("Коробка 3 весит: " + box3.getBoxWeight());

        System.out.println(box1.compair(box2));
        System.out.println(box2.compair(box3));
        System.out.println(box3.compair(box1));

        box1.moveFruits(box3);
        System.out.println("Коробка 1 весит: " + box1.getBoxWeight());
        System.out.println("Коробка 3 весит: " + box3.getBoxWeight());

    }
    private static <R> List<R> arrayToList(R arry[]){
        List<R> list = new ArrayList<>();
        for (R r:arry) list.add(r);
        return list;
    }
    private static <Z> void changeElements(List<Z> list, int elem1, int elem2){
        Z temp = list.get(elem1);
        list.set(elem1, list.get(elem2));
        list.set(elem2, temp);
    }
}
