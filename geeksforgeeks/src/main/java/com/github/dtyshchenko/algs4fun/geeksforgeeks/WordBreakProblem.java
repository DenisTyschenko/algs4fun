package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import java.util.Set;

/**
 *
 * TODO: Use dynamic programming to optimize
 *
 * <a href="http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/">
 *     Word Break Problem</a>
 *
 * @author denis on 10/27/16.
 */
public class WordBreakProblem {

    private final Set<String> dictionary;

    public WordBreakProblem(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public boolean contains(String word) {
        return contains(word.substring(0, 1), word.substring(1));
    }

    private boolean contains(String prefix, String suffix) {
        if (suffix.isEmpty()) {
            return dictionary.contains(prefix);
        } else {
            String nextSuffix = suffix.substring(1);
            String nextPrefix = prefix + suffix.substring(0, 1);

            return dictionary.contains(prefix)
                    ? contains(suffix.substring(0, 1), nextSuffix) | contains(nextPrefix, nextSuffix)
                    : contains(nextPrefix, nextSuffix);
        }
    }
}
