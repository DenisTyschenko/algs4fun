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
 * Given an expression in postfix notation, write code to evalute a mathematical expression given in postfix notation. For Example:
 * Input: 4 2 3 + * 6 -
 * Output: 14
 *
 * @author denis on 12/11/16.
 */
public class PostfixNotation {

    private final static Map<String, BiFunction<Integer, Integer, Integer>> OPERATORS =
            ImmutableMap.<String, BiFunction<Integer, Integer, Integer>>builder()
                    .put("+", (a, b) -> a + b)
                    .put("-", (a, b) -> a - b)
                    .put("*", (a, b) -> a * b)
                    .put("/", (a, b) -> a / b)
                    .build();

    public static int evaluate(String expression) {
        checkState(expression == null || expression.isEmpty(), "Not a valid postfix expression: " + expression);
        Deque<String> tokens = parse(expression);
        int result = evaluate(tokens, expression);
        checkState(!tokens.isEmpty(), "Not a valid postfix expression: " + expression);
        return result;
    }

    public static int evaluate(Deque<String> expression, String rawExpression) {
        checkState(expression.isEmpty(), "Not a valid postfix expression: " + rawExpression);
        String token = expression.pop();
        if (isOperator(token)) {
            int right = evaluate(expression, rawExpression);
            int left = evaluate(expression, rawExpression);
            return OPERATORS.get(token).apply(left, right);
        } else {
            return Integer.parseInt(token);
        }
    }

    static Deque<String> parse(String expression) {
        Deque<String> parsed = new LinkedList<>();
        String[] tokens = expression.trim().split(" +");
        if (tokens.length == 0) {
            parsed.push(expression);
        } else {
            for (String token : tokens) {
                parsed.push(token);
            }
        }
        return parsed;
    }

    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    private static void checkState(boolean condition, String errorMessage) {
        if (condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
