package ru.geekbrains.lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.lesson6.myHW6.Methods;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MethodsTest2 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 1, 1}, false},
                {new int[]{1, 1, 4}, true},
                {new int[]{4, 4, 4}, false}
        });
    }

    private int[] arr;
    private boolean result;

    public MethodsTest2(int[] arr, boolean result) {
        this.arr= arr;
        this.result = result;
    }

    private Methods methods;

    @Before
    public void init() {
        methods = new Methods();
    }

    @Test
    public void massTest() {
        Assert.assertEquals(methods.method2(arr), result);
    }

}