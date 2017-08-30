package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;

public class ReverseShuffleMerge {

    public static String findLexiMax(String input) {
        //get slice length
        char[] chars = input.toCharArray();
        if (chars.length % 2 != 0) {
            throw new IllegalArgumentException("Incorrect input size");
        }
        int sliceLength = chars.length / 2;

        //get pattern
        //get unique slices that corresponds to the pattern
        //choose lexi max thus reverse is lexi min i.e A is min in merge(reverse(A), shuffle(A))
        return slices(chars, 0, new StringBuilder(), sliceLength, getFrequencyPattern(chars))
                .stream()
                .max(Comparator.naturalOrder())
                .map(StringBuilder::new)
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString)
                .orElseThrow(() -> new IllegalStateException("Unable to find max element"));
    }

    private static Set<String> slices(char[] input, int ic, StringBuilder slice, int slc, int[] pattern) {
        if (slc == 0) {
            String sliceStr = slice.toString();
            int[] sliceFrq = calcFrequencies(sliceStr.toCharArray());
            return Arrays.equals(sliceFrq, pattern) ? singleton(sliceStr) : emptySet();
        }

        return IntStream.range(ic, input.length)
                .mapToObj(i ->
                        slices(input, i + 1,
                                new StringBuilder(slice).append(input[i]),
                                slc - 1,
                                pattern))
                .flatMap(Collection::stream)
                .collect(toSet());
    }
    

    private static int[] getFrequencyPattern(char[] chars) {
        int[] frq = calcFrequencies(chars);
        //every freq in original input divide by 2 to get
        for (int i = 0; i < frq.length; i++) {
            frq[i] /= 2;
        }
        return frq;
    }

    private static int[] calcFrequencies(char[] chars) {
        int[] frq = new int[256];
        for (int i = 0; i < chars.length; i++) {
            frq[chars[i]]++;
        }
        return frq;
    }
}
