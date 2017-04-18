package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <a href="https://www.hackerrank.com/challenges/non-divisible-subset">
 *     Non-Divisible Subset</a>
 *
 * Created by dtyshenko on 4/18/2017.
 */
public class NonDivisibleSet {

    public static int maximumElementsSubSet(Set<Integer> elements, Integer k) {
        Map<Integer, Integer> remainders = computeRemainders(elements, k);
        Integer count = 0;
        for (Map.Entry<Integer, Integer> remaining : remainders.entrySet()) {
            Integer remainder = remaining.getKey();
            Integer diff = k - remainder;
            Integer diffCount = remainders.get(diff);
            if (diffCount == null || remaining.getValue() >= diffCount) {
                count += remaining.getValue();
            }
        }
        return count;
    }

    private static Map<Integer, Integer> computeRemainders(Set<Integer> elements, Integer k) {
        Map<Integer, Integer> remainders = new HashMap<>();
        for (Integer element : elements) {
            int remainder = element % k;
            if (remainder != 0 && remainder << 1 != k) {
                remainders.computeIfPresent(remainder, (el, num) -> num + 1);
            }
            remainders.putIfAbsent(remainder, 1);
        }
        return remainders;
    }
}
