package com.github.dtyshchenko.algs4fun.basics.graphs;

import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/9/16.
 */
@RunWith(JUnitParamsRunner.class)
public class ConnectivityInGraphTest {
    /**
     *        4 - 6
     *      /    /
     *     1 - 5        10-11
     *   /      \       |
     *  0 - 2 - 7       12
     *   \
     *    3 - 8 - 9     13--
     *                  |__|
     *
     *                  14
     *
     */
    public static Object[] data() {
        @SuppressWarnings("unchecked")
        Set<Integer>[] graph = new Set[] {
                of(1,2,3),//0
                of(0,4,5),//1
                of(0,7),//2
                of(0,8),//3
                of(1,6),//4
                of(1,6),//5
                of(4,5),//6
                of(2,5),//7
                of(3,9),//8
                of(8),//9
                of(11,12),//10
                of(10),//11
                of(10),//12
                of(13),//13
                of(),//14
        };
        return new Object[][]{
                {graph, 0, 9, true},
                {graph, 7, 9, true},
                {graph, 6, 9, true},
                {graph, 10, 9, false},
                {graph, 13, 9, false},
                {graph, 10, 13, false},
                // single node not connected to anyone, not connected to itself => no path to itself
                {graph, 14, 14, false},
                //single however connected to itself
                {graph, 13, 13, true},
                //not connected to itself, however there is a path to itself through other nodes
                {graph, 0, 0, true},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyIsConnected_DFS(Set<Integer>[] adjacency, int source, int dest, boolean expected) {
        Assert.assertThat(DepthFirstGraphTraversal.isConnected(source, dest, adjacency), is(expected));
    }

    @Test
    @Parameters(method = "data")
    public void verifyIsConnected_BFS(Set<Integer>[] adjacency, int source, int dest, boolean expected) {
        Assert.assertThat(BreadthFirstGraphTraversal.isConnected(source, dest, adjacency), is(expected));
    }
}
