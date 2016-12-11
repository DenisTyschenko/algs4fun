package com.github.dtyshchenko.algs4fun.expression.notations;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 12/11/16.
 */
@RunWith(JUnitParamsRunner.class)
public class PostfixNotationTest {

    public static Object[] data() {
        return new Object[][]{
                {"1", 1},
                {"1 2 +", 3},
                {"3 1 2 + +", 6},
                {"2 3 + 1 +", 6},
                {"2 3 * 1 +", 7},
                {"3 1 - 3 * 1 +", 7},
                {"4 2 -", 2},
                {"4 2 /", 2},
                {"2 2 3 + * 5 / 1 -", 1},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyExpressionEvaluation(String expression, int expected) {
        Assert.assertThat(PostfixNotation.evaluate(expression), is(expected));
    }

    public static Object[] failureData() {
        return new Object[][]{
                {""},
                {null},
                {"+"},
                {"1 +"},
                {"3 2 1 +"},
                {"3 + 1 +"},
        };
    }

    @Test(expected = Exception.class)
    @Parameters(method = "failureData")
    public void verifyExpressionEvaluationOnIncorrectDate(String expression) {
        PostfixNotation.evaluate(expression);
    }

    public static Object[][] parseData() {
        return new Object[][]{
                {"2", singletonList("2")},
                {"2 3 +", asList("2", "3", "+")},
                {"5 32 3 + *", asList("5", "32", "3", "+", "*")},
                {"5 32 3 + * ", asList("5", "32", "3", "+", "*")},
                {" 5 32 3 + * ", asList("5", "32", "3", "+", "*")},
                {" 5  32 3  + * ", asList("5", "32", "3", "+", "*")},
                {"1 2 +", asList("1", "2", "+")},
        };
    }


    @Test
    @Parameters(method = "parseData")
    public void verifyParse(String expression, List<String> expected) {
        Collections.reverse(expected);
        Assert.assertThat(PostfixNotation.parse(expression), is(expected));
    }
}
