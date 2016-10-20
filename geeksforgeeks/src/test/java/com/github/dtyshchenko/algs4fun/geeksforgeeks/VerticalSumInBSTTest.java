package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static com.github.dtyshchenko.algs4fun.geeksforgeeks.VerticalSumInBST.verticalSumOfBST;

/**
 * Created by denis on 10/20/16.
 */
@RunWith(Parameterized.class)
public class VerticalSumInBSTTest {

    @Parameterized.Parameters(name = "Test: {index}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                //array should represent a binary search tree with the root at 0
                //each sibling is 2*index + 1 (left) and 2*index+2 (right)
                {new int[]{0, 1, 2, 3, 4, 5, 6},
                        ImmutableMap.<Integer, Integer>builder().
                                put(-2, 3).
                                put(-1, 1).
                                put(0, 9).
                                put(1, 2).
                                put(2, 6)
                        .build()
                },
                {new int[]{0, 1, 2},
                        ImmutableMap.<Integer, Integer>builder().
                                put(-1, 1).
                                put(0, 0).
                                put(1, 2)
                        .build()
                },
                {new int[]{0},
                        ImmutableMap.<Integer, Integer>builder().
                                put(0, 0).build()
                }
        });
    }

    @Parameterized.Parameter
    public int[] inputBst;
    @Parameterized.Parameter(1)
    public Map<Integer, Integer> expectedSums;

    @Test
    public void verifyVerticalBSTSum() {
        Map<Integer, Integer> actualSums = verticalSumOfBST(inputBst);
        Assert.assertEquals(expectedSums, actualSums);
    }

    @Ignore("TODO: not supported at the moment")
    @Test
    public void verifySumOnBSTWithNodesThatHasLessThanTwoChilds() {
        Map<Integer, Integer> actualSums = verticalSumOfBST(new int[]{0, 1, 2, 3, 4, 5});
        Assert.assertEquals(expectedSums, actualSums);    }
}
