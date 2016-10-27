package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.dtyshchenko.algs4fun.geeksforgeeks.MaxSumInSubarraysProblem.findMaxSumInArbitraryContiguousSubarray;
import static com.github.dtyshchenko.algs4fun.geeksforgeeks.MaxSumInSubarraysProblem.findMaxSumInContiguousSubarrays;
import static com.github.dtyshchenko.algs4fun.geeksforgeeks.MaxSumInSubarraysProblem.findMaxSumInNotContiguousSubarrays;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 10/27/16.
 */
@RunWith(JUnitParamsRunner.class)
public class MaxSumInSubarraysTest {

    public static Object[][] dataForMaxInContiguousArbitrarySubarray() {
        return new Object[][]{
                {new int[]{1}, 1},
                {new int[]{1, 2}, 3},
                {new int[]{1, -2, 2}, 2},
                {new int[]{1, -2, 2, 4, -7, 30}, 30},
                {new int[]{1, -2, 2, 4, -7}, 6}
        };
    }

    public static Object[][] dataForContiguousSubarrays() {
        return new Object[][]{
                {new int[]{1}, 1, 1},

                {new int[]{1, 2}, 1, 2},
                {new int[]{1, 2}, 2, 3},

                {new int[]{1, 2, 3}, 1, 3},
                {new int[]{1, 2, 3}, 2, 5},
                {new int[]{1, 2, 3}, 3, 6},

                {new int[]{-1, -2, -3, -4, -5}, 3, -6},
        };
    }

    public static Object[][] dataForNotContiguousSubarrays() {
        return new Object[][]{
                {new int[]{1}, 1, 1},

                {new int[]{1, 2}, 1, 2},
                {new int[]{1, 2}, 2, 3},

                {new int[]{1, 2, 3}, 1, 3},
                {new int[]{1, 1, 3}, 2, 2},
                {new int[]{-100, 1, 3}, 2, -99},
                {new int[]{1, 2, 3}, 3, 6},
                {new int[]{-1, -2, -3}, 3, -6},
                {new int[]{-1, -3, -3, -4}, 2, -4},
        };
    }

    @Test
    @Parameters(method = "dataForContiguousSubarrays")
    public void verifyMaxInContiguousSubarrays(int[] array, int k, int expectedMaxSum) {
        Assert.assertThat(findMaxSumInContiguousSubarrays(array, k), is(expectedMaxSum));
    }

    @Test
    @Parameters(method = "dataForNotContiguousSubarrays")
    public void verifyMaxInSubarraysNotContoguousSubarrays(int[] array, int k, int expectedMaxSum) {
        Assert.assertThat(findMaxSumInNotContiguousSubarrays(array, k), is(expectedMaxSum));
    }

    @Test
    @Parameters(method = "dataForMaxInContiguousArbitrarySubarray")
    public void verifyMaxInArbitraryContiguousSubarray(int[] array,int expectedMaxSum) {
        Assert.assertThat(findMaxSumInArbitraryContiguousSubarray(array), is(expectedMaxSum));
    }
}
