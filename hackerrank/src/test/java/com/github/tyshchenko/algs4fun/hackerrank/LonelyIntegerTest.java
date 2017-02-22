package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 2/22/17.
 */
@RunWith(JUnitParamsRunner.class)
public class LonelyIntegerTest {

    public static Object[][] testData() {
        return new Object[][] {
                {new int[] {1,2,1}, 2},
                {new int[] {1,2,1,3,2}, 3},
                {new int[] {1,1,1,2,2}, 1},
                {new int[] {1,2,3,2,1}, 3},
                {new int[] {3,2,3,2,1}, 1},
                {new int[] {15,18,15,17,18}, 17},
        };
    }

    @Test
    @Parameters(method = "testData")
    public void verifyLocatingOfDistinctElement(int[] input, int expectedDistinct) {
        Assert.assertThat(LonelyInteger.distinct(input), is(expectedDistinct));
    }
}