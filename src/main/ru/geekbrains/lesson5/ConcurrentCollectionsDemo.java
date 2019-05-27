package ru.geekbrains.lesson5;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollectionsDemo {

    public static void main(String[] args) {
        Map<String, String> threadSafeMap = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> concurrentMap = new ConcurrentHashMap<>(); // аналог HashMap
        Map<String, String> concurrentSkipMap = new ConcurrentSkipListMap<>(); // аналог LinkedHashMap

        List<String> threadSafeList = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    }
}