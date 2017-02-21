package com.github.tyshchenko.algs4fun.hackerrank;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid">
 *     DFS: Connected Cell in a Grid</a>
 *
 * This solution uses original input matrix to perform DFS search without
 * any transformations to intermediate adjacency matrix.
 *
 *
 * In general analysis for DFS {@link #nodesInRegionOf(int, int)}:
 * <ul>
 *     <li>
 *         Number of recursion spawn is O(V) where V is hte number of vertexes in graph
 *     </li>
 *     <li>
 *         Number of times loop is executed is equal to the number of edges from the current node
 *         and in total is O(E)
 *     </li>
 * </ul>
 *
 * The overall complexity is O(V + E)
 *
 * Created by denis on 2/16/17.
 */
public class DFSConnectedCellsInAGrid {
    public static final int VISITED_NODE = -1;
    public static final int SIGNIFICANT_NON_VISITED_NODE = 1;

    private final int rows;
    private final int cols;
    private final int[][] inputGrid;

    public DFSConnectedCellsInAGrid(int rows, int cols, int[][] inputGrid) {
        this.rows = rows;
        this.cols = cols;
        this.inputGrid = inputGrid;
    }

    public int findLargestRegionInGrid() {
        int maxNodesInRegion = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (inputGrid[i][j] == SIGNIFICANT_NON_VISITED_NODE) {
                    maxNodesInRegion = Math.max(maxNodesInRegion, nodesInRegionOf(i, j));
                }
            }
        }
        return maxNodesInRegion;
    }

    private boolean isValidNonVisitedNode(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return inputGrid[row][col] == SIGNIFICANT_NON_VISITED_NODE;
        }
        return false;
    }

    /**
     * In general analysis for DFS:
     * <ul>
     *     <li>
     *         Number of recursion spawn is O(V) where V is hte number of vertexes in graph
     *     </li>
     *     <li>
     *         Number of times loop is executed is equal to the number of edges from the current node
     *         and in total is O(E)
     *     </li>
     * </ul>
     *
     * The overall complexity is O(V + E)
     */
    private int nodesInRegionOf(int row, int col) {
        //mark current as visited
        inputGrid[row][col] = VISITED_NODE;
        //include current node
        int nodesInRegion = 1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int x = row + i;
                int y = col + j;
                if (isValidNonVisitedNode(x, y)) {
                    nodesInRegion += nodesInRegionOf(x, y);
                }
            }
        }
        return nodesInRegion;
    }

}
