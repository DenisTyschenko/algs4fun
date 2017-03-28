package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Scanner;

/**
 * Created by denis on 2/14/17.
 */
@RunWith(JUnitParamsRunner.class)
public class ShortReachInAGraphTest {

    public static Object[] data() {
        return new Object[][]{
                {
                        "4 2\n" +
                                "1 2\n" +
                                "1 3\n" +
                                "1",
                        new int[] {6, 6, -1}
                },
                {
                    "3 1\n" +
                            "2 3\n" +
                            "2",
                        new int[] {-1, 6}
                }
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyGraphQuery(String input, int[] expectedDistances) {
        Scanner scanner = new Scanner(input);

        // Create a graph of size n where each edge weight is 6:
        ShortReachInAGraph.Graph graph = new ShortReachInAGraph.Graph(scanner.nextInt());
        int m = scanner.nextInt();

        // read and set edges
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;

            // add each edge to the graph
            graph.addEdge(u, v);
        }

        // Find shortest reach from node s
        int startId = scanner.nextInt() - 1;
        int[] actualDistances = graph.shortestReach(startId);

        scanner.close();

        Assert.assertArrayEquals(expectedDistances, exclude(startId, actualDistances));
    }

    /**
     * Added to test just for compatibility with hackerrank tests
     */
    public static int[] exclude(int index, int[] els) {
        int[] result = new int[els.length - 1];
        for (int i = 0; i < els.length - 1; i++) {
            result[i] = i < index ? els[i] : els[i + 1];
        }
        return result;
    }


}