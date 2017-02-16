package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Set;

import static com.github.tyshchenko.algs4fun.hackerrank.DFSConnectedCellsInAGrid.toAdjacencyMatrix;
import static com.google.common.collect.ImmutableSet.of;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 2/16/17.
 */
@RunWith(JUnitParamsRunner.class)
public class DFSConnectedCellsInAGridTest {

    public static Object[][] convertToAdjacencyData() {
        return new Object[][]{
                {       //input grid
                        new int[][]{
                                {1, 0, 1},
                                {1, 0, 0},
                                {1, 0, 0},
                        },
                        //adjacency
                        asList(
                                of(0, 3), of(), singleton(2),
                                of(3, 0, 6), of(), of(),
                                of(6, 3), of(), of()
                        )
                },
                {
                        //input grid
                        new int[][]{
                                {1, 0, 1},
                                {1, 1, 0},
                                {1, 0, 0},
                        },
                        //adjacency
                        asList(
                                of(0, 3, 4), of(),
                                of(2, 4), of(3, 0, 6, 4),
                                of(4, 0, 2, 3, 6), of(), of(6, 3, 4),
                                of(), of()
                        )
                }
        };
    }

    @Test
    @Parameters(method = "convertToAdjacencyData")
    public void verifyToAdjacencyMatrixConversion(int[][] grid, List<Set<Integer>> expectedAdjacency) {

        List<Set<Integer>> adjacency = toAdjacencyMatrix(grid.length, grid[0].length, grid);
        Assert.assertThat("Size of actual adjacency matrix is not as per expected", adjacency.size(), is(expectedAdjacency.size()));
        for (int i = 0; i < expectedAdjacency.size(); i++) {
            Assert.assertEquals("Node : " + i, expectedAdjacency.get(i), adjacency.get(i));
        }
    }

}