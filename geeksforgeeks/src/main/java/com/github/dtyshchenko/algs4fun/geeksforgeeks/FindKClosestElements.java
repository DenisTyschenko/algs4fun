package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://www.geeksforgeeks.org/find-k-closest-elements-given-value/">
 *     Find k closest elements to a given value</a>
 * <p>
 *     Given a sorted array arr[] and a value X, find the k closest elements to X in arr[].
 *     Examples:
 *
 *     Input: K = 4, X = 35
 *     arr[] = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56}
 *     Output: 30 39 42 45
 *
 *     <b>Note that if the element is present in array, then it should not be in output, only the other closest elements are required.</b>
 * </p>
 * <p>
 *     Complexity is O(LogN + k).
 *     O(LogN) because of using binary sort to find the crossover point
 *     O(k) to iterate over k elements around crossover point to find k closest elements
 * </p>
 * @author denis on 11/2/16.
 */
public class FindKClosestElements {

    public static List<Integer> findClosestElements(int k, int x, List<Integer> els) {
        List<Integer> result = new ArrayList<>(k);
        CP crossoverPoint = findCP(x, els);
        for (int p = 0,
             // i - refers to els that are less than X, j - to els that are greater
             left = crossoverPoint.left,
             right = crossoverPoint.right;
             p < k && (left >= 0 || right < els.size());
             p++) {

            if (left < 0) {
                result.add(els.get(right++));
                continue;
            }
            if (right >= els.size()) {
                result.add(els.get(left--));
                continue;
            }
            int leftDiff = x - els.get(left);
            int rightDiff = els.get(right) - x;
            // "<=" if distance to the right and left
            // is the same then prefer smaller (left) element
            result.add(leftDiff <= rightDiff ?
                    els.get(left--) : els.get(right++));
        }
        return result;
    }

    /**
     * Find index of crossover point, point before which elements are smaller than X
     * and after which elements are greater than X
     * Using Binary Search.
     */
    private static CP findCP(int x, List<Integer> els) {
        int lo = 0, hi = els.size() - 1;
        int midIndex;
        while (hi >= lo) {
            midIndex = lo + (hi - lo) / 2;
            if (x == els.get(midIndex)) {
                return new CP(midIndex - 1, midIndex + 1);
            }
            if (x < els.get(midIndex)) {
                hi = midIndex - 1;
            } else {
                lo = midIndex + 1;
            }
        }
        return new CP(hi, lo);
    }

    private static class CP {
        private final int left;
        private final int right;

        CP(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "(" + left + "," + right + ")";
        }
    }

}
