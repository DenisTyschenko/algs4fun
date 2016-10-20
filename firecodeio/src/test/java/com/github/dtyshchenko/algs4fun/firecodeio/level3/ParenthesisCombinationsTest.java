package com.github.dtyshchenko.algs4fun.firecodeio.level3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static com.github.dtyshchenko.algs4fun.firecodeio.level3.ParenthesisCombinations.combParenthesis;
import static com.github.dtyshchenko.algs4fun.firecodeio.level3.ParenthesisCombinations.combParenthesisIterative;
import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Created by denis on 10/16/16.
 */
@RunWith(Parameterized.class)
public class ParenthesisCombinationsTest {
    @Parameters(name = "Test {index} - pairs: {0} expected: {1}")
    public static Collection<Object[]> data() {
        return asList(new Object[][] {
                {-1, emptySet()},
                {0, emptySet()},
                {1, singleton("()")},
                {2, new HashSet<>(asList("()()", "(())"))},
                {3, new HashSet<>(asList("()()()", "(()())", "()(())", "(())()", "((()))"))},
                {4, new HashSet<>(asList("()()()()", "(()())()", "(()(()))", "()()(())", "(())()()", "(((())))", "(())(())",
                        "()((()))", "()(())()", "()(()())", "(()()())", "((()()))", "((()))()", "((())())"))}
        });
    }

    @Parameter
    public int pairs;
    @Parameter(1)
    public Set<String> expectedResult;

    @Test
    public void verifyParenthesisCombination() {
        Assert.assertThat("Actual result set does not match expected",
                combParenthesis(pairs), is(expectedResult));
    }

    @Test
    public void verifyIterativeParenthesisCombination() {
        Assert.assertThat("Actual result set does not match expected",
                combParenthesisIterative(pairs), is(expectedResult));
    }
}
