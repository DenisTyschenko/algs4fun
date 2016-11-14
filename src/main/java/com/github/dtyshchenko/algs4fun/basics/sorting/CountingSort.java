package com.github.dtyshchenko.algs4fun.basics.sorting;

/**
 * <a href="http://www.geeksforgeeks.org/counting-sort/">Counting Sort</a> implementation.
 * <ul>
 *     <li>
 *          First sort method generates result over iteration on input sequence.
 *     </li>
 *     <li>
 *          Second is generating result during iteration on range array,
 *          notice that we still need to iterate over range array in first method.
 *          No need to use auxiliary input array for the second method
 *     </li>
 * </ul>
 *
 * Sort method is stable (depends on implementation when it comes to compound object,
 * however stability is easily achieved by proper order of iteration of range(count) array and
 * order of retrieval of equal elements)
 *
 * @author denis on 11/14/16.
 */
public class CountingSort {

    /**
     * Performance O(k+n) with auxiliary space O(k+n)
     */
    public static int[] sort(int[] a, int lo, int hi) {
        int k = hi - lo + 1;
        int[] output = new int[a.length];
        int[] count = new int[k + 1];
        for (int item : a) {
            count[item - lo]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = a.length - 1; i >= 0; i--) {
            output[count[a[i] - lo] - 1] = a[i];
            count[a[i] - lo]--;
        }
        return output;
    }

    /**
     * Performance O(k+n) with auxiliary space O(k)
     */
    public static int[] sortSpaceReduced(int[] a, int lo, int hi) {
        int r = hi - lo + 1;
        int[] count = new int[r];
        for (int i = 0; i < a.length; count[a[i++] - lo]++);
        for (int i = count.length-1, j = a.length-1; i >= 0; i--) {
            while (count[i] > 0) {
                a[j--] = i + lo;
                count[i]--;
            }
        }
        return a;
    }
}
