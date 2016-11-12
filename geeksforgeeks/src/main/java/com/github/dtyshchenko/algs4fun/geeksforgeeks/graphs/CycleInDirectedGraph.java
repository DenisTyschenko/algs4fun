package com.github.dtyshchenko.algs4fun.geeksforgeeks.graphs;

import java.util.*;
import static java.util.Collections.singleton;

/**
 *
 * <a href="http://www.geeksforgeeks.org/detect-cycle-in-a-graph/">
 *     Detect Cycle in a Directed Graph</a>
 *
 * Complicated original problem by reporting actual loops in graph.
 * It is possible to have unconnected nodes or even regions in graph as well.
 *
 * Just loop detection is much simpler - one boolean return type when returning from recursion
 *
 * @author denis on 11/10/16.
 */
public class CycleInDirectedGraph {

    public static Set<List<Integer>> loopsInGraph(List<Set<Integer>>graph) {
        Set<Integer> unvisitedNodes = new HashSet<>();
        for (int i = 0; i < graph.size(); i++) {
            unvisitedNodes.add(i);
        }
        Set<List<Integer>> loops = new HashSet<>();
        while(!unvisitedNodes.isEmpty()) {
            Integer node = unvisitedNodes.iterator().next();
            Set<Integer> regionVisitedNodes = new HashSet<>();
            loops.addAll(detectLoopsInRegion(node, graph, regionVisitedNodes, new HashSet<>()));
            unvisitedNodes.removeAll(regionVisitedNodes);
        }
        return loops;
    }

    public static Set<LinkedList<Integer>> detectLoopsInRegion(Integer source,
                                                               List<Set<Integer>> graph,
                                                               Set<Integer> visited,
                                                               Set<Integer> stack) {
        if (stack.contains(source)) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(source);
            return singleton(list);
        }

        /*
         * Need to have both stack and visited
         * visited - to track what nodes where traversed in general (graph may contain unconnected regions)
         * stack - to detect loops
         */
        visited.add(source);
        stack.add(source);

        Set<LinkedList<Integer>> loops = new HashSet<>();
        for (Integer sibling : graph.get(source)) {
            Set<LinkedList<Integer>> possibleLoops =
                    detectLoopsInRegion(sibling, graph, visited, stack);
            if (!possibleLoops.isEmpty()) {
                loops.addAll(possibleLoops);
            }
        }
        //iterate through existing solutions
        //add current node to those loops that have not being formed yet
        loops.stream()
                // completed loops are 1-5-1 or 1-2-3-1, last el equals to first
                // however depends on order of node traversal and on the node from which we
                // start loop recording, thus it is possible to have 5-1-5 of 2-3-1-2.
                // To fix the above, need to have some property associated with solution to
                // know whether it is a completed solution or no
                .filter(loop -> !isLoopCompleted(loop))
                //add current node to the head, as we are returning back from recursion
                //result is appended to the head
                .forEach(loop -> loop.offerFirst(source));

        stack.remove(source);

        return loops;
    }

    private static boolean isLoopCompleted(LinkedList<Integer> possibleLoop) {
        return possibleLoop.size() > 1 && possibleLoop.peekFirst().equals(possibleLoop.peekLast());
    }
}
