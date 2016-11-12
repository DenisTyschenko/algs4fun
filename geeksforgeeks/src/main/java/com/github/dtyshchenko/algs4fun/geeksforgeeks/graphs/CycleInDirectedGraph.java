package com.github.dtyshchenko.algs4fun.geeksforgeeks.graphs;

import java.util.*;

import static java.util.Collections.singleton;

/**
 * //FIXME: incorrect solution, see tests.
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
            loops.addAll(detectLoopsInRegion(node, graph, regionVisitedNodes));
            unvisitedNodes.removeAll(regionVisitedNodes);
        }
        return loops;
    }

    public static Set<LinkedList<Integer>> detectLoopsInRegion(Integer source,
                                                     List<Set<Integer>> graph,
                                                     Set<Integer> visited) {
        if (visited.contains(source)) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(source);
            return singleton(list);
        }

        visited.add(source);

        Set<LinkedList<Integer>> loops = new HashSet<>();
        for (Integer sibling : graph.get(source)) {
            loops = detectLoopsInRegion(sibling, graph, visited);
            loops.stream()
                    .filter(loop -> !isLoopCompleted(loop))
                    .forEach(loop -> loop.add(source));
        }
        return loops;
    }

    private static boolean isLoopCompleted(LinkedList<Integer> possibleLoop) {
        return possibleLoop.size() > 1 && possibleLoop.peekFirst().equals(possibleLoop.peekLast());
    }
}
