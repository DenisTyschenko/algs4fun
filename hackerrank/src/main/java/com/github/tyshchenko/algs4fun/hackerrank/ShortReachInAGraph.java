package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.*;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach">
 * BFS: Shortest Reach in a Graph</a>
 *
 * Created by denis on 2/14/17.
 */
public class ShortReachInAGraph {

    private static final int NOT_VISITED = -1;
    private static final int NODE_DISTANCE = 6;

    public static class Graph {

        private final List<List<Integer>> adjacency;
        private final int size;

        public Graph(int size) {
            this.size = size;
            adjacency = new ArrayList<List<Integer>>(size);
            for (int i = 0; i < size; i++) {
                adjacency.add(new ArrayList<Integer>());
            }
        }

        public void addEdge(int first, int second) {
            adjacency.get(first).add(second);
            adjacency.get(second).add(first);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            int[] distances = new int[size];
            Arrays.fill(distances, NOT_VISITED);

            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(0, startId));

            while (!queue.isEmpty()) {
                Node currentNode = queue.poll();
                if (distances[currentNode.id] == NOT_VISITED) {
                    for (Integer adjacent : adjacency.get(currentNode.id)) {
                        Node adjacentNode = new Node(currentNode.distance + NODE_DISTANCE, adjacent);
                        queue.offer(adjacentNode);
                    }
                    distances[currentNode.id] = currentNode.distance;
                }
            }
            return distances;
        }
    }

    public static class Node {
        private final int distance;
        private final int id;

        public Node(int distance, int id) {
            this.distance = distance;
            this.id = id;
        }
    }
}
