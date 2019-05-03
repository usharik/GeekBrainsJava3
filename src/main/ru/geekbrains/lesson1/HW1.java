package ru.geekbrains.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HW1 {

    /**
     * Написать метод, который меняет два элемента массива местами
     * (массив может быть любого ссылочного типа);
     */
    public static <T> void shift(T[] array, int i1, int i2) {
        if (i1 >= array.length || i2 >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    /**
     * Написать метод, который преобразует массив в ArrayList;
     */
    public static <T> ArrayList<T> toArrayList(T... a) {
        ArrayList<T> result = new ArrayList<>();
        for (T el : a
        ) {
            result.add(el);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] arrayInt = new Integer[]{0, 1, 2, 3, 4, 5};

        for (Integer el : arrayInt
        ) {
            System.out.printf("%d ", el);
        }
        shift(arrayInt, 3, 5);
        System.out.println();
        for (Integer el : arrayInt
        ) {
            System.out.printf("%d ", el);
        }

        System.out.println();

        String[] arrayStr = new String[]{"zero", "one", "two", "three", "four", "five"};
        for (String el : arrayStr
        ) {
            System.out.printf("%s ", el);
        }
        shift(arrayStr, 0, 5);
        System.out.println();
        for (String el : arrayStr
        ) {
            System.out.printf("%s ", el);
        }
        System.out.println();

        ArrayList<Integer> arrayListInt = toArrayList(arrayInt);
        System.out.println(arrayListInt.toString());

        ArrayList<String> arrayListStr = toArrayList(arrayStr);
        System.out.println(arrayListStr.toString());
    }
}
