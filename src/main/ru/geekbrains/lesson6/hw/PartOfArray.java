package ru.geekbrains.lesson6.hw;

import java.util.Arrays;

public class PartOfArray {
    public int[] partOfArray(int[] arr) {
        for (int i=arr.length-1; i>=0; i--) {
            if (arr[i] == 4) {
                return Arrays.copyOfRange(arr, i+1, arr.length);
            }
        }

        throw new RuntimeException("Digit 4 is not found in the array");
    }
}
