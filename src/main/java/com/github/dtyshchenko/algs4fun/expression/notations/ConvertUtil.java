package com.github.dtyshchenko.algs4fun.expression.notations;

import com.google.common.collect.ImmutableMap;

import java.util.*;

import static java.util.stream.Collectors.joining;

/**
 * <a href="https://en.wikipedia.org/wiki/Shunting-yard_algorithm">Algorithm description</a>
 * <a href="http://interactivepython.org/runestone/static/pythonds/BasicDS/InfixPrefixandPostfixExpressions.html">Infix to postfix conversion</a>
 *
 * Created by denis on 1/29/17.
 */
public class ConvertUtil {

    public static Map<String, Integer> OP_PRIORITY =
            ImmutableMap.of("+", 0, "-", 0, "*", 1, "/", 1);

    public static String fromInfixToPostfix(String infixExpression) {

        String[] tokens = infixExpression.trim().split("[ \t]+");

        Queue<String> result = new ArrayDeque<>(tokens.length);
        Deque<String> operators = new LinkedList<>();

        for (String token : tokens) {
            if (OP_PRIORITY.containsKey(token)) {
                // operation
                if (!operators.isEmpty()) {
                    String prevOperation = operators.peek();
                    if (OP_PRIORITY.get(prevOperation) > OP_PRIORITY.get(token)) {
                        // if incoming operation has less priority then pop entire stack to result
                        // EX: a + b * c + (here stack will is popped) d = a b c * + (stack is popped) d +
                        while (!operators.isEmpty()) {
                            result.offer(operators.pop());
                        }
                    } else if (OP_PRIORITY.get(prevOperation).equals(OP_PRIORITY.get(token))) {
                        //if priority is equal just pop previous operation and put a new one on a stack
                        result.offer(operators.pop());
                    }
                }
                operators.push(token);
            } else {
                // operand
                result.offer(token);
            }
        }

        while (!operators.isEmpty()) {
            result.offer(operators.pop());
        }

        return result.stream().collect(joining(" "));
    }
}
