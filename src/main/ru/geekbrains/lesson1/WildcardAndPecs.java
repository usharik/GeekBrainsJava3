package ru.geekbrains.lesson1;


import java.util.ArrayList;
import java.util.List;

public class WildcardAndPecs {

    public static void main(String[] args) {
        Number[] numArr = new Number[100];

        Integer[] intArr = {1, 2, 3, 4};
        Long[] longArr = {1L, 2L, 3L, 4L};
        Double[] doubleArr = {1.1, 1.2, 2.5, 3.7};
        Float[] floatArr = {1.1f, 1.5f, 100.99f, 99.9999f};

        printNumbers(intArr);
        printNumbers(longArr);
        printNumbers(doubleArr);
        printNumbers(floatArr);

        try {
            changeNumbers(intArr);
        } catch (ArrayStoreException ex) {
            ex.printStackTrace();
        }

        try {
            changeNumbers(longArr);
        } catch (ArrayStoreException ex) {
            ex.printStackTrace();
        }

        List<Number> numList;
        List<Integer> intList = new ArrayList<>();
    }

    public static void printNumbers(Number[] numArr) {
        for (Number num : numArr) {
            System.out.println(num);
        }
    }

    public static void changeNumbers(Number[] numArr) {
        numArr[0] = 1;
        numArr[1] = 1L;
        numArr[2] = 1.1;
        numArr[3] = 1.1f;
    }

    public static void printNumbers(List<? extends Number> numList) {
        for (Number num : numList) {
            System.out.println(num);
        }
    }

    public static void fillNumList(List<? super Number> numList) {
        numList.add(1);
        numList.add(1L);
        numList.add(5.5);
        numList.add(99.99f);
    }

    // List<?> == List<? extends Object>
    public static void numListMethod(List<?> numbers) {
        // numbers.add(1); // ошибка компиляции
    }

    public static void numListRawMethod(List numbers) {
        numbers.add(1);
        numbers.add(new Object());
        numbers.add(new ArrayList<>());
    }
}
