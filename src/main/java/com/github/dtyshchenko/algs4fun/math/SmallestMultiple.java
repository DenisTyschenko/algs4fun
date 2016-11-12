package com.github.dtyshchenko.algs4fun.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 *
 * @author denis on 11/11/16.
 */
public class SmallestMultiple {

    /**
     * Gives the smallest multiple that is evenly divisible by all of numbers from 1 to n (inclusive)
     */
    public static int findSmallestMultipleOf(int n) {
        Map<Integer, Integer> allFactors = new HashMap<>();
        for (int x = 2; x <= n; x++) {
            Map<Integer, Integer> factors = factorsOf(x);
            for (Integer factor : factors.keySet()) {
                if (!allFactors.containsKey(factor) || allFactors.get(factor) < factors.get(factor)) {
                    allFactors.put(factor, factors.get(factor));
                }
            }
        }
        System.out.println(allFactors);
        return allFactors.entrySet().stream()
                // input numbers are small, no need to worry about overflow in this case
                .map(entry -> (int)Math.pow(entry.getKey(), entry.getValue()))
                .reduce(1, (acc, val) -> acc * val);
    }

    public static Map<Integer, Integer> factorsOf(int x) {
        int factor = 2;
        Map<Integer, Integer> frequencies = new HashMap<>();
        while (x > 1) {
            if (x % factor == 0) {
                x /= factor;
                frequencies.computeIfPresent(factor, (k,v) -> v+1);
                frequencies.computeIfAbsent(factor, k -> 1);
            } else {
                factor++;
            }
        }
        return frequencies;
    }

}
