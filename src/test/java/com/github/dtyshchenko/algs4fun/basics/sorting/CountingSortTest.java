package com.github.dtyshchenko.algs4fun.basics.sorting;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.dtyshchenko.algs4fun.basics.sorting.CountingSort.sortSpaceReduced;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/14/16.
 */
@RunWith(JUnitParamsRunner.class)
public class CountingSortTest {

    public static Object[] data() {
        return new Object[][] {
                {new int[] {0}, 0, 1, new int[] {0}},
                {new int[] {0,1,2}, 0, 3, new int[] {0,1,2}},
                {new int[] {1,0,2}, 0, 3, new int[] {0,1,2}},
                {new int[] {2,0,1,0,1,2}, 0, 3, new int[] {0,0,1,1,2,2}},
                {new int[] {1,1,1,1,1}, 0, 3, new int[] {1,1,1,1,1}},
                {new int[] {2,1,0,0,1,2}, 0, 3, new int[] {0,0,1,1,2,2}},

                {new int[] {-1}, -1, -1, new int[] {-1}},
                {new int[] {-1, -2, -1}, -2, -1, new int[] {-2, -1, -1}},
                {new int[] {-1, 0, -2, 0, -1, 3, 5}, -2, 5, new int[] {-2, -1, -1, 0, 0, 3 ,5}},
                {new int[] {5,4,2,-1,-5}, -5, 5, new int[] {-5,-1,2,4,5}},
        };
    }
    @Test
    @Parameters(method = "data")
    public void verifyCountingSort(int[] input, int lo, int hi, int[] expected) {
        Assert.assertThat(CountingSort.sort(input, lo, hi), is(expected));
    }

    @Test
    @Parameters(method = "data")
    public void verifyCountingSortSpaceReduced(int[] input, int lo, int hi, int[] expected) {
        Assert.assertThat(sortSpaceReduced(input, lo, hi), is(expected));
    }
}
