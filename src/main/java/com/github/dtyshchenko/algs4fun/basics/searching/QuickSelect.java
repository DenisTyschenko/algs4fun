package com.github.dtyshchenko.algs4fun.basics.searching;

import java.util.*;

/**
 * <a href="http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/">
 *     K’th Smallest/Largest Element in Unsorted Array</a>
 * <p>
 * Find k smallest elements in unsorted array in O(n).
 * Corresponds to method 4 in original task solution.
 * Partition function of quick sort is taken from Sedgewick's "Algorithms" book.
 * Partition also done as randomized function to make theoretically possible O(n^2) very rare,
 * thus average complexity is O(n)
 * </p>
 *
 * @author denis on 11/12/16.
 */
public class QuickSelect {

    /**
     * @return new array with k minimum elements,
     * however algorithm is in place,
     * so new array creation is just for convenient testing
     */
    public static int[] selectMinimumElements(int k, int[] els) {
        int indexOfLastMinimum = select(k, 0, els.length - 1, els);
        return Arrays.copyOf(els, indexOfLastMinimum + 1);
    }

    private static int select(int k, int lo, int hi, int[] els) {
        int i = (hi == lo) ? hi : randomizedPartition(lo, hi, els);
        int loToIndex = i - lo + 1;
        if (k == loToIndex) {
            return i;
        } else if (k < loToIndex) {
            return select(k, lo, i - 1, els);
        } else {// k > i+1
            return select(k - loToIndex, i+1, hi, els);
        }
    }

    public static int randomizedPartition(int lo, int hi, int[] els) {
        Random random = new Random();
        // random num from 0 till hi - lo + 1
        // if lo - 3 , hi = 5, we have 3 possibility hi-lo+1 = 3
        // nextInt(3) gives random num from 0 till 3 (exclusive)
        int ri = lo + random.nextInt(hi - lo + 1);
        exch(lo, ri, els);
        //now els[lo] is randomly chosen element
        //next put every element that is less to the left of partition and
        //all that are greater to the right of the partition
        return partition(lo, hi, els);
    }
    /**
     * Standard partition of QuickSort as in Sedgewick's "Algorithms" book.
     *
     * Returns index of element pasted onto correct position in array,
     * i.e. all the elements that are left to the element are smaller,
     * all elements that are right to the element are larger
     */
    public static int partition(int lo, int hi, int[] els) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (els[++i] < els[lo]) if (i == hi) break;
            while (els[--j] > els[lo]) if (j == lo) break;
            if (i >= j) break;
            exch(i, j, els);
        }
        exch(lo, j, els);
        return j;
    }

    private static void exch(int i, int j, int[] els) {
        int swap = els[j];
        els[j] = els[i];
        els[i] = swap;
    }

}
