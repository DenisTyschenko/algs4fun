package com.github.dtyshchenko.algs4fun.geeksforgeeks.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;
import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/10/16.
 */
@RunWith(JUnitParamsRunner.class)
public class CycleInDirectedGraphTest {
    /**
     *   1  2  6  10  9
     *
     *   ---> 5 --> 8
     *  /           |
     * 0 <----------
     *
     *  3 <-> 7 -->  4
     *   \          /
     *    --> 11 <--
     */
    private static List<Set<Integer>> GRAPH11 = asList(
                /*0*/of(5),
                /*1*/emptySet(),
                /*2*/emptySet(),
                /*3*/of(7, 11),
                /*4*/of(11),
                /*5*/of(8),
                /*6*/emptySet(),
                /*7*/of(3, 4),
                /*8*/of(0),
                /*9*/emptySet(),
                /*10*/emptySet(),
                /*11*/emptySet()
    );

    /**
     *        4 - 6
     *      /    /
     *     1 - 5        10-11
     *   /      \       |
     *  0 - 2 - 7       12
     *   \
     *    3 - 8 - 9     13--
     *                  |__|
     *
     *                  14
     *
     *        ->4 -> 6
     *      /       /
     *     ->1<->5<-        10->11
     *   /          \       |
     *  0 <- 2 <- 7<-        ->12
     *   \
     *    >3 -> 8 -> 9     13--
     *                      |__|
     *
     *                      14
     */
    private static List<Set<Integer>> GRAPH14 = asList(
            of(1,3),//0
            of(4,5),//1
            of(0),//2
            of(8),//3
            of(6),//4
            of(1,7),//5
            of(5),//6
            of(2),//7
            of(9),//8
            of(),//9
            of(11,12),//10
            of(),//11
            of(),//12
            of(13),//13
            of()//14
    );

    /**
     *    1
     *   / \
     *  0 - 5
     */
    private static List<Set<Integer>> GRAPH15 = asList(
            of(1, 5),//0
            of(0, 5),//1
            emptySet(),//2
            emptySet(),//3
            emptySet(),//4
            of(0, 1),//5
            emptySet(),//6
            emptySet(),//7
            emptySet(),//8
            emptySet(),//9
            emptySet(),//10
            emptySet(),//11
            emptySet(),//12
            emptySet(),//13
            emptySet(),//14
            emptySet()//15
    );

    public static Object[] data() {
        return new Object[][]{
                {GRAPH11, of(asList(0,5,8,0), asList(3,7,3))},
                {GRAPH14, of(asList(0,1,4,6,5,7,2,0), asList(0,1,5,7,2,0),
                        asList(1,4,6,5,1), asList(1,5,1), asList(13,13))},
                // the below test case shows that loops are duplicated
                //5-1-5, 1-5-1 etc..
                // to get rid of this:
                //      need to report each node once(for 1-5-1 only report 1-5)
                {GRAPH15,
                        of(asList(0,1,5,0), asList(5,1,5), asList(0,5,0),
                         asList(0,5,1,0), asList(1,5,1), asList(0,1,0))},
        };
    }


    @Test
    @Parameters(method = "data")
    public void verifyCycleDetection(List<Set<Integer>> graph, Set<List<Integer>> expectedLoops) {
        Assert.assertThat(CycleInDirectedGraph.loopsInGraph(graph), is(expectedLoops));
    }

}
