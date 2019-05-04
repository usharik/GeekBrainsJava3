package ru.geekbrains.lesson1.myHW1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //задания 2 и 1:
        String[] array = {"Ivan" , "Petr", "Julia"};
        //List<String> list = arrayToList(array);
        List<String> list = new ArrayList<>();
        Collections.addAll(list, array);
        swapElements(list,0,2);
        System.out.println(list);


        //задание 3:
        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();
        Box<Orange> box3 = new Box<>();

        box1.addFruits(new Apple(), 6);
        box2.addFruits(new Orange(),7);
        System.out.println("Яблоки: " + box1.getBoxWeight());
        System.out.println("Апельсины: " + box2.getBoxWeight());
        System.out.println(box1.compare(box2));

        box3.fromBox(box2);
        System.out.println(box3.getBoxWeight() + " " + box2.getBoxWeight());

    }

    private static <E> void swapElements(List<E> list, int e1, int e2){
        try {
            E temp1 = list.get(e1);
            E temp2 = list.get(e2);
            list.set(e1, temp2);
            list.set(e2, temp1);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("Last index: " + (list.size()-1));
        }
    }

/*    private static <T> List<T> arrayToList(T array[]){
        List<T> list = new ArrayList<>();
        for (T t : array) list.add(t);
        return list;
    }*/
}
