package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 3/22/17.
 */
@RunWith(JUnitParamsRunner.class)
public class CountingInversionsTest {

    public static Object[][] data() {
        return new Object[][]{
                {new int[]{1}, 0},
                {new int[]{2, 1}, 1},
                {new int[]{4, 2, 1}, 3},
                {new int[]{4, 1, 2}, 2},
                {new int[]{4, 1, 2, 5}, 2},
                {new int[]{7, 4, 1, 2, 5}, 6},
                {new int[]{7, 4, 1, 2, 5}, 6},
                {new int[]{3, 2, 1}, 3},
                {new int[]{4, 3, 2, 1}, 6},
                {new int[]{5, 4, 3, 2, 1}, 10},
                {new int[]{6, 5, 4, 3, 2, 1}, 15},
                {new int[]{7, 6, 5, 4, 3, 2, 1}, 21}
        };
    }

    public static Object[][] illegalData() {
        return new Object[][]{
                {new int[0]},
                {null}
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyInversionCounting(int[] input, int expected) {
        Assert.assertThat(CountingInversions.countInversions(input), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "illegalData")
    public void verifyInversionCountingOnIllegalInput(int[] illegalInput) {
        CountingInversions.countInversions(illegalInput);
    }

}
