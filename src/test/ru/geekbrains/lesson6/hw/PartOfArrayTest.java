package ru.geekbrains.lesson6.hw;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PartOfArrayTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[] {5, 6, 7}, new int[] {1, 2, 3, 4, 5, 6, 7}},
                {new int[] {5, 6, 7}, new int[] {1, 4, 3, 4, 5, 6, 7}},
                {new int[] {2, 3, 6, 5, 6, 7}, new int[] {4, 2, 3, 6, 5, 6, 7}},
                {new int[] {}, new int[] {4}},
                {new int[] {3, 55, 77}, new int[] {100, 21, 78, 777, 4, 0, -1, 4, 3, 55, 77}}
        });
    }

    private int[] expected;
    private int[] actual;

    public PartOfArrayTest(int[] expected, int[] actual) {
        this.expected = expected;
        this.actual = actual;
    }

    private PartOfArray partOfArray;

    @Before
    public void init() {
        partOfArray = new PartOfArray();
    }

    @Test
    public void testPartOfArray() {
        Assert.assertArrayEquals(expected, partOfArray.partOfArray(actual));
    }
}
