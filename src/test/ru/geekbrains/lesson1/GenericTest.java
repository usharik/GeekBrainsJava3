package ru.geekbrains.lesson1;

import org.junit.Test;

public class GenericTest {

    @Test
    public void testSwapArray() {
        Object[] arr = {1, "", 10};
        swap(arr, 0, 1);

        String[] strArr = {"fdsf", "sadasdasd", "asd"};
        swap(strArr, 0, 1);
    }

    public static void swap(Object[] array, int index1, int index2) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
