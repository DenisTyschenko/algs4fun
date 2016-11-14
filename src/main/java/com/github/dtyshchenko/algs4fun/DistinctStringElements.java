package com.github.dtyshchenko.algs4fun;

import java.util.HashMap;
import java.util.Map;

/**
 * @author denis on 11/14/16.
 */
public class DistinctStringElements {

    public static int largestUniqueElementsStrLength(String word) {
        char[] chars = word.toCharArray();
        int pos = 0;
        int result = 1;
        Map<Character, Integer> charToIndex = new HashMap<>();
        charToIndex.put(chars[0], pos);
        for (int i = 1; i < chars.length; i++) {
            if (charToIndex.containsKey(chars[i])) {
                result = Math.max(i - pos, result);
                Integer prevPos = charToIndex.get(chars[i]);
                if (prevPos < pos) {continue;}
                pos = prevPos + 1;
                charToIndex.put(chars[i], i);
            } else {
                charToIndex.put(chars[i], i);
                result = Math.max(i - pos + 1, result);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(largestUniqueElementsStrLength("a"));
                System.out.println(largestUniqueElementsStrLength("ab"));
        System.out.println(largestUniqueElementsStrLength("aa"));
        System.out.println(largestUniqueElementsStrLength("abc"));
        System.out.println(largestUniqueElementsStrLength("abcabcd"));
        System.out.println(largestUniqueElementsStrLength("abcaefga"));
        System.out.println(largestUniqueElementsStrLength("beacdarbp"));
    }
}
