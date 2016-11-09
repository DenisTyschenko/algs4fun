package com.github.dtyshchenko.algs4fun.basics.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * As per <a href="http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/">Breadth First Traversal for a Graph
 * </a> and <i>explanation</i> to
 * <a href="https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid">Connected Cell in a Grid</a>,
 * particularly approach described in <a href="https://www.youtube.com/watch?v=zaBhtODEL0w">video about dfs and bfs</a>
 *
 * @author denis on 11/9/16.
 */
public class BreadthFirstGraphTraversal {

    public static boolean isConnected(Integer source, Integer dest, Set<Integer>[] adjacency) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        while(!queue.isEmpty()) {
            Integer node = queue.poll();
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            if (adjacency[node].contains(dest)) {
                return true;
            }
            for (Integer sibling: adjacency[node]) {
                queue.offer(sibling);
            }
        }
        return false;
    }
}
