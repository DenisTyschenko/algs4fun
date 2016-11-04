package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/4/16.
 */
@RunWith(JUnitParamsRunner.class)
public class ArrayRotationTest {

    public static Object[] data() {
        return new Object[][]{
                {0, new int[]{}, new int[]{}},
                {0, new int[]{1}, new int[]{1}},
                {0, new int[]{1,2}, new int[]{1,2}},
                {1, new int[]{1}, new int[]{1}},
                {1, new int[]{1,2}, new int[]{2,1}},
                {2, new int[]{1,2}, new int[]{1,2}},
                {2, new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}},
                {1, new int[]{1,2,3,4,5}, new int[]{2,3,4,5,1}}
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyArrayRotation(int d, int[] input, int[] expected) {
        Assert.assertThat(ArrayRotation.rotateLeft(d, input), is(expected));
    }
}
