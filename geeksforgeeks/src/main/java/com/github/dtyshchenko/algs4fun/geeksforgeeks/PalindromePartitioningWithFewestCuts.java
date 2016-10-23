package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <a href="http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-partitioning/">
 * Palindrome Partitioning</a>
 * <p>
 * Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome.
 * For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
 * Determine the fewest cuts needed for palindrome partitioning of a given string.
 * For example, minimum 3 cuts are needed for “ababbbabbababa”.
 * The three cuts are “a|babbbab|b|ababa”. If a string is palindrome, then minimum 0 cuts are needed.
 * If a string of length n containing all different characters, then minimum n-1 cuts are needed.
 * </p>
 *
 * @author denis on 10/23/16.
 */
public class PalindromePartitioningWithFewestCuts {

    public static List<String> palindromePartition(String word) {
        if (!word.isEmpty() && isPalindrome(word)) {
            return Collections.singletonList(word);
        }
        // length for largest palindrome word in partition
        int window = word.length() - 1;
        List<String> solution = Collections.emptyList();
        // finish either when solution is found or window size is 1 (single char is a palindrome already)
        while (solution.isEmpty() && window > 1) {
            int[] counters = new int[word.length() - window];
            for (int i = 0, p = counters.length; i < counters.length; i++, p--) {
                counters[i] = p;
            }
            solution = iterateWithNestedLoops(counters, word.length(), word);
            window--;
        }
        return solution.isEmpty()
                ? word.chars().mapToObj(ch -> String.valueOf((char) ch)).collect(toList())
                : solution;
    }

    /**
     * Emulates nested loops. For example three nested loop below
     * <pre>
     * {@code
     * for (i -> 0 to N)
     *  for (j -> i+1 to N)
     *   for (k -> j+1 to N)
     *  }
     * </pre>
     * Can be represented as an array of counters: {@code {2,1,0} with N as a boundary}
     * <ul>
     *  <li>0 element corresponds to the most inner loop k</li>
     *  <li>1 element corresponds to the middle inner loop j</li>
     *  <li>2 element corresponds to the outer loop i</li>
     * </ul>
     *
     */
    public static List<String> iterateWithNestedLoops(int[] counters, int boundary, String word) {
        while (counters[counters.length - 1] <= boundary - counters.length) {
            for (; counters[0] < boundary; counters[0]++) {
                //**********************ITERATION ACTION**************************************
                // iteration action depends on first (the quickest) counter
                LinkedList<String> solution = new LinkedList<>();
                String suffix = word.substring(counters[0]);
                solution.push(suffix);
                int prefixEndIndex = counters[0];
                //iteration action depends on all the counters
                for (int ci = 1; ci < counters.length; ci++) {
                    String middle = word.substring(counters[ci], counters[ci - 1]);
                    solution.push(middle);
                    prefixEndIndex = counters[ci];
                }
                String prefix = word.substring(0, prefixEndIndex);
                solution.push(prefix);
                if (solution.stream().allMatch(PalindromePartitioningWithFewestCuts::isPalindrome)) {
                    return solution;
                }
                //**********************ITERATION ACTION**************************************
            }
            int j;
            for (j = 0; j < counters.length - 1 && counters[j] < boundary; j++) ;

            for (; j + 1 < counters.length && j >= 0; j--) {
                counters[j + 1]++;
                counters[j] = counters[j + 1] + 1;
            }
        }
        return Collections.emptyList();
    }

    private static boolean isPalindrome(String word) {
        return word.equals(new StringBuilder(word).reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println("Solution: " + iterateWithNestedLoops(new int[]{1}, "abaaa".length(), "abaaa"));
        System.out.println("Solution: " + iterateWithNestedLoops(new int[]{2, 1}, "abc".length(), "abc"));
        System.out.println("Solution: " + iterateWithNestedLoops(new int[]{2, 1}, "abcd".length(), "abcd"));
        System.out.println("Solution: " + iterateWithNestedLoops(new int[]{2, 1}, "abbd".length(), "abbd"));
        System.out.println("Solution: " + iterateWithNestedLoops(new int[]{3, 2, 1}, "abcd".length(), "abcd"));
        System.out.println("Palindromes: " + palindromePartition("ababbbabbababa"));
        System.out.println("Palindromes: " + palindromePartition("aba"));
        System.out.println("Palindromes: " + palindromePartition("abab"));
        System.out.println("Palindromes: " + palindromePartition("abaab"));
    }

}
