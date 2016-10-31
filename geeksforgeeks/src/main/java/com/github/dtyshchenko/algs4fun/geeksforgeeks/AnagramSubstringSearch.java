package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="http://www.geeksforgeeks.org/anagram-substring-search-search-permutations/">
 * Anagram Substring Search (Or Search for all permutations)</a>
 * <p>
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints all occurrences of pat[] and its permutations (or anagrams) in txt[]. You may assume that n > m.
 * Expected time complexity is O(n)
 * </p>
 * Or similar description:
 * <p>
 * Given a string (haystack) and a pattern (needle). Find all the anagrams of the pattern that are contained in the string.
 * </p>
 * <p>
 * <p>
 * Solution is based on assumption that symbols in text and patter are both withing ASCII set.
 * Handle two arrays of size 256 symbols for each symbol in ASCII set. Array index stands for symbol ASCII value,
 * value stands for number of symbols. First array corresponds to symbol frequencies in pattern.
 * Second array corresponds to symbol frequencies in current text window.
 * If those arrays are equal pattern and analyzed part of text is anagram.
 * <p>
 * Such approach gives O(n) solution.
 * </p>
 *
 * @author denis on 10/29/16.
 */
public class AnagramSubstringSearch {

    private static final int ASCII_SIZE = 256;

    public static List<Integer> anagramIndexes(String text, String pattern) {
        int patternLength = pattern.length();
        if (patternLength > text.length()) {
            throw new IllegalArgumentException("Pattern string must be less or equal to text string");
        }
        //symbol frequencies in text window string
        int[] frqWindow = new int[ASCII_SIZE];
        //symbol frequencies in pattern string
        int[] frqPattern = new int[ASCII_SIZE];

        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();

        for (int i = 0; i < patternLength; i++) {
            //put chars into frequencies array, value stands for the number of symbol occurrence
            //0 - symbol is not present
            frqWindow[textChars[i]]++;
            frqPattern[patternChars[i]]++;
        }
        List<Integer> solutions = new ArrayList<>();
        //after window is full with pattern.length() number of elements we can check for possible anagram solution
        //if frequencies arrays are equal the tested string is an anagram of pattern
        if (Arrays.equals(frqWindow, frqPattern)) {
            solutions.add(0);
        }
        //move window further till the end of input text
        for (int i = patternLength; i < text.length(); i++) {
            //remove stale character from window symbol frequencies, set to 0 means element was not detected
            int staleSymbolIndex = i - patternLength;
            frqWindow[textChars[staleSymbolIndex]]--;
            //add new element from string input
            frqWindow[textChars[i]]++;

            if (Arrays.equals(frqWindow, frqPattern)) {
                //stale index + 1 is a start index of new window position
                solutions.add(staleSymbolIndex + 1);
            }
        }
        return solutions;
    }

}
