package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by denis on 2/16/17.
 */
public class DFSConnectedCellsInAGrid {

    public static int largestRegionInGrid(int[][] inputGrid) {
        return -1;
    }

    public static List<Set<Integer>> toAdjacencyMatrix(int rows, int cols, int[][] inputGrid) {
        int nodes = rows * cols;
        List<Set<Integer>> adjacency = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) {
            adjacency.add(new HashSet<>());
        }

        for (int row = 0, currentNodeIndex = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++, currentNodeIndex++) {
                if (inputGrid[row][col] == 1) {
                    adjacency.get(currentNodeIndex).add(currentNodeIndex);
                    connectToCurrent(row, col - 1, currentNodeIndex, inputGrid, adjacency, rows, cols);
                    connectToCurrent(row + 1, col - 1, currentNodeIndex, inputGrid, adjacency, rows, cols);
                    connectToCurrent(row + 1, col, currentNodeIndex, inputGrid, adjacency, rows, cols);
                    connectToCurrent(row + 1, col + 1, currentNodeIndex, inputGrid, adjacency, rows, cols);
                    connectToCurrent(row, col + 1, currentNodeIndex, inputGrid, adjacency, rows, cols);
                    connectToCurrent(row - 1, col + 1, currentNodeIndex, inputGrid, adjacency, rows, cols);
                    connectToCurrent(row - 1, col, currentNodeIndex, inputGrid, adjacency, rows, cols);
                    connectToCurrent(row - 1, col - 1, currentNodeIndex, inputGrid, adjacency, rows, cols);
                }
            }
        }
        return adjacency;
    }

    private static void connectToCurrent(int row, int col, int currentNode,
                                         int[][] inputGrid, List<Set<Integer>> adjacency,
                                         int rows, int cols) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }
        if (inputGrid[row][col] == 1) {
            adjacency.get(currentNode).add(toNodeIndex(row, col, cols));
        }
    }
    private static int toNodeIndex(int row, int col, int cols) {
        return row * cols + col;
    }

    public static int largestRegion(int[][] adjacency) {
        return -1;
    }
}
