package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach">
 * BFS: Shortest Reach in a Graph</a>
 * <p>
 * Created by denis on 2/14/17.
 */
public class ShortReachInAGraph {

    private static final int NOT_VISITED = -1;

    /**
     * @param startNode - node to compute shortest path to other n - 1 nodes.
     *                  Path between two neighbours is 6 by task definition
     * @return array of the shortest paths from startNode to all n - 1 other nodes
     * or -1 if there is no connection with startNode
     */
    public static int[] shortestPathsFrom(int startNode, List<List<Integer>> adjacency) {
        Queue<WeightedNode> queue =
                putOnQueueTraversal(new LinkedList<>(), adjacency.get(startNode), 0);

        int[] result = new int[adjacency.size() - 1];
        Arrays.fill(result, NOT_VISITED);

        while (!queue.isEmpty()) {
            WeightedNode current = queue.poll();
            int resultIndex = resultIndex(current.node, startNode);
            if (current.node != startNode && result[resultIndex] == NOT_VISITED) {
                putOnQueueTraversal(queue, adjacency.get(current.node), current.weight);
                result[resultIndex] = current.weight;
            }
        }
        return result;
    }

    private static int resultIndex(int currentNodeIndex, int startNodeIndex) {
        return currentNodeIndex > startNodeIndex ? currentNodeIndex - 1 : currentNodeIndex;
    }

    private static Queue<WeightedNode> putOnQueueTraversal(Queue<WeightedNode> queue, List<Integer> siblings, int weight) {
        for (Integer node : siblings) {
            queue.add(new WeightedNode(node, weight + 6));
        }
        return queue;
    }

    private static class WeightedNode {
        final Integer node;
        final int weight;

        public WeightedNode(Integer node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
