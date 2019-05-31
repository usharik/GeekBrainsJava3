package ru.geekbrains.lesson6.myHW6;

import java.util.stream.IntStream;

public class Methods {

    public int[] method1(int[] array){

        int lastIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4) lastIndex = i;
        }
        if (lastIndex!= 0) {
            int[] newArray = new int[array.length - lastIndex - 1];
            System.arraycopy(array, lastIndex + 1, newArray, 0, newArray.length);
            return newArray;
        } else throw new RuntimeException();

    }

    public boolean method2 (int[] array){
        //еще не добрался до лямбда-выражений и честно говоря не понимаю можно ли сделать этот код короче
        return IntStream.of(array).anyMatch(x -> x == 1) && IntStream.of(array).anyMatch(x -> x == 4);

    }
}
