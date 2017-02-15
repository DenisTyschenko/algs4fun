package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Scanner;

import static com.github.tyshchenko.algs4fun.hackerrank.ShortReachInAGraph.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 2/14/17.
 */
@RunWith(JUnitParamsRunner.class)
public class ShortReachInAGraphTest {

    private static final List<List<Integer>> ADJACENCY_MATRIX = asList(
            asList(1, 2),
            singletonList(0),
            singletonList(0),
            emptyList()
    );
    private static final String FIRST_QUERY = "4 2\n" +
            "1 2\n" +
            "1 3\n";
    public static final String SECOND_QUERY = "3 1\n" +
            "2 3\n";
    private static final String TWO_QUERY_INPUT =
            "2\n" + /*num of queries */
                    //first query
                    FIRST_QUERY + "1\n" +
                    //second query
                    SECOND_QUERY + "2";

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

                {0, ADJACENCY_MATRIX, new int[]{6, 6, -1}}
        };
    }

    public static Object[][] viewData() {
        return new Object[][]{
                {
                        TWO_QUERY_INPUT,
                        "6 6 -1\n" +
                                "-1 6"
                },
        };
    }

    @Test
    @Parameters(method = "shortestPathsData")
    public void verifyShortestPathLookup(int startNode, List<List<Integer>> adjacency, int[] expectedPaths) {
        Assert.assertThat(shortestPathsFrom(startNode, adjacency), is(expectedPaths));
    }

    @Test
    @Parameters(method = "viewData")
    public void verifyShortestPathWithRepresentation(String input, String expected) {
        Assert.assertThat(shortestPathsForMultiQueries(input), is(expected));
    }

    @Test
    public void verifyConversionToAdjacencyMatrix() {
        Scanner sc = new Scanner(FIRST_QUERY);
        Assert.assertThat(readAdjacencyMatrix(sc), is(ADJACENCY_MATRIX ));
    }
}