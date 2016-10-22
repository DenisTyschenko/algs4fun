package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static com.github.dtyshchenko.algs4fun.geeksforgeeks.FindMaxValuesInSubarrays.maxValuesInSubarraysOf;
import static com.github.dtyshchenko.algs4fun.geeksforgeeks.FindMaxValuesInSubarrays.maxValuesInSubarraysOf_BST;
import static com.github.dtyshchenko.algs4fun.geeksforgeeks.FindMaxValuesInSubarrays.maxValuesInSubarraysOf_Dequeue;

/**
 * Created by denis on 10/20/16.
 */
@RunWith(JUnitParamsRunner.class)
public class FindMaxValuesInSubarraysTest {

    public static Collection<Object[]> failureData() {
        return Arrays.asList(new Object[][] {
                {new int[] {} , 0},
                {new int[] {1,2,3} , 0},
                {new int[] {} , 5},
                {new int[] {1,2,3} , -2}
        });
    }

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[] {1,2,3,1,2,3} , 3, new int[] {3,3,3,3}},
                {new int[] {1,2,3,1,2,3} , 2, new int[] {2,3,3,2,3}},
                {new int[] {1,2,3,1,2,3} , 1, new int[] {1,2,3,1,2,3}},
                {new int[] {3,2,1,0} , 2, new int[] {3,2,1}},
                {new int[] {9,8,7,6,5,4,3,2,1} , 4, new int[] {9,8,7,6,5,4}},
                {new int[] {9,8,7,6,10,4,3,2,11} , 4, new int[] {9,10,10,10,10,11}},
                {new int[] {9,9,9,9,1,1,1,1,11} , 4, new int[] {9,9,9,9,1,11}},
        });
    }

    @Test
    @Parameters(method = "data")
    public void verifyFindMaxValuesInSubarraysBruteForce(int[] inputArray, int subarraySize, int[] expectedMaxValues) {
        int[] actualMaxValues = maxValuesInSubarraysOf(inputArray, subarraySize);
        Assert.assertArrayEquals(expectedMaxValues, actualMaxValues);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "failureData")
    public void verifyCornerCasesBruteForce(int[] inputArray, int subarraySize) {
        maxValuesInSubarraysOf(inputArray, subarraySize);
    }

    @Test
    @Parameters(method = "data")
    public void verifyFindMaxValuesInSubarrays_WithBSTOptimization(int[] inputArray, int subarraySize, int[] expectedMaxValues) {
        Integer[] actualMaxValues = maxValuesInSubarraysOf_BST(mapToObj(inputArray), subarraySize);
        Assert.assertArrayEquals(mapToObj(expectedMaxValues), actualMaxValues);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "failureData")
    public void verifyCornerCases_WithBSTOptimization(int[] inputArray, int subarraySize) {
        maxValuesInSubarraysOf_BST(mapToObj(inputArray), subarraySize);
    }

    @Test
    @Parameters(method = "data")
    public void verifyFindMaxValuesInSubarrays_WithDequeOptimizaiton(int[] inputArray, int subarraySize, int[] expectedMaxValues) {
        Integer[] actualMaxValues = maxValuesInSubarraysOf_Dequeue(mapToObj(inputArray), subarraySize);
        Assert.assertArrayEquals(mapToObj(expectedMaxValues), actualMaxValues);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "failureData")
    public void verifyCornerCases_WithDequeOptimizaiton(int[] inputArray, int subarraySize) {
        maxValuesInSubarraysOf_Dequeue(mapToObj(inputArray), subarraySize);
    }

    private Integer[] mapToObj(int[] inputArray) {
        return Arrays.stream(inputArray).boxed().toArray(Integer[]::new);
    }
}
