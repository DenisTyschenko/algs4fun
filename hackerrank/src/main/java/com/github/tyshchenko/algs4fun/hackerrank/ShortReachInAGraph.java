package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.*;

import static java.util.stream.Collectors.joining;

/**
 * //FIXME: revisit, overcompicated solution
 *
 * <a href="https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach">
 * BFS: Shortest Reach in a Graph</a>
 * <p>
 * Created by denis on 2/14/17.
 */
public class ShortReachInAGraph {

    private static final int NOT_VISITED = -1;

    /**
     * Controller method to accept input, convert input into multiple graphs,
     * provide shortest path computation and create view of the result
     *
     * Such flow can be represented in terms of MVC pattern,
     * however to be able to submit solution to hackerrank online verifier will use
     * one single class decomposed on multiple utility methods.
     */
    public static String shortestPathsForMultiQueries(String input) {
        Scanner sc = new Scanner(input);
        int queriesNum = sc.nextInt();
        StringBuilder output = new StringBuilder();
        for (; queriesNum > 1; queriesNum--) {
            output.append(shortestPathForQuery(sc))
                    .append("\n");
        }
        return output.append(shortestPathForQuery(sc))
                .toString();
    }

    private static String shortestPathForQuery(Scanner sc) {
        List<List<Integer>> adjacency = readAdjacencyMatrix(sc);
        int startNode = toNodeIndex(sc.nextInt());
        int[] shortestPaths = shortestPathsFrom(startNode, adjacency);
        return Arrays.stream(shortestPaths)
                .mapToObj(String::valueOf)
                .collect(joining(" "));
    }

    static List<List<Integer>> readAdjacencyMatrix(Scanner sc) {
        int nodes = sc.nextInt();
        List<List<Integer>> adjacency = new ArrayList<>(nodes);

        for (int i = 0; i < nodes; i++) {
            adjacency.add(new ArrayList<>());
        }

        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            int nodeA = toNodeIndex(sc.nextInt());
            int nodeB = toNodeIndex(sc.nextInt());
            adjacency.get(nodeA).add(nodeB);
            adjacency.get(nodeB).add(nodeA);
        }

        return adjacency;
    }

    private static int toNodeIndex(int node) {
        return node - 1;
    }
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
