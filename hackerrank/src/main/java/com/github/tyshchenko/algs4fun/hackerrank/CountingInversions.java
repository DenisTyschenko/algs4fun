package com.github.tyshchenko.algs4fun.hackerrank;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-merge-sort">
 *     Merge Sort: Counting Inversions </a>
 *
 * Created by denis on 3/22/17.
 */
public class CountingInversions {

    /**
     * Using long as a return type to support test data provided by hackerrank tests
     */
    public static long countInversions(int[] items) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Only non empty array is allowed");
        }

        int[] aux = items.clone();
        return countInversions(items, aux, 0, items.length - 1);
    }

    private static long countInversions(int[] items, int[] aux, int lo, int hi) {
        if (hi <= lo) {
            return 0;
        }
        int mid = (lo + hi) / 2;
        long leftInversions = countInversions(items, aux, lo, mid);
        long rightInversions = countInversions(items, aux, mid + 1, hi);
        return merge(items, aux, lo, mid, hi) + leftInversions + rightInversions;
    }

    private static long merge(int[] items, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = items[i];
        }

        long inversions = 0;
        for (int left = lo, leftLength = mid + 1, right = leftLength, index = left;
             index <= hi;
             index++) {
            if (left > mid) {
                //copy from right
                items[index] = aux[right++];
                continue;
            }
            if (right > hi) {
                //copy from left
                items[index] = aux[left++];
                continue;
            }

            if (aux[left] <= aux[right]) {
                items[index] = aux[left++];
            } else {
                items[index] = aux[right++];
                //count inversion
                inversions += (leftLength - left);
            }
        }
        return inversions;
    }


}
