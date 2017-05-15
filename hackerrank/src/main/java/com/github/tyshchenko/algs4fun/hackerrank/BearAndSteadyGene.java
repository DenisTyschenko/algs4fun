package com.github.tyshchenko.algs4fun.hackerrank;


import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * <a href="https://www.hackerrank.com/challenges/bear-and-steady-gene">
 * Bear And Steady Gene</a>
 *
 * Complexity is linear however input sequence will be traversed several (constant) times
 *
 * Created by denis on 5/13/17.
 */
public class BearAndSteadyGene {
    private final static char OFFSET = 'A';
    private final static int NUCLEOTIDES = 4;
    private final static Map<Character, Character> DECODER = ImmutableMap.<Character, Character>builder()
            .put('A', 'A')
            .put('C', 'B')
            .put('T', 'C')
            .put('G', 'D')
            .build();

    /**
     * API method to be as close as possible to hackerrank input format
     */
    public static int minSubstringLengthToReplaceIn(String input) {
        Scanner sc = new Scanner(input);
        char[] decoded = new char[input.length()];
        char[] original = sc.next().toCharArray();
        for (int i = 0; i < input.length(); i++) {
            decoded[i] = DECODER.get(original[i]);
        }
        return minSubstringLengthToReplaceIn(decoded);
    }

    public static int minSubstringLengthToReplaceIn(char[] input) {
        int N = input.length;
        int limitNucleotideFreq = N / NUCLEOTIDES;
        //count nucleotide frequencies
        int[] inputFreq = new int[NUCLEOTIDES];
        Arrays.fill(inputFreq, -limitNucleotideFreq);
        // positive nucleotides are over the limit
        for (int i = 0; i < input.length; i++) {
            inputFreq[input[i] - OFFSET]++;
        }

        int[] sliceFreq = new int[NUCLEOTIDES];
        //find first violating symbol in input seq to start with
        int result = 0;
        for (int lo = getBeginning(0, input, inputFreq), hi = lo;
             lo >= 0 && hi < input.length; hi++) {

            if (inputFreq[input[hi] - OFFSET] > 0) {
                sliceFreq[input[hi] - OFFSET]++;
            }

            while (isSteady(inputFreq, sliceFreq) && lo <= hi) {
                int length = hi - lo + 1;
                result = result == 0 ? length : Math.min(result, length);

                sliceFreq[input[lo] - OFFSET]--;
                lo = getBeginning(lo + 1, input, inputFreq);
            }
        }
        return result;
    }

    private static boolean isSteady(int[] referenceFreq, int[] sliceFreq) {
        for (int i = 0; i < NUCLEOTIDES; i++) {
            if (sliceFreq[i] < referenceFreq[i]) {
                return false;
            }
        }
        return true;
    }

    private static int getBeginning(int pos, char[] input, int[] pattern) {
        for (int i = pos; i < input.length; i++) {
            if (pattern[input[i] - OFFSET] > 0) {
                return i;
            }
        }
        return -1;
    }
}

