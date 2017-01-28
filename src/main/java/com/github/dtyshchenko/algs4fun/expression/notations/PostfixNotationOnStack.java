package com.github.dtyshchenko.algs4fun.expression.notations;

import com.google.common.collect.ImmutableMap;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * <a href="http://www.ritambhara.in/code-to-evaluate-a-postfix-expression/">
 * Code to evaluate a postfix expression</a>
 * <p>
 * Given an expression in postfix notation, write code to evaluate a mathematical expression given in postfix notation. For Example:
 * Input: 4 2 3 + * 6 -
 * Output: 14
 * <p>
 * This implementation uses stack to evaluate expression.
 * <p>
 * Created by denis on 1/28/17.
 */
public class PostfixNotationOnStack {

    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS =
            ImmutableMap.of("+", (x, y) -> x + y,
                    "-", (x, y) -> x - y,
                    "*", (x, y) -> x * y,
                    "/", (x, y) -> x / y);

    public static int evaluate(String expression) {
        if(expression == null) {
            throw new IllegalArgumentException("Input should be a valid postfix expression");
        }
        String[] tokens = expression.trim().split("[ \t]+");
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            if (OPERATIONS.containsKey(token)) {
                // perform operations and put result on a stack
                if (stack.size() < 2) {
                    throw new IllegalStateException("Incorrect postfix expression: " + expression);
                }
                Integer right = stack.pop();
                Integer left = stack.pop();
                Integer result = OPERATIONS.get(token).apply(left, right);
                stack.push(result);
            } else {
                // put token on a stack, only numbers can be put on stack
                try {
                    stack.push(Integer.valueOf(token));
                } catch (NumberFormatException e) {
                    throw new IllegalStateException("Unexpected token: " + token +
                            " detected in expression: " + expression);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalStateException("Incorrect expression: " + expression + " can't be evaluated to single result : " + stack);
        }

        return stack.pop();
    }
}
