package com.github.dtyshchenko.algs4fun.basics.sorting;

/**
 *
 * Sorting algorithm with O(NLogN)running time and O(N) additional space, holds stability property.
 *
 * @author denis on 10/25/16.
 */
public class MergeSort {

    /**
     * O(n) auxiliary space or O(2n) space complexity
     * Bottom up merge sort, without recursion.
     *
     * @param els
     * @return
     */
    public static int[] sort(int[] els) {
        int N = els.length;
        int[] aux = new int[N];
        // Taken from Sedgewik's algorithms
        // Notice Math.min(lo + sz + sz - 1, N - 1) - handles array with odd numbers of elements
        // Also notice that in case of array of odd element number the last element (which has odd number starting from 1)
        // will be merged (n-1) already sorted elements.
        // The outer loop iterates LogN times
        // inner loop iteration number depends on current shift size (any way in merge there will be iteration from lo to hi)
        // so consider N iterations per one outer loop iterations, thus N*LogN
        // Inner loop is designed to handle:
        // - Odd number of elements in array - handled in the last iteration,
        //   middle index will be N-2, last index will be N-1 (due to Math.min)
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo = lo + sz + sz) {
                merge(els, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
        return els;
    }

    private static int[] merge(int[] els, int[] aux, int lo, int mid, int hi) {
        // copy to aux[]. otherwise need to create temp array on each merge
        for (int k = lo; k <= hi; k++) {
            aux[k] = els[k];
        }

        // merge back to els[]
        for (int i = lo, li = lo, ri = mid+1; i <= hi; i++) {
            if (li > mid) {
                els[i] = aux[ri++];
            } else if (ri > hi) {
                els[i] = aux[li++];
            }
            //notice "<=" here, this preserves stability of merge sort !
            else if (aux[li] <= aux[ri]) {
                els[i] = aux[li++];
            } else {
                els[i] = aux[ri++];
            }
        }

        return els;
    }

    public static int[] sortRecursive(int[] els) {
        return els == null || els.length == 0 ? els : sortRecursive(els, 0, els.length - 1);
    }


    private static int[] sortRecursive(int[] els, int start, int end) {
        if (start >= end) {
            return new int[]{els[start]};
        } else {
            int middleOffset = (end - start) / 2;
            return merge(sortRecursive(els, start, start + middleOffset), sortRecursive(els, start + middleOffset + 1, end));
        }
    }

    private static int[] merge(int[] left, int[] right) {
        int[] mergeResult = new int[left.length + right.length];
        for (int i = 0, li = 0, ri = 0; i < mergeResult.length; i++) {
            if (li == left.length) {
                mergeResult[i] = right[ri++];
            } else if (ri == right.length) {
                mergeResult[i] = left[li++];
            } else if (left[li] < right[ri]) {
                mergeResult[i] = left[li++];
            } else {
                mergeResult[i] = right[ri++];
            }
        }
        return mergeResult;
    }

}
