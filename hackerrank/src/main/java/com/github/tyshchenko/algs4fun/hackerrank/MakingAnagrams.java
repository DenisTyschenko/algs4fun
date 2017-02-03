package com.github.tyshchenko.algs4fun.hackerrank;

/**
 * Taken from :
 * <a href="https://www.hackerrank.com/challenges/ctci-making-anagrams">Making Anagrams</a>
 *
 * Alice is taking a cryptography class and finding anagrams to be very useful.
 * We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string. In other words,
 * both strings must contain the same exact letters in the same exact frequency.
 * For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.
 *
 * Alice decides on an encryption scheme involving two large strings where encryption
 * is dependent on the minimum number of character deletions required to make the two strings anagrams.
 * Can you help her find this number?
 * Given two strings {@code a} and {@code b} that may or may not be of the same length,
 * determine the minimum number of character deletions required to make  {@code a} and   {@code b} anagrams.
 *
 * Any characters can be deleted from either of the strings.
 *
 * Created by denis on 2/3/17.
 */
public class MakingAnagrams {
    private static final char ASCII_TO_ENGLISH_LOWER_CASE_OFFSET = 'a';

    public static int numberNeeded(String first, String second) {
        int[] firstFreq = fillFrequencyFor(first, new int[26]);
        int[] secondFreq = fillFrequencyFor(second, new int[26]);

        int requiredDeletions = 0;
        for (int i = 0; i < firstFreq.length; i++) {
            requiredDeletions += Math.abs(firstFreq[i] - secondFreq[i]);
        }

        return requiredDeletions;
    }

    private static int[] fillFrequencyFor(String src, int[] freq) {
        for (char ch : src.toCharArray()) {
            freq[ch - ASCII_TO_ENGLISH_LOWER_CASE_OFFSET]++;
        }
        return freq;
    }

}
