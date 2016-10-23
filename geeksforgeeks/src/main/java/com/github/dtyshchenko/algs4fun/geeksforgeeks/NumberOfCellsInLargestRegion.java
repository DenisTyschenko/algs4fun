package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import java.io.InputStream;
import java.util.*;

import static java.util.Comparator.reverseOrder;

/**
 * <a href="https://www.hackerrank.com/challenges/connected-cell-in-a-grid">
 * <h3>Connected Cells in a Grid</h3></a>
 *
 * Consider a matrix with rows and columns, where each cell contains either a or a and any cell containing a is called a filled cell. Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally; in other words,
 * cell {@code [i][j]} is connected to cells
 * {@code [i-1][j-1],[i+1][j-1],[i+1][j+1],[i-1][j+1],[i-1][j],[i+1][j],[i][j-1],[i][j+1]}
 * provided that the location exists in the matrix for that .
 *
 * If one or more filled cells are also connected, they form a region. Note that each cell in a region is connected to at least one other cell in the region but is not necessarily directly connected to all the other cells in the region.
 *
 * <h4>Task</h4>
 * Given an matrix, find and print the number of cells in the largest region in the matrix. Note that there may be more than one region in the matrix.
 * <h4>Input Format</h4>
 * The first line contains an integer, , denoting the number of rows in the matrix.
 * The second line contains an integer, , denoting the number of columns in the matrix.
 * Each line of the subsequent lines contains space-separated integers describing the respective values filling each row in the matrix.
 *
 * @author denis on 10/22/16.
 */
public class NumberOfCellsInLargestRegion {

    public static int numberOfCellsInLargestRegion(String fileName) {
        return numberOfCellsInLargestRegion(
                NumberOfCellsInLargestRegion.class.
                        getClassLoader().
                        getResourceAsStream(fileName));
    }

    public static int numberOfCellsInLargestRegion(InputStream in) {
        Scanner sc = new Scanner(in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] matrix = readInputMatrix(sc, rows, cols);
        return numberOfCellsInLargestRegion(matrix, rows, cols);
    }

    private static int[][] readInputMatrix(Scanner sc, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            matrix[row] = new int[cols];
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = sc.nextInt();
            }
        }
        return matrix;
    }

    private static int numberOfCellsInLargestRegion(int[][] inMatrix, int rows, int cols) {
        List<Set<Integer>> graph = translateIntoAdjacencyMatrix(inMatrix, rows, cols);
        TreeSet<Integer> solution = findNumberOfNodesInEachRegionInDescendingOrder(inMatrix, rows, cols, graph);
        return solution.first();
    }

    private static TreeSet<Integer> findNumberOfNodesInEachRegionInDescendingOrder(int[][] inMatrix, int rows, int cols, List<Set<Integer>> graph) {
        //iterate through input and find region for each node if
        // - it has value "1" input
        // - it was not visited previously
        TreeSet<Integer> solution = new TreeSet<>(reverseOrder());
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int currentNodeVal = inMatrix[i][j];
                int currentGraphNodeIndex = toOneDimensionIndex(cols, i, j);
                if (currentNodeVal == 1 && !visited[currentGraphNodeIndex]) {
                    int nodesInRegion = DepthFirstSearchUtil
                            .nodesInRegionFormedByNodeIterative(currentGraphNodeIndex, graph, visited);
                    solution.add(nodesInRegion);
                }
            }
        }
        return solution;
    }

    public static List<Set<Integer>> translateIntoAdjacencyMatrix(int[][] inMatrix, int rows, int cols) {
        //translate input into adjacency matrix
        List<Set<Integer>> adjMatrix = new ArrayList<>(rows * cols);
        for (int i = 0; i < rows * cols; i++) {
            adjMatrix.add(new HashSet<>());
        }
        for (int i = 0; i < inMatrix.length; i++) {
            for (int j = 0; j < inMatrix[i].length; j++) {
                //current node val
                int cnv = inMatrix[i][j];
                //current node index in one dimensional array
                int cni = toOneDimensionIndex(cols, i, j);
                //go round around the node and connect
                //if current and neighbours have value 1 in input matrix
                connect(cnv, cni, inMatrix, adjMatrix, i, j - 1, rows, cols);
                connect(cnv, cni, inMatrix, adjMatrix, i, j + 1, rows, cols);
                connect(cnv, cni, inMatrix, adjMatrix, i + 1, j, rows, cols);
                connect(cnv, cni, inMatrix, adjMatrix, i - 1, j, rows, cols);

                connect(cnv, cni, inMatrix, adjMatrix, i - 1, j - 1, rows, cols);
                connect(cnv, cni, inMatrix, adjMatrix, i + 1, j - 1, rows, cols);
                connect(cnv, cni, inMatrix, adjMatrix, i - 1, j + 1, rows, cols);
                connect(cnv, cni, inMatrix, adjMatrix, i + 1, j + 1, rows, cols);
            }
        }
        return adjMatrix;
    }

    private static void connect(int currentNodeVal, int currentNodeIndex,
                                int[][] matrix, List<Set<Integer>> adjMatrix,
                                int k, int p, int rows, int cols) {
        if (isValidRange(k, p, rows, cols) &&
                currentNodeVal == 1 && currentNodeVal == matrix[k][p]) {
            int referenceNodeIndex = toOneDimensionIndex(cols, k, p);
            //interconnect two nodes if those are detected as neighbours
            adjMatrix.get(currentNodeIndex).add(referenceNodeIndex);
        }
    }

    private static boolean isValidRange(int k, int p, int rows, int cols) {
        return k >= 0 && p >= 0 && k < rows && p < cols;
    }

    private static int toOneDimensionIndex(int cols, int i, int j) {
        return i * cols + j;
    }
}
