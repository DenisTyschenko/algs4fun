package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.junit.Assert.assertEquals;

/**
 * Created by denis on 10/22/16.
 */
@RunWith(JUnitParamsRunner.class)
public class DepthFirstSearchUtilTest {
    /**
     * All nodes in graph below are interconnected in both directions
     * On picture below nodes with letters represent connected components
     * 0-s represent single, not connected nodes
     * <pre>
     *  Nodes:      Connections:        Node Indexes:
     *  X 0 0 Y     5   -   -  7,11     0  1  2  3
     *  Y X 0 Y     7  0,8  -  3,4      4  5  6  7
     *  X 0 0 Y     5   -   -  3        8  9  10 11
     * </pre>
     * Adjacency matrix for the graph above:
     * <pre>
     *  0  1  2  3  4  5  6  7  8  9  10  11    -> first dimension in adjacency matrix,
     *                                             represents only nodes existing in the grid
     *  5        7  7  0     3  5          3
     *           11    8     4
     *
     * </pre>
     */
    // adjacency matrix above translated into 2 dimensional array
    private static int[][] GRAPH = new int[][]{
                /*0*/{5},
                /*1*/{},
                /*2*/{},
                /*3*/{7, 11},
                /*4*/{7},
                /*5*/{0, 8},
                /*6*/{},
                /*7*/{3, 4},
                /*8*/{5},
                /*9*/{},
                /*10*/{},
                /*11*/{3}

    };

    public static Iterable<Object[]> queryNodesOnTheSameGraph() {
        ImmutableSet<Integer> region058 = ImmutableSet.<Integer>builder()
                .add(0, 5, 8).build();
        ImmutableSet<Integer> region34711 = ImmutableSet.<Integer>builder()
                .add(3, 4, 7, 11).build();
        return Arrays.asList(new Object[][]{
                {0, region058},
                {5, region058},
                {8, region058},
                {3, region34711},
                {11, region34711},
                {4, region34711},
                {7, region34711},
                {2, singleton(2)},
        });
    }

    public static Iterable<Object[]> numberOfNodesInRegion() {
        return Arrays.asList(new Object[][]{
                {0, 3},
                {5, 3},
                {8, 3},
                {3, 4},
                {11, 4},
                {4, 4},
                {7, 4},
                {2, 1},
        });
    }

    private DepthFirstSearchUtil dfsUtil = new DepthFirstSearchUtil(GRAPH);

    @Test
    @Parameters(method = "queryNodesOnTheSameGraph")
    public void verifyRegionByNode(int node, Set<Integer> expectedConnectedNodes) {
        Set<Integer> actualConnectedNodes = dfsUtil.regionByNode(node);
        assertEquals(expectedConnectedNodes, actualConnectedNodes);
    }

    @Test
    @Parameters(method = "queryNodesOnTheSameGraph")
    public void verifyRegionByNodeIterative(int node, Set<Integer> expectedConnectedNodes) {
        Set<Integer> actualConnectedNodes = dfsUtil.regionByNodeIterative(node);
        assertEquals(expectedConnectedNodes, actualConnectedNodes);
    }

    @Test
    @Parameters(method = "numberOfNodesInRegion")
    public void verifyNumberOfNodesInRegionFormedByNode(int node, int expectedNodesNumber) {
        int actualNodesNumber = dfsUtil.nodesInRegionFormedByNode(node);
        assertEquals(expectedNodesNumber, actualNodesNumber);
    }

    @Test
    @Parameters(method = "numberOfNodesInRegion")
    public void verifyNumberOfNodesInRegionFormedByNodeIterative(int node, int expectedNodesNumber) {
        int actualNodesNumber = dfsUtil.nodesInRegionFormedByNodeIterative(node);
        assertEquals(expectedNodesNumber, actualNodesNumber);
    }

}
