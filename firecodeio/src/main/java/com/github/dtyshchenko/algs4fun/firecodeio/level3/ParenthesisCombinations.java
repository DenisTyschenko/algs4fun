package com.github.dtyshchenko.algs4fun.firecodeio.level3;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * <h3>Original task</h3>
 * Write a method to return all valid combinations of n-pairs of parentheses.
 * The method should return an  ArrayList of strings, in which each string represents a valid combination of parentheses.
 * The order of the strings in the ArrayList does not matter.
 * Examples:
 * combParenthesis(2) ==> {"(())","()()"}
 * Note: Valid combination means that parentheses pairs are not left open. ")()(" is not a valid combination.
 *
 * <h3>Added corrections</h3>
 * Instead of returning an ArrayList just return Set, as we don't want to have duplications in result
 * and we don't care of returned strings order
 *
 * Created by denis on 10/16/16.
 */
public class ParenthesisCombinations {

    public static Set<String> combParenthesis(int pairs) {
        if (pairs <= 0) {
            return Collections.emptySet();
        } else if (pairs == 1) {
            return Collections.singleton("()");
        } else {
            return combParenthesis(pairs - 1)
                    .stream()
                    .flatMap(solution -> applyNewPareToSolution(solution).stream())
                    .collect(toSet());
        }
    }

    public static Set<String> combParenthesisIterative(int pairs) {
        if (pairs <= 0) {
            return Collections.emptySet();
        }

        Set<String> solutions = new HashSet<>();
        solutions.add("()");

        for (int i=1; i < pairs; i++) {
            Set<String> stepSolutions = new HashSet<>();
            for (String solution: solutions)  {
                stepSolutions.addAll(applyNewPareToSolution(solution));
            }
            solutions = stepSolutions;
        }
        return solutions;
    }

    private static Set<String> applyNewPareToSolution(String solution) {
        Set<String> solutions = new HashSet<>();
        // +2 for new "(" and ")"
        int nextSolutionStrLength = solution.length() + 2;
        for (int j = 0; j < nextSolutionStrLength; j++) {
            for (int k = j+1; k < nextSolutionStrLength; k++) {
                solutions.add(new StringBuilder(solution)
                        .insert(j, "(")
                        .insert(k, ")")
                        .toString());
            }
        }
        return solutions;
    }

    public static void main(String[] args) {
        System.out.println(combParenthesis(-1));
        System.out.println(combParenthesis(0));
        System.out.println(combParenthesis(1));
        System.out.println(combParenthesis(2));
        System.out.println(combParenthesis(3));
        System.out.println(combParenthesis(4));
        System.out.println(combParenthesis(5).size());
        System.out.println(combParenthesis(6).size());
    }
}
