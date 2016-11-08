package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-balanced-brackets">
 *     Stacks: Balanced Brackets</a>
 *
 *
 * @author denis on 11/8/16.
 */
public class BalancedBrackets {

    public static boolean isBalanced(String expression) {
        if (expression.isEmpty()) {
            return true;
        }

        char[] brackets = expression.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        stack.push(brackets[0]);
        for (int i = 1; i < brackets.length; i++) {

            if (")]}".indexOf(brackets[i]) == -1) {
                //not a closing bracket
                stack.push(brackets[i]);
            } else {
                //closing bracket
                Character openBracket = stack.peek();
                if (openBracket == null || !isBalanced(openBracket, brackets[i])) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static boolean isBalanced(Character a, Character b) {
        return ('(' == a && ')' == b) ||
                ('[' == a && ']' == b) ||
                ('{' == a && '}' == b);
    }
}
