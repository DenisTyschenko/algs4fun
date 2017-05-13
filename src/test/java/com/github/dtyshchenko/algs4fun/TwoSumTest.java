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
}