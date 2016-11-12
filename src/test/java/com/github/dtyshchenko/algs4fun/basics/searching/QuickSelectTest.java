package com.github.dtyshchenko.algs4fun.basics.searching;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Set;

import static com.github.dtyshchenko.algs4fun.basics.searching.QuickSelect.selectMinimumElements;
import static com.google.common.collect.ImmutableSet.of;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/12/16.
 */
@RunWith(JUnitParamsRunner.class)
public class QuickSelectTest {

    public static Object[] dataForSelect() {
        return new Object[][]{
                //order of minimum elements in response can be mixed
                //due to randomized partition function
                //for example it is possible to pick up i = randPartition(), such that
                //i+1 = k, therefore left subarray + partitioning item will be reported as a response, however
                //partition function only guarantees that elements in left subarray are less or equal to partitioning item,
                //and no guarantee on order in left subarray
                {new int[]{1}, 1, new int[]{1}},
                {new int[]{1, 2}, 2, new int[]{1, 2}},
                {new int[]{1, 2, 3}, 3, new int[]{1, 2, 3}},
                {new int[]{3, 1, 2}, 1, new int[]{1}},
                {new int[]{3, 1, 2}, 2, new int[]{1, 2}},
                {new int[]{1, 1, 1, 1}, 2, new int[]{1, 1}},
                {new int[]{5, 1, 4, 3, 2, 1, 7}, 4, new int[]{1, 1, 2, 3}},
        };
    }

    public static Object[] dataForPartition() {
        return new Object[][]{
                {new int[]{1, 2}, new int[]{1, 2}, 0},
                {new int[]{1, 1}, new int[]{1, 1}, 1},

                {new int[]{1, 2, 3}, new int[]{1, 2, 3}, 0},
                {new int[]{2, 1, 3}, new int[]{1, 2, 3}, 1},
                {new int[]{3, 2, 1}, new int[]{1, 2, 3}, 2},
                {new int[]{3, 1, 2}, new int[]{2, 1, 3}, 2},

                {new int[]{2, 1, 2}, new int[]{2, 1, 2}, 2},
                {new int[]{2, 1, 1, 2}, new int[]{2, 1, 1, 2}, 3},
                {new int[]{2, 1, 2, 2}, new int[]{2, 1, 2, 2}, 2},
        };
    }

    @Test
    @Parameters(method = "dataForPartition")
    public void verifyPartitioning(int[] els, int[] expected, int expectedIndex) {
        int partitionIndex = QuickSelect.partition(0, els.length - 1, els);
        Assert.assertThat(els, is(expected));
        Assert.assertThat(partitionIndex, is(expectedIndex));
    }

    @Test
    @Parameters(method = "dataForSelect")
    public void verifySelection(int[] els, int k, int[] expected) {
        //quick selection is based on randomized function, so verifying that test is stable
        for (int i = 0; i < 10000; i++) {
            //ensure we are not mutating test input data
            int[] inputCopy = Arrays.copyOf(els, els.length);
            int[] actual = selectMinimumElements(k, inputCopy);
            //order of minimum elements in response can be mixed
            //due to randomized partition function
            //for example it is possible to pick up i = randPartition(), such that
            //i+1 = k, therefore left subarray + partitioning item will be reported as a response, however
            //partition function only guarantees that elements in left subarray are less or equal to partitioning item,
            //and no guarantee on order in left subarray
            Arrays.sort(actual);
            //expected is already sorted on declaration
            Assert.assertThat("Failed on "+i+" test repeat", actual, is(expected));
        }
    }



}
