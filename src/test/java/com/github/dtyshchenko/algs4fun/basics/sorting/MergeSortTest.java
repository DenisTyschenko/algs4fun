package com.github.dtyshchenko.algs4fun.basics.sorting;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author denis on 10/25/16.
 */
@RunWith(JUnitParamsRunner.class)
public class MergeSortTest {

    @Test
    @Parameters(source = InsertionSortTest.PrimitiveDataForSortProvider.class)
    public void verifyMergeSort(int[] input, int[] expectedSorted) {
        Assert.assertArrayEquals(expectedSorted, MergeSort.sortRecursive(input));
    }

    @Test
    @Parameters(source = InsertionSortTest.PrimitiveDataForSortProvider.class)
    public void verifyMergeSortIterativeBottomUp(int[] input, int[] expectedSorted) {
        Assert.assertArrayEquals(expectedSorted, MergeSort.sort(input));
    }

}
