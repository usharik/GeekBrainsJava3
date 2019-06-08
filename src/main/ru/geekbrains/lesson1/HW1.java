package ru.geekbrains.lesson1;

import ru.geekbrains.lesson1.hw_classes.Apple;
import ru.geekbrains.lesson1.hw_classes.Orange;

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

        /**
         * Большая задача:
         *
         * Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
         * Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
         * поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
         * Для хранения фруктов внутри коробки можно использовать ArrayList;
         * Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
         * (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
         * Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
         * которую подадут в compare в качестве параметра,
         * true – если она равны по весу,
         * false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
         * Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
         * (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
         * Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
         * Не забываем про метод добавления фрукта в коробку.
         */

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> emptyAppleBox = new Box<>();
        Box<Orange> emptyOrangeBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());


        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(emptyAppleBox.getWeight());

        System.out.println(appleBox.compare(orangeBox));
        System.out.println(orangeBox.compare(emptyAppleBox));

        appleBox.emptyBoxTo(emptyAppleBox);
        System.out.println(appleBox.getWeight());
        System.out.println(emptyAppleBox.getWeight());

        emptyOrangeBox.add(new Orange());
        System.out.println(emptyOrangeBox.getWeight());
        orangeBox.emptyBoxTo(emptyOrangeBox);
        System.out.println(orangeBox.getWeight());
        System.out.println(emptyOrangeBox.getWeight());

    }
}
