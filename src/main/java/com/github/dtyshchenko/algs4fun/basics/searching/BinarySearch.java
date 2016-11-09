package com.github.dtyshchenko.algs4fun.basics.searching;

/**
 * Searches on already sorted array in O(LogN)
 *
 * @author denis on 10/26/16.
 */
public class BinarySearch {
    /**
     * Iterative version to search for element index in a sorted array(ascending order)
     * in O(LogN)
     */
    public static int index(int[] els, int el) {
        int lo = 0, hi = els.length - 1;
        while (lo <= hi) {
            // mid index lo + mid offset
            int mid = lo + (hi - lo) / 2;
            if (el == els[mid]) return mid;
            if (el < els[mid]) {
                hi = mid - 1;
            } else if (el > els[mid]) {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public static int indexRecursive(int[] els, int el) {
        return indexRecursive(els, el, 0, els.length - 1);
    }
    /**
     * Tail recursive so can be easily translated into loop variant {@link #index(int[], int)}
     */
    private static int indexRecursive(int[] els, int el, int lo, int hi) {
        int mid = (hi - lo) / 2;
        int midIndex = lo + mid;
        if (lo <= hi) {
            if (els[midIndex] == el) return midIndex;
            if (el < els[midIndex]) return indexRecursive(els, el, lo, midIndex - 1);
            if (el > els[midIndex]) return indexRecursive(els, el, midIndex + 1, hi);
        }
        return -1;
    }


}
