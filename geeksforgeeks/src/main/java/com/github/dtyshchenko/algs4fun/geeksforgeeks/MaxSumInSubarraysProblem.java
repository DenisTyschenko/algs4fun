package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import static java.lang.Math.max;

/**
 * <a href="http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/">Largest Sum Contiguous Subarray</a>
 *
 * @author denis on 10/27/16.
 */
public class MaxSumInSubarraysProblem {

    /**
     * As per original problem :
     * <p>
     * Write an efficient program to find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum.
     * </p>
     * Handles the case of all negative elements.
     *
     */
    public static int findMaxSumInArbitraryContiguousSubarray(int[] els) {
        int maxSum = els[0];
        int sum = els[0];
        for (int i = 1; i < els.length; i++) {
            sum = max(sum + els[i], els[i]);
            maxSum = max(maxSum, sum);
        }
        return maxSum;
    }

    public static int findMaxSumInContiguousSubarrays(int[] els, int k) {
        //calculate the sum of first window
        int wsum = 0;
        int i = 0;
        for (; i < k; i++) {
            wsum += els[i];
        }
        int maxSum = wsum;
        // i == k
        for (; i < els.length; i++) {
            int wsi = i - k; // index of element that no more present in window
            wsum -= els[wsi]; // remove quantity of stale element from window sum
            wsum += els[i]; // add quantity of new element to the window sum
            maxSum = maxSum < wsum ? wsum : maxSum; // check whether it is a new max or not
        }
        return maxSum;
    }

    public static int findMaxSumInNotContiguousSubarrays(int[] els, int k) {
        // can't just init max with 0 as input may contain negative values
        int maxSum = Integer.MIN_VALUE;
        //the last remainder of input array with length less than k will be ignored
        for (int i = 0; i <= els.length - k; i += k) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += els[j];
            }
            maxSum = maxSum < sum ? sum : maxSum;
        }
        return maxSum;
    }

}
