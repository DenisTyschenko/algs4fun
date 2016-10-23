package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.dtyshchenko.algs4fun.geeksforgeeks.NumberOfCellsInLargestRegion.numberOfCellsInLargestRegion;
import static org.junit.Assert.assertEquals;

/**
 * Created by denis on 10/22/16.
 */
@RunWith(JUnitParamsRunner.class)
public class NumberOfCellsInLargestRegionTest {
    private static final String DIR_PREFIX = "number-of-cells-in-largest-region/";

    @Test
    @Parameters({
            "case1.txt , 5",
            "case2.txt, 3",
            "case3.txt, 13",
            "case4.txt, 3",
            "case5.txt, 2",
            "case6.txt, 2",
    })
    public void verifyNumberOfCellsInLargestRegion(String fileName, int expectedMaxCells) {
        int actualMaxCells = numberOfCellsInLargestRegion(DIR_PREFIX + fileName);
        assertEquals(expectedMaxCells, actualMaxCells);
    }
}
