package ru.geekbrains.lesson1;

import java.util.ArrayList;
import java.util.List;

/**
 * Разбор применения шаблона (wildcard <?>)
 * и принципа PECS (Producer Extends Consumer Super)
 *
 * Несколько полезных ссылок
 * https://habr.com/ru/company/sberbank/blog/416413/
 * https://www.youtube.com/watch?v=mNyQYTp-Njw
 * https://ru.stackoverflow.com/questions/361807/%D0%98%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-wildcard-%D0%B2-generics-java
 *
 */
public class WildcardAndPecs1 {

    public static void main(String[] args) {

        // ----------------------------------------------------------------------------
        // Heap pollution (загрязнение кучи)
        // Объект имеет не тот тип, который ожидается
        String[] strs = {"Some string", "Ha-ha", "!!!"};
        Object[] objs = strs;
        try {
            objs[0] = 125;
        } catch (ArrayStoreException ex) {
            System.out.println("Возникла ошибка");
            ex.printStackTrace();
        }
        // Обобщения (generics) и шаблоны (wildcard <?>) сделаны так, чтобы избежать подобных проблем

        // ----------------------------------------------------------------------------
        // Список List<? extends Number> (граница слева)
        List<? extends Number> extendsWildcardList;

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(1000L);

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.5);
        doubleList.add(27.999);

        // Любой из этих трех списков может быть присвоен переменной extendsWildcardList
        extendsWildcardList = intList;
        extendsWildcardList = longList;
        extendsWildcardList = doubleList;

        // мы можем его проитерировать
        // при этом не важно, какой из возможных массивов ему присвоен
        // все будет корректно
        for (Number num : extendsWildcardList) {
            System.out.println(num);
        }

        // но добавить в него можем только null
        extendsWildcardList.add(null);

        // List<?> => List<? extends Object>
        List<?> lst = new ArrayList<>();
        // lst.add(new Object()); // ошибка компиляции

        // Попытка добавить в него что-то еще потенциально может привести к ошибке
        // с приведением типов
        // extendsWildcardList.add(1); - будет ошибка, если extendsWildCard != intList
        // extendsWildcardList.add(1L); - будет ошибка, если extendsWildCard != longList
        // extendsWildcardList.add(2.999); - будет ошибка, если extendsWildCard != doubleList

        // Вывод wildcard с левой границей идеально подходит в качестве источника данных
        // В него нельзя добавлять новые данные из соображений типобезопасности

        // ----------------------------------------------------------------------------
        // Список List<? super Number> (граница справа)

        // В список List<Object> мы можем положить все что угодно
        // любой класс в Java является Object
        List<Object> objList = new ArrayList<>();
        objList.add(new Object());
        objList.add("Some text");
        objList.add(22);
        objList.add(0.5);
        objList.add(new ArrayList<String>());

        // В список List<Number> мы можем положить объекты всех классов, поражденных от Number
        List<Number> numList = new ArrayList<>();
        numList.add(11);
        numList.add(0.5);
        numList.add(0.77f);

        // Переменной типа List<? super Number> мы можем присвоить либой из
        // выше созданных списков
        List<? super Number> superWildcardList;

        // Очевидно, что в superWildcardList мы можем безопасно добавлять
        // объекты любых классов производных от number
        // при этом не важно какой из возможных списков присвоен этой переменной

        // список объектов Number (все добавляемые значения являются числами)
        superWildcardList = numList;
        superWildcardList.add(11);
        superWildcardList.add(0.5);
        superWildcardList.add(0.77f);

        // или список Object (все добавляемые числа также являются объектами)
        superWildcardList = objList;
        superWildcardList.add(11);
        superWildcardList.add(0.5);
        superWildcardList.add(0.77f);

        // извлексать из такого массива можно только Object
        Object object = superWildcardList.get(0);

        // можно итериовать, но опять же, все элементы будут Object
        for (Object obj : superWildcardList) {
            System.out.println(obj);
        }

        // Вывод - wildcard с границей справа (super) идеально подходит
        // для типобезопасного добавления значений

        copy(numList, objList);
        copy(intList, numList);
        copy(doubleList, objList);
    }

    /**
     * Метод копирования списка с любыми классами произвоными от T
     * Тут используется принцип PECS (Producer Extends Consumer Super)
     *
     * @param source - в source может быть список классов типа T или любого другого
     *               класса, поражденного от T или его потомков
     * @param dest - в dest может быть любой класс, который является суперклассом для T
     *             начиная от Object и заканчивая самим T. Очевидно, что любой элемент извлеченный
     *             из source может быть помещен в dest при полном соблюдении безопасности типов
     * @param <T>
     */
    //
    public static <T> void copy(List<? extends T> source, List<? super T> dest) {
        dest.addAll(source);
    }

}
