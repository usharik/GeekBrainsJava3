package ru.geekbrains.lesson1;


import ru.geekbrains.lesson1.ccc.ChildOfChildOfRoot;
import ru.geekbrains.lesson1.ccc.ChildOfRoot;
import ru.geekbrains.lesson1.ccc.Root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {

    // параметризованный метод
    public static <T> T printType(T val) {
        System.out.println(val.getClass().getName());
        return val;
    }

    public static void main(String[] args) {
        ContainerObj c1 = new ContainerObj("Ha-ha");
        ContainerObj c2 = new ContainerObj("String");
        ContainerObj c3 = new ContainerObj(0.5);
        ContainerObj c4 = new ContainerObj(new Object());

        if (c1.getObj() instanceof Integer) {
            int val = (Integer) c1.getObj();
            System.out.println(val);
        } else {
            System.out.println("Not integer value in container");
        }

        if (c2.getObj() instanceof String) {
            String str = (String) c2.getObj();
            System.out.println(str);
        }

        if (c3.getObj() instanceof Double) {
            Double dval = (Double) c3.getObj();
            System.out.println(dval);
        }

        if (c4.getObj() instanceof Object) {
            Object obj = c4.getObj();
            System.out.println(obj);
        }

        Container<String> strCon = new Container<>("String");
        Container<Integer> intCon = new Container<>(10);
        Container<Object> objCon = new Container<>(new Object());
        Container<Object> objCon1 = new Container<>(10); // !!! Не рекомендуется
        Container rawCon = new Container(100); // !!! Не рекомендуется

        Pair<String, Integer> strIntPair = new Pair<>("Key", 999);

        System.out.println(strCon.getObj());
        System.out.println(intCon.getObj());
        System.out.println(objCon.getObj());
        System.out.println(rawCon.getObj());

        Stat<Integer> intStat = new Stat<>(10, 1, 25, 500);
        Stat<Number> numStat = new Stat(10, 10.0, 10L, 5f);
        Stat rawStat = new Stat(10, 10.0, 10L, 5f); // !!! Не рекомендуется

        System.out.println("Avg = " + intStat.avg());

        printType(new Object());
        printType(10);
        printType("Wow!!!");

        Stat<Integer> intStat1 = new Stat<>(11, 12, 5, 50, 33);
        Stat<Double> doubleStat = new Stat<>(1.0, 7.2, 5.5, 3.0);

        intStat.sameAvg(intStat1);
        intStat.sameAvg(doubleStat);

        // PECS - Producer Extends Consumer Super

        // В массив ничего нельзя добавить. Можно только использоать данные из него
        List<? extends ChildOfRoot> extChildOfRoot = new ArrayList<>();
        extChildOfRoot = new ArrayList<ChildOfRoot>();
        extChildOfRoot = new ArrayList<ChildOfChildOfRoot>();
        // В массив можно только добавлять информацию
        List<? super ChildOfRoot> superChildOfRoot = new ArrayList<>();

        // extChildOfRoot.add(new ChildOfChildOfRoot());
        // extChildOfRoot.add(new ChildOfRoot());
        // extChildOfRoot.add(new Root());
        extChildOfRoot.add(null);

        superChildOfRoot.add(new ChildOfChildOfRoot());
        superChildOfRoot.add(new ChildOfRoot());
        // superChildOfRoot.add(new Root());

        //for (ChildOfRoot item : superChildOfRoot) {
        //
        //}

        methodExtend(new ArrayList<ChildOfRoot>());
        methodExtend(new ArrayList<ChildOfChildOfRoot>());

        methodSuper(new ArrayList<Object>());
        methodSuper(new ArrayList<Root>());
        methodSuper(new ArrayList<ChildOfRoot>());
    }

    private static void methodExtend(List<? extends ChildOfRoot> list) {

    }

    private static void methodSuper(List<? super ChildOfRoot> list) {
        list.add(new ChildOfChildOfRoot());
    }
}
