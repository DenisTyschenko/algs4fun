package com.github.dtyshchenko.algs4fun.basics.sorting;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author denis on 10/25/16.
 */
@RunWith(JUnitParamsRunner.class)
public class InsertionSortTest {

    public static class PrimitiveDataForSortProvider {

        public static Object[][] provideIntValues() {
            return new Object[][]{
                    {new int[] {3,2,1,5,6,7,9}, new int[]{1,2,3,5,6,7,9}},
                    {new int[] {1,1,0,1}, new int[] {0,1,1,1}},
                    {new int[] {1,2,0,1,2,3}, new int[] {0,1,1,2,2,3}},
                    {new int[] {1}, new int[] {1}},
                    {new int[] {}, new int[] {}}
            };
        }
    }


    public static Collection<Object[]> comparableDataTypes() {
        return Arrays.asList(new Object[][]{
                {new Integer[] {3,2,1,5,6,7,9}, new Integer[]{1,2,3,5,6,7,9}},
                {new Integer[] {1,1,0,1}, new Integer[] {0,1,1,1}},
                {new Integer[] {1,2,0,1,2,3}, new Integer[] {0,1,1,2,2,3}},
                {new Integer[] {1}, new Integer[] {1}},
                {new Integer[] {}, new Integer[] {}},
                {new String[] {"a","d","b","d","f"}, new String[] {"a","b","d","d","f"}},
        });
    }

    @Test
    @Parameters(source = PrimitiveDataForSortProvider.class)
    public void verifySortOnPrimitiveType(int[] input, int[] expectedSorted) {
        int[] actualSorted = InsertionSort.sort(input);
        Assert.assertArrayEquals(expectedSorted, actualSorted);
    }

    @Test
    @Parameters(method = "comparableDataTypes")
    public <T extends Comparable<T>> void verifySortOnComparables(T[] input, T[] expectedSorted) {
        T[] actualSorted = InsertionSort.sort(input);
        Assert.assertArrayEquals(expectedSorted, actualSorted);
    }

    @Test
    @Parameters(method = "comparableDataTypes")
    public <T extends Comparable<T>> void verifySortOnObjWithComparator(T[] input, T[] expectedSorted) {
        T[] actualSorted = InsertionSort.sort(input, Comparable::compareTo);
        Assert.assertArrayEquals(expectedSorted, actualSorted);
    }

}
