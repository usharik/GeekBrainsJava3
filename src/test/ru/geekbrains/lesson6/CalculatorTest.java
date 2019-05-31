package ru.geekbrains.lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(10, calculator.add(3, 7));
    }

    @Test
    @Ignore
    public void testSub() {
        Assert.assertEquals(4, calculator.sub(7, 3));
    }

    @Test(timeout = 12)
    public void testDiv() {
        Assert.assertEquals(3, calculator.div(9, 3));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivByZero() {
        Assert.assertEquals(3, calculator.div(9, 0));
    }

}
