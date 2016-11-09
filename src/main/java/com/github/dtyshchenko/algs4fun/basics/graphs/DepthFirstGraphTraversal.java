package com.github.dtyshchenko.algs4fun.basics.graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * As per <a href="http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/">Depth First Traversal for a Graph
 * </a> and <i>explanation</i> to
 * <a href="https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid">Connected Cell in a Grid</a>,
 * particularly approach described in <a href="https://www.youtube.com/watch?v=zaBhtODEL0w">video about dfs and bfs</a>
 *
 * @author denis on 11/9/16.
 */
public class DepthFirstGraphTraversal {

    public static boolean isConnected(Integer source, Integer dest, Set<Integer>[] adjacency) {
        Set<Integer> visited = new HashSet<>();
        return isConnected(source, dest, adjacency, visited);
    }

    private static boolean isConnected(Integer source, Integer dest,
                                       Set<Integer>[] adjacency, Set<Integer> visited) {
        if (visited.contains(source)) {
            return false;
        }

        visited.add(source);

        if (adjacency[source].contains(dest)) {
            return true;
        }
        for (Integer sibling : adjacency[source]) {
            if (isConnected(sibling, dest, adjacency, visited)) {
                return true;
            }
        }
        return false;
    }
}
