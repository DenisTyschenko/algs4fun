package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 2/21/17.
 */
@RunWith(JUnitParamsRunner.class)
public class LargestRegionInGridTest {

    public Object[][] testData() {
        return new Object[][] {
                {
                        //input grid
                        new int[][] {
                                {0, 0, 0},
                                {0, 0, 0},
                                {0, 0, 0},
                        }, 0

                },
                {
                        //input grid
                        new int[][] {
                                {1, 0, 0},
                                {0, 0, 0},
                                {0, 0, 0},
                        }, 1

                },
                {
                        //input grid
                        new int[][] {
                                {1, 0, 1},
                                {1, 0, 0},
                                {1, 0, 0},
                        }, 3

                },
                {
                        //input grid
                        new int[][] {
                                {1, 0, 1},
                                {1, 1, 0},
                                {1, 0, 0},
                        }, 5
                }

        };
    }

    @Test
    @Parameters(method = "testData")
    public void verifyLocatingOfLargestRegionInGrid(int[][] grid, int expectedMaxRegions) {
        DFSConnectedCellsInAGrid dfsUtil = new DFSConnectedCellsInAGrid(grid.length, grid[0].length, grid);
        Assert.assertThat(dfsUtil.findLargestRegionInGrid(), is(expectedMaxRegions));
    }
}
