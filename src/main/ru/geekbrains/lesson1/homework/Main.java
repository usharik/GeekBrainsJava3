package ru.geekbrains.lesson1.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

    }

    public static <T> List<T> arrToList(T[] arr) {
        List<T> res = new ArrayList<>();
        Collections.addAll(res, arr);
        return res;
    }
}
