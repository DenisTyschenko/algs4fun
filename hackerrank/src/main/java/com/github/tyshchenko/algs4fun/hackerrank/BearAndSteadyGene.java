package com.github.tyshchenko.algs4fun.hackerrank;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <a href="https://www.hackerrank.com/challenges/bear-and-steady-gene">
 * Bear And Steady Gene</a>
 * <p>
 * Complexity is linear however input sequence will be traversed several (constant) times
 * <p>
 * Created by denis on 5/13/17.
 */
public class BearAndSteadyGene {
    private final static int NUCLEOTIDES = 4;

    /**
     * API method to be as close as possible to hackerrank input format
     */
    public static int minSubstringLengthToReplaceIn(String input) {
        Scanner sc = new Scanner(input);
        int length = sc.nextInt();
        //only symbols above the limit are making gene non steady
        int limit = length / NUCLEOTIDES;
        Map<Character, Integer> charToFreq = new HashMap<>();

        char[] genom = sc.next().toCharArray();
        for (char symbol : genom) {
            charToFreq.computeIfPresent(symbol, (k, freq) -> freq + 1);
            charToFreq.putIfAbsent(symbol, 1);
        }

        if (isSteady(charToFreq, limit)) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        for (int lo = 0, hi = lo; hi < length; hi++) {
            charToFreq.compute(genom[hi], (k, freq) -> freq - 1);
            while (isSteady(charToFreq, limit) && lo <= hi) {
                result = Math.min(hi - lo + 1, result);
                charToFreq.compute(genom[lo++], (k, freq) -> freq + 1);
            }
        }
        return result;
    }

    private static boolean isSteady(Map<Character, Integer> charToFreq, int limit) {
        return charToFreq.values().stream()
                .allMatch(freq -> freq <= limit);
    }
}

