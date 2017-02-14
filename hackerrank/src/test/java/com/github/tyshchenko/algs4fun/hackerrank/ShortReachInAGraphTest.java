package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.tyshchenko.algs4fun.hackerrank.ShortReachInAGraph.shortestPathsFrom;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 2/14/17.
 */
@RunWith(JUnitParamsRunner.class)
public class ShortReachInAGraphTest {

    public static Object[][] shortestPathsData() {
        return new Object[][]{
                {0, singletonList(emptyList()), new int[]{}},
                {1, asList(
                        emptyList(),
                        emptyList(),
                        emptyList()
                ), new int[]{-1, -1}},

                {0, asList(
                        asList(1, 2),
                        singletonList(0),
                        singletonList(0)
                ), new int[]{6, 6}},

                {0, asList(
                        singletonList(1),
                        asList(0, 2),
                        singletonList(1)
                ), new int[]{6, 12}},

                {1, asList(
                        singletonList(1),
                        asList(0, 2),
                        singletonList(1)
                ), new int[]{6, 6}},

                {2, asList(
                        singletonList(1),
                        asList(0, 2),
                        singletonList(1)
                ), new int[]{12, 6}},
        };
    }

    public static Object[][] toAdjacencyMatrixData() {
        return new Object[][]{
                {},

        };
    }

    @Test
    @Parameters(method = "shortestPathsData")
    public void verifyShortestPathLookup(int startNode, List<List<Integer>> adjacency, int[] expectedPaths) {
        Assert.assertThat(shortestPathsFrom(startNode, adjacency), is(expectedPaths));
    }

    @Test
    @Parameters(method = "toAdjacencyMatrixData")
    public void verifyInputConversionToAdjacencyMatrix() {
        //TODO
    }

}