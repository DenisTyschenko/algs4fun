package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 2/13/17.
 */
@RunWith(JUnitParamsRunner.class)
public class CoinChangeTest {

    public static Object[][] data() {
        return new Object[][]{
                {1, new int[]{1, 3, 2}, 1},
                {3, new int[]{3, 2, 1}, 3},
                {4, new int[]{4, 1, 3, 2}, 5},
                {4, new int[]{1, 3, 2}, 4},
                {10, new int[]{2, 5, 3, 6}, 5},

                {250, new int[]{41, 34, 46, 9, 37, 32, 42, 21, 7, 13, 1, 24, 3, 43, 2, 23, 8, 45, 19, 30, 29,
                        18, 35, 11}, 15685693751L},

        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyCoinChange(int sum, int[] coins, long expectedSolutions) {
        Assert.assertThat(CoinChange.solutions(sum, coins), is(expectedSolutions));
    }

}