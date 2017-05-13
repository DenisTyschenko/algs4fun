package com.github.dtyshchenko.algs4fun;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

/**
 * Created by denis on 5/13/17.
 */
@RunWith(JUnitParamsRunner.class)
public class TwoSumTest {

    public static Object[] data() {
        return new Object[][]{
                {new int[]{1, 1, 0}, 2, new int[]{0, 1}},
                {new int[]{0, 1, 2, 3,4,5,6,7,8,9,10}, 19, new int[]{9, 10}},
                {new int[]{3, 2, 4}, 6, new int[]{1, 2}},
                {new int[]{1, 5, 0}, 6, new int[]{0, 1}},
                {new int[]{2, 3, 4, 1}, 7, new int[]{1, 2}},
                {new int[]{2, -3, 4, 2}, 1, new int[]{1, 2}},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verify(int[] input, int target, int[] expected) {
        int[] actuals = TwoSum.twoSum(input, target);
        Assert.assertArrayEquals(Arrays.toString(actuals), expected, actuals);
    }

    public static Object[] performance() {
        return new Object[][]{
                {new int[]{1, 1, 0}, 2, new int[]{0, 1}},
                {new int[]{3, 2, 4}, 6, new int[]{1, 2}},
        };
    }

    @Test
    public void verifyPerformanceOptimized() {
        int[] input = new int[1_000_001];
        for (int i=0; i < input.length; i++) {
            input[i] = i;
        }
        long start = System.nanoTime();
        int[] actuals = TwoSum.twoSum(input, 1_999_999);

        System.out.println("Elapsed time of optimized method: " + (System.nanoTime() - start) / 1_000_000);

        Assert.assertArrayEquals(Arrays.toString(actuals), new int[] {999_999, 1_000_000}, actuals);
    }

    @Test
    public void verifyPerformanceBruteForce() {
        int[] input = new int[1_000_001];
        for (int i=0; i < input.length; i++) {
            input[i] = i;
        }
        long start = System.nanoTime();
        int[] actuals = TwoSum.twoSumQuadtratic(input, 1_999_999);

        System.out.println("Elapsed time of brute force method: " + (System.nanoTime() - start) / 1_000_000);

        Assert.assertArrayEquals(Arrays.toString(actuals), new int[] {999_999, 1_000_000}, actuals);
    }

}