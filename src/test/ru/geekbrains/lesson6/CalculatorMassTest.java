package ru.geekbrains.lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculatorMassTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 0, 0},
                {1, 1, 2},
                {5, 4, 9},
                {3, 7, 10}
        });
    }

    private int first;
    private int second;
    private int result;

    public CalculatorMassTest(int first, int second, int result) {
        this.first = first;
        this.second = second;
        this.result = result;
    }

    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(result, calculator.add(first, second));
    }
}
