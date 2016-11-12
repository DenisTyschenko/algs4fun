package com.github.dtyshchenko.algs4fun.geeksforgeeks.graphs;

import com.github.dtyshchenko.algs4fun.geeksforgeeks.graphs.DepthFirstSearchUtil;
import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;
import static java.util.Collections.emptySet;
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
    private static List<Set<Integer>> GRAPH = Arrays.asList(
                /*0*/of(5),
                /*1*/emptySet(),
                /*2*/emptySet(),
                /*3*/of(7, 11),
                /*4*/of(7),
                /*5*/of(0, 8),
                /*6*/emptySet(),
                /*7*/of(3, 4),
                /*8*/of(5),
                /*9*/emptySet(),
                /*10*/emptySet(),
                /*11*/of(3)
    );

    private static List<Set<Integer>> GRAPH2 = Arrays.asList(
            of(1, 5),
            of(0, 5, 6),
            emptySet(),
            emptySet(),
            emptySet(),
            of(0, 1, 6, 10),
            of(1, 5, 10),
            emptySet(),
            emptySet(),
            emptySet(),
            of(5, 6),
            emptySet(),
            emptySet(),
            emptySet(),
            emptySet(),
            emptySet()
        );

    public static Iterable<Object[]> queryNodesOnTheSameGraph() {
        ImmutableSet<Integer> region058 = of(0, 5, 8);
        ImmutableSet<Integer> region34711 = of(3, 4, 7, 11);
        return Arrays.asList(new Object[][]{
                {GRAPH, 0, region058},
                {GRAPH, 5, region058},
                {GRAPH, 8, region058},
                {GRAPH, 3, region34711},
                {GRAPH, 11, region34711},
                {GRAPH, 4, region34711},
                {GRAPH, 7, region34711},
                {GRAPH, 2, singleton(2)},
                {GRAPH2, 0, of(0, 1, 5, 6, 10)},
                {GRAPH2, 1, of(0, 1, 5, 6, 10)},
                {GRAPH2, 5, of(0, 1, 5, 6, 10)},
                {GRAPH2, 6, of(0, 1, 5, 6, 10)},
                {GRAPH2, 10, of(0, 1, 5, 6, 10)},
                {GRAPH2, 2, singleton(2)}
        });
    }

    public static Iterable<Object[]> numberOfNodesInRegion() {
        return Arrays.asList(new Object[][]{
                {GRAPH, 0, 3},
                {GRAPH, 5, 3},
                {GRAPH, 8, 3},
                {GRAPH, 3, 4},
                {GRAPH, 11, 4},
                {GRAPH, 4, 4},
                {GRAPH, 7, 4},
                {GRAPH, 2, 1},
                {GRAPH2, 0, 5}
        });
    }


    @Test
    @Parameters(method = "queryNodesOnTheSameGraph")
    public void verifyRegionByNode(List<Set<Integer>> graph, int node, Set<Integer> expectedConnectedNodes) {
        DepthFirstSearchUtil dfsUtil = new DepthFirstSearchUtil(graph);
        Set<Integer> actualConnectedNodes = dfsUtil.regionByNode(node);
        assertEquals(expectedConnectedNodes, actualConnectedNodes);
    }

    @Test
    @Parameters(method = "queryNodesOnTheSameGraph")
    public void verifyRegionByNodeIterative(List<Set<Integer>> graph, int node, Set<Integer> expectedConnectedNodes) {
        DepthFirstSearchUtil dfsUtil = new DepthFirstSearchUtil(graph);
        Set<Integer> actualConnectedNodes = dfsUtil.regionByNodeIterative(node);
        assertEquals(expectedConnectedNodes, actualConnectedNodes);
    }

    @Test
    @Parameters(method = "numberOfNodesInRegion")
    public void verifyNumberOfNodesInRegionFormedByNode(List<Set<Integer>> graph, int node, int expectedNodesNumber) {
        DepthFirstSearchUtil dfsUtil = new DepthFirstSearchUtil(graph);
        int actualNodesNumber = dfsUtil.nodesInRegionFormedByNode(node);
        assertEquals(expectedNodesNumber, actualNodesNumber);
    }

    @Test
    @Parameters(method = "numberOfNodesInRegion")
    public void verifyNumberOfNodesInRegionFormedByNodeIterative(List<Set<Integer>> graph, int node, int expectedNodesNumber) {
        DepthFirstSearchUtil dfsUtil = new DepthFirstSearchUtil(graph);
        int actualNodesNumber = dfsUtil.nodesInRegionFormedByNodeIterative(node);
        assertEquals(expectedNodesNumber, actualNodesNumber);
    }

}
