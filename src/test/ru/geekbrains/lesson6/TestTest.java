package ru.geekbrains.lesson6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){

        return Arrays.asList(new Object[][] {
                {new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {5, 6, 7, 8, 9}, true},
                {new int[] {1, 1, 2, 2, 3, 3, 5}, new int[] {}, true},
                {new int[] {6, 7, 2, 2, 3, 3, 5, 6}, new int[] {}, false},
                {new int[] {1, 2, 3, 4, 5, 6, 4, 8, 9}, new int[] {8, 9}, true}
        });
    }

    private int[] in;
    private int[] out;
    private boolean result;


    public TestTest(int[] in, int[] out, boolean result){
        this.in = in;
        this.out = out;
        this.result = result;
    }

    @Test
    public void test() {

    }
}
