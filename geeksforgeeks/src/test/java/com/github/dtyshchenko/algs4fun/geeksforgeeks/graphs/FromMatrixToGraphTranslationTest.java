package com.github.dtyshchenko.algs4fun.geeksforgeeks.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Set;

import static com.github.dtyshchenko.algs4fun.geeksforgeeks.graphs.NumberOfCellsInLargestRegion.translateIntoAdjacencyMatrix;
import static com.google.common.collect.ImmutableSet.of;
import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by denis on 10/22/16.
 */
@RunWith(JUnitParamsRunner.class)
public class FromMatrixToGraphTranslationTest {

    public static Iterable<Object[]> data() {
        return asList(new Object[][]{
                {new int[][]{{0, 1}}, asList(emptySet(), emptySet())},
                {new int[][]{{0}, {1}}, asList(emptySet(), emptySet())},
                {new int[][]{{0}}, singletonList(emptySet())},

                {new int[][]{
                        {0, 1},
                        {1, 0}
                }, asList(
                        emptySet(),
                        singleton(2),
                        singleton(1),
                        emptySet()
                )},
                {new int[][]{
                        {1, 0, 0, 1},
                        {0, 1, 0, 1},
                        {1, 0, 0, 1}
                }, asList(
                        singleton(5),   //0
                        emptySet(),     //1
                        emptySet(),     //2
                        singleton(7),   //3
                        emptySet(),     //4
                        of(0, 8),       //5
                        emptySet(),     //6
                        of(3, 11),      //7
                        singleton(5),   //8
                        emptySet(),     //9
                        emptySet(),     //10
                        singleton(7)    //11
                )},
                {new int[][] {
                        {1,1,0,0},
                        {0,1,1,0},
                        {0,0,1,0},
                        {1,0,0,0}
                }, asList(
                        of(1,5),        //0
                        of(0,5,6),      //1
                        emptySet(),     //2
                        emptySet(),     //3
                        emptySet(),     //4
                        of(0,1,6,10),   //5
                        of(1,5,10),       //6
                        emptySet(),     //7
                        emptySet(),     //8
                        emptySet(),     //9
                        of(5,6),        //10
                        emptySet(),     //11
                        emptySet(),     //12
                        emptySet(),      //13
                        emptySet(),      //14
                        emptySet()      //15
                )}

        });
    }

    @Test
    @Parameters(method = "data")
    public void verifyTwoDimensionMatrixToGraphTranslation(int[][] inMatrix, List<Set<Integer>> expectedGraph) {
        List<Set<Integer>> actualGraph = translateIntoAdjacencyMatrix(inMatrix, inMatrix.length, inMatrix[0].length);
        assertEquals(expectedGraph, actualGraph);
    }

}
