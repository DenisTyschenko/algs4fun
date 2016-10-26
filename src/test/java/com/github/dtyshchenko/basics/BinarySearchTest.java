package com.github.dtyshchenko.basics;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 10/26/16.
 */
@RunWith(JUnitParamsRunner.class)
public class BinarySearchTest {

    public static Object[][] data() {
        return new Object[][] {
                {new int[] {1,2}, 1, 0},
                {new int[] {1,2}, 2, 1},
                {new int[] {1,2}, 0, -1},
                {new int[] {2,2}, 2, 0},
                {new int[] {-2,-2,1}, 1, 2},
                {new int[] {-2,-2,-1,0,1}, 0, 3},

                {new int[] {-2,-2,-1,0,1}, 7, -1},
                {new int[] {2,2}, 3, -1},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyRecursiveBinarySearch(int[] input, int el, int foundExpct) {
        Assert.assertThat(BinarySearch.indexRecursive(input, el), is(foundExpct));
    }

    @Test
    @Parameters(method = "data")
    public void verifyBinarySearch(int[] input, int el, int foundExpct) {
        Assert.assertThat(BinarySearch.index(input, el), is(foundExpct));
    }

}
