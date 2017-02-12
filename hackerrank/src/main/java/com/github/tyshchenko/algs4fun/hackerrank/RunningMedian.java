package com.github.tyshchenko.algs4fun.hackerrank;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * <a href="https://www.hackerrank.com/challenges/ctci-find-the-running-median">Heaps: Find the Running Median</a>
 * <p>
 * Solution divides current snapshot into to half's:
 * <ul>
 *     <li>max heap - to track first half, max element - is the most closest to the center element in sorted sequence</li>
 *     <li>min heap - to track second half, min element - is the most closest to the center element in sorted sequence</li>
 * </ul>
 *
 * Invariant ensures that in case of odd number of elements, minheap.size() > maxheap.size()
 * For each new element added left and right half's need to be balanced (possible switch of MAX/MIN elements).
 *
 * Result is built upon current size (odd or even):
 * <ul>
 *     <li>odd: min element from minheap</li>
 *     <li>even: (max + min) / 2</li>
 * </ul>
 * </p>
 *
 * Complexity:
 * <p>
 * For each new element we either insert into right or left half of max heap (log(items in heap))
 * with possible chance to rebalance left and right half's 2 * log(items in right heap) + 2 * log(items in left heap)
 * In  general for each new element to compute a median we will perform 1 insertion into heap and
 * in case of rebalancing 2 insertions and 2 removals from heaps.
 * <br/>
 * Therefore time complexity is O(N * logN).
 * Space is O(N)
 * </p>
 *
 * Created by denis on 2/12/17.
 */
public class RunningMedian {

    /**
     * @param items - input array to calculate running median for each next item
     * @return array of median items calculated from the original array of items
     */
    public static BigDecimal[] find(Integer[] items) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Input must not be null or empty");
        }

        PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

        BigDecimal[] result = new BigDecimal[items.length];

        rightMinHeap.offer(items[0]);
        //first element is result by definition
        result[0] = toResult(items[0]);

        for (int i = 1, size = i + 1; i < items.length; i++, size++) {
            if (size % 2 == 0) {
                //even num of input elements
                leftMaxHeap.offer(items[i]);
                balance(leftMaxHeap, rightMinHeap);
                result[i] = toResult(leftMaxHeap.peek() + rightMinHeap.peek())
                        .divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP);
            } else {
                //odd num of input elements
                rightMinHeap.offer(items[i]);
                balance(leftMaxHeap, rightMinHeap);
                result[i] = toResult(rightMinHeap.peek());
            }
        }
        return result;
    }

    private static BigDecimal toResult(Integer item) {
        return new BigDecimal(item).setScale(1, BigDecimal.ROUND_HALF_UP);
    }

    private static void balance(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.peek() > right.peek()) {
            Integer maxLeft = left.poll();
            Integer minRight = right.poll();
            left.offer(minRight);
            right.offer(maxLeft);
        }
    }
}
