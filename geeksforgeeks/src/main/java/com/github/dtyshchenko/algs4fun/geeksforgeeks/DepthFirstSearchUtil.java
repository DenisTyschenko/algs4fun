package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Below is taken from <a href="https://www.hackerrank.com/challenges/connected-cell-in-a-grid/topics/depth-first-search">
 *     Depth First Search</a> page
 * <p>
 * Depth First Search (popularly abbreviated as DFS) is a recursive graph searching technique.
 *
 * <h3>The Algorithm</h3>
 * While doing a DFS, we maintain a set of visited nodes. Initially this set is empty.
 * When DFS is called on any vertex (say uu), first that vertex is marked as visited and then for every edge going out of that vertex, (u,v)(u,v), such that vv is unvisited, we call DFS on vv.
 * Finally, we return when we have exhausted all the edges going out from uu.
 * Hence, if a graph is represented in the stanard adjacency list form (a vector of vectors in C++), the C++ implementation follows:
 *
 * <h3>The Complexity and Correctness</h3>
 * The above code provides a good angle to view at the complexity. Under no circumstance, DFS will be called twice on a node. For every node, we have iterations equal to the degree of that node. Hence the time complexity is simply sum of degrees of all the nodes which is O(V+E)O(V+E).
 * If DFS is called on a node uu, and there exists a path u→vu→v then DFS will definitiely visit vv.
 * This can be proven as an induction on the path length of u→vu→v by observing that DFS indeed visits all nodes which have a direct edge from the first node and hence establishing the base case i.e. for path length = 11.
 * </p>
 *
 * Created by denis on 10/22/16.
 */
public class DepthFirstSearchUtil {
    //internally graph is represented as adjacency matrix
    private final int[][] graph;

    /**
     *
     * @param graph - adjacency matrix to represent a graph
     *
     */
    public DepthFirstSearchUtil(int[][] graph) {
        this.graph = graph;
    }

    /**
     * Returns nodes that forms a region with the parameter's node
     */
    public Set<Integer> regionByNode(int node) {
        //to track visited nodes during graph traversal
        return regionByNode(node, new boolean[graph.length]);
    }

    public Set<Integer> regionByNodeIterative(int node) {
        Set<Integer> result = new HashSet<>();
        Deque<Integer> stack = new LinkedBlockingDeque<>();
        boolean[] visited = new boolean[graph.length];
        stack.push(node);
        while(!stack.isEmpty()) {
            Integer currentNode = stack.pop();
            visited[currentNode] = true;
            result.add(currentNode);
            //iterate through adjacent nodes
            for (Integer adjNode: graph[currentNode]) {
                if (!visited[adjNode]) {
                    stack.push(adjNode);
                }
            }
        }
        return result;
    }

    public int nodesInRegionFormedByNodeIterative(int node) {
        return regionByNodeIterative(node).size();
    }

    public int nodesInRegionFormedByNode(int node) {
        //to track visited nodes during graph traversal
        return nodesInRegionFormedByNode(node, new boolean[graph.length]);
    }

    private Set<Integer> regionByNode(int node, boolean[] visited) {
        Set<Integer> result = new HashSet<>();
        visited[node] = true;
        result.add(node);
        // for all adjacent nodes to the current node
        for (int adjNode : graph[node]) {
            if (!visited[adjNode]) {
               result.addAll(regionByNode(adjNode, visited));
            }
        }
        return result;
    }

    /**
     * Not using {@link #regionByNode(int, boolean[])} to avoid redundant Set creation for each iteration
     */
    private int nodesInRegionFormedByNode(int node, boolean[] visited) {
        visited[node] = true;
        int result = 1; // one stands for current node, included in result
        // for all adjacent nodes to the current node
        for (int adjNode : graph[node]) {
            if (!visited[adjNode]) {
                result += nodesInRegionFormedByNode(adjNode, visited);
            }
        }
        return result;
    }

}
