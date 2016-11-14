package com.github.dtyshchenko.algs4fun.basics.sorting;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/14/16.
 */
@RunWith(JUnitParamsRunner.class)
public class RadixSortTest {

    public static Object[] data() {
        return new Object[][]{
                {new int[] {1}, new int[]{1}},
                {new int[] {1,2}, new int[]{1,2}},
                {new int[] {2,1}, new int[]{1,2}},
                {new int[] {3,2,1}, new int[]{1,2,3}},
                {new int[] {3,2,1,3}, new int[]{1,2,3,3}},
                {new int[] {3,2,1,1,2,3}, new int[]{1,1,2,2,3,3}},
                {new int[] {3,-2,1,-1,2,-3}, new int[]{-3,-2,-1,1,2,3}},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyRadixSort(int[] input, int[] expected) {
        Assert.assertThat(RadixSort.sort(input), is(expected));
    }
}
