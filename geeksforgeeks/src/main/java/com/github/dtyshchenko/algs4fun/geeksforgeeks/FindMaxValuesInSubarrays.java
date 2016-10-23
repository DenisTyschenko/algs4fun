package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

import static java.util.Comparator.reverseOrder;
import static java.util.Objects.requireNonNull;

/**
 * <a href="http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/">Maximum of all subarrays of size k</a>
 * <p>
 * Given an array and an integer k, find the maximum for each and every contiguous subarray of size k.
 * Examples:
 * Input :
 * arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
 * k = 3
 * Output :
 * 3 3 4 5 5 5 6
 * <p>
 * Input :
 * arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
 * k = 4
 * Output :
 * 10 10 10 15 15 90 90
 * <p>
 * Three solutions with different complexities. The best one is O(n)
 * <p>
 *
 * @author denis on 10/20/16.
 */
public class FindMaxValuesInSubarrays {

    /**
     * Given <i>abcdef</i> array and {@code k = 3}<br/>
     * Subarrays are:
     * <ul>
     * <li>abc</li>
     * <li>bcd</li>
     * <li>cde</li>
     * <li>def</li>
     * </ul>
     * <p>
     * Complexity varies on k and n value and is O(k(n-k+1)),
     * Worst case when k = n/2 with complexity O(n^2) - n/2 iterations in outer and n/2 iterations
     * in nested loops
     *
     * @param elements - input
     * @param k        - size of subarray
     * @return - array of max values, every item is max value in each k subarray
     */
    public static int[] maxValuesInSubarraysOf(int[] elements, int k) {
        requireNonNull(elements, "Input array must not be null");
        verifyPreconditions(elements.length, k);
        int[] maxValues = createSolutionArray(elements, k);
        for (int i = 0; i < maxValues.length; i++) {
            int max = elements[i];
            for (int j = i; j < i + k; j++) {
                max = max < elements[j] ? elements[j] : max;
            }
            maxValues[i] = max;
        }
        return maxValues;
    }

    /**
     * Use a slice window of size k. Slice window is a Balanced BST.
     * Iterate through original array and shift window one position to the right on each iteration.
     * Before the shift, query BST for max element which gives the one solution for one subarray.
     * <br/>
     * Complexity is O(n*log(k))
     * <ul>
     * <li>n for number of iteration through original array, for every iteration</li>
     * <li>On every iteration add element to Balanced BST which implies log(k)</li>
     * <li>After slice is full on every iteration query for the max element,
     * which is a max value for one subarray (one element of solution array),
     * remove stale element (that was added first to the slice window) from Balanced BST (log(k)) and then
     * add next element to Balanced BST (log(k))</li>
     * </ul>
     */
    public static Integer[] maxValuesInSubarraysOf_BST(Integer[] elements, int k) {
        requireNonNull(elements, "Input array must not be null");
        verifyPreconditions(elements.length, k);
        Integer[] maxValues = createSolutionArray(elements, k);
        // key is an element value, value is an element index
        // need to track indexes in slice to ensure that left most element can be removed after window shift,
        // consider the case when input 5,5,5,1,2 with k=4
        // after first shift if remove 5 from slice without index check, all 5's will be gone
        // notice that after slice was filled 5 with index 3 is present in slice, as it has been overridden few times.
        TreeMap<Integer, Integer> slice = new TreeMap<>(reverseOrder());

        //fill first slice first
        for (int i = 0; i < k; i++) {
            slice.put(elements[i], i);
        }
        maxValues[0] = slice.firstKey();
        //define element to be removed from slice
        int stale = elements[0];
        for (int i = k; i < elements.length; i++) {
            int sliceStartIndex = i - k + 1;
            //to handle several equals els, like 5,5,5,1,2 with k=4
            //if ignore index all 5 will be removed
            if (slice.get(stale) < sliceStartIndex) {
                slice.remove(stale);
            }
            slice.put(elements[i], i);
            stale = elements[sliceStartIndex];
            maxValues[sliceStartIndex] = slice.firstKey();
        }
        return maxValues;
    }

    /**
     * Use a slice window of size k. Slice window is a Deque.
     * Complexity is O(n)
     * <ul>
     * <li>
     * Slice window contains indexes only (to recognize stale elements after window shift in the loop below)
     * </li>
     * <li>
     * Left most index in slice represents the max element with regards to current window pos
     * Issues possible iteration over slice from right to left,
     * as shown below for worst case does not introduce much complexity
     * </li>
     * <li>
     * Right most index in slice represents the min element with regards to current window pos
     * </li>
     * <li>
     * Order of elements in original array must be preserved
     * </li>
     * </ul>
     * <h3>Worst case is O(2n-2)</h3>
     * <pre>
     * {@code
     *
     * Trace worst case scenario:
     *
     *   7 6 5 8 7 6 9
     * . 7 6 5              -> next window shift adds the maximum from right side,
     *                         to hold invariant that max must be the left most need to traverse the slice window
     *   . . . 8            -> to cleanup we need to traverse (k-1) els in slice window, or n/2-1 elements of original array
     *
     *       . 8 7 6        -> next window shift adds the maximum from right side,
     *                         to hold invariant that max must be the left most need to traverse the slice window
     *         . . . 9      -> to cleanup we need to traverse (k-1) els in slice window, or n/2-1 elements of original array
     *  Eventually there was n iterations on original array and 2 cleanups which caused additional 2*(n/2-1) = n-2
     *  Total n + n - 2 = 2n-2
     *  }
     *  </pre>
     */
    public static Integer[] maxValuesInSubarraysOf_Dequeue(Integer[] elements, int k) {
        requireNonNull(elements, "Input array must not be null");
        verifyPreconditions(elements.length, k);
        Integer[] solutions = createSolutionArray(elements, k);
        Deque<Integer> sliceIndexes = new ArrayDeque<>(k);
        for (int i = 0; i < k; i++) {
            //slice window contains indexes only (to recognize stale elements after window shift in the loop below)
            //left most index in slice represents the max element with regards to current window pos
            //right most index in slice represents the min element with regards to current window pos
            //order of elements in original array must be preserved
            while (!sliceIndexes.isEmpty() && elements[i] > elements[sliceIndexes.peekLast()]) {
                sliceIndexes.pollLast();
            }
            sliceIndexes.offerLast(i);
        }
        for (int sliceEndIndex = k; sliceEndIndex < elements.length; sliceEndIndex++) {
            Integer maxElementIndex = sliceIndexes.peekFirst();
            solutions[sliceEndIndex - k] = elements[maxElementIndex];
            Integer sliceStartIndex = sliceEndIndex - k + 1;
            //if window has been shifted and the leftmost element no more relevant then get rid of it
            if (maxElementIndex < sliceStartIndex) {
                sliceIndexes.pollFirst();
            }
            while (!sliceIndexes.isEmpty() && elements[sliceEndIndex] > elements[sliceIndexes.peekLast()]) {
                sliceIndexes.pollLast();
            }
            sliceIndexes.offerLast(sliceEndIndex);
        }
        solutions[solutions.length - 1] = elements[sliceIndexes.pollFirst()];
        return solutions;
    }

    private static int[] createSolutionArray(int[] elements, int k) {
        return new int[elements.length - k + 1];
    }

    private static Integer[] createSolutionArray(Integer[] elements, int k) {
        return new Integer[elements.length - k + 1];
    }

    private static void verifyPreconditions(int elementsLength, int k) {
        if (k > elementsLength || k <= 0) {
            throw new IllegalArgumentException("Subarray length must be greater than zero and " +
                    "must not be greater than input array length");
        }
    }
}