package ru.geekbrains.lesson1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.lesson6.myHW6.Methods;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class MethodsTest1 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new int[][][] {
                {{1, 4, 1}, {1}},
                {{1, 2, 3, 4, 5, 6, 7}, {5, 6, 7}},
                {{1, 2, 3, 4}, {}}
        });
    }

    private int[] firstArr;
    private int[] secondArr;

    public MethodsTest1(int[] firstArr, int[] secondArr) {
        this.firstArr = firstArr;
        this.secondArr = secondArr;
    }

    private Methods methods;

    @Before
    public void init() {
        methods = new Methods();
    }

    @Test
    public void massTest() {
        Assert.assertArrayEquals(secondArr, methods.method1(firstArr));
    }

    @Test(expected = RuntimeException.class)
    public void ex() {
        methods.method1(new int[]{1, 2, 3});
    }
}