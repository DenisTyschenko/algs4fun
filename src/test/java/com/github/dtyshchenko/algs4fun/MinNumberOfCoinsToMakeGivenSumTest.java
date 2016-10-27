package com.github.dtyshchenko.algs4fun;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.dtyshchenko.algs4fun.MinNumberOfCoinsToMakeGivenSum.minNumberOfCoins;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 10/27/16.
 */
@RunWith(JUnitParamsRunner.class)
public class MinNumberOfCoinsToMakeGivenSumTest {

    public static Object[][] data() {
        return new Object[][]{
                //happy path
                {new int[]{1}, 1, 1},
                {new int[]{1,1}, 1, 1},
                {new int[]{1,2}, 1, 1},
                {new int[]{2,1}, 1, 1},
                {new int[]{1,1,1}, 1, 1},
                {new int[]{1,1,2}, 2, 1},
                {new int[]{1,2,1}, 2, 1},
                {new int[]{1,1,1}, 2, 2},
                {new int[]{1,1,3,1,1,2,1,1}, 6, 3},
                {new int[]{2,4,6,9}, 6, 1},
                {new int[]{2,4,6,8}, 10, 2},

                //not possible to make sum
                {new int[]{1}, 2, -1},
                {new int[]{2,4,6,8}, 9, -1},
                {new int[]{2,4,6,8}, 3, -1},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyMinNumberOfCoinsCalculation(int[] coins, int sum, int expectedNumOfCoins) {
        Assert.assertThat(minNumberOfCoins(coins, sum), is(expectedNumOfCoins));
    }
}
