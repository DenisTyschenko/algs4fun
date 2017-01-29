package com.github.dtyshchenko.algs4fun.expression.notations;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 1/29/17.
 */
@RunWith(JUnitParamsRunner.class)
public class ConvertUtilTest {

    public static Object[][] data() {
        return new Object[][] {
                //without parenthesis
                {"1", "1"},
                {"1 + 2", "1 2 +"},
                {"1 + 2 + 3", "1 2 + 3 +"},
                {"1 + 2 * 3", "1 2 3 * +"},
                {"1 * 2 + 3", "1 2 * 3 +"},
                {"1 * 2 * 3", "1 2 * 3 *"},
                {"1 + 2 + 3 * 4", "1 2 + 3 4 * +"},
                {"1 + 2 * 3 * 4 + 5", "1 2 3 * 4 * + 5 +"},
                {"1 + 2 * 3 / 4 + 5", "1 2 3 * 4 / + 5 +"},
                //with parenthesis
                {"( 1 + 2 ) + 3", "1 2 + 3 +"},
                {"( 1 + 2 ) * 3", "1 2 + 3 *"},
                {"( 1 * 2 ) + 3", "1 2 * 3 +"},

                {"4 + ( 1 * 2 ) + 3", "4 1 2 * + 3 +"},
                {"4 * ( 1 * 2 ) + 3", "4 1 2 * * 3 +"},

                {"( 4 + ( 1 + 2 ) ) + 3", "4 1 2 + + 3 +"},
                {"( 4 + ( 1 + 2 ) ) * 3", "4 1 2 + + 3 *"},
                {"( 4 * ( 1 + 2 ) ) * 3", "4 1 2 + * 3 *"},
                {"( 4 * ( 1 / 2 ) ) * 3", "4 1 2 / * 3 *"},

                {"( 1 + ( 2 + ( 3 + 4 ) ) ) + 5", "1 2 3 4 + + + 5 +"},
                {"( 1 + ( 2 + 3 + 4 ) ) + 5", "1 2 3 + 4 + + 5 +"},

                {"1 + ( ( 2 + 3 ) + 4 ) + 5", "1 2 3 + 4 + + 5 +"},

                {"( 1 + 2 ) * ( 3 + 4 ) / 5 ", "1 2 + 3 4 + * 5 /"},

                {"1 + 2  * ( 3 + 4 * 5 )", "1 2 3 4 5 * + * +"}
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyConversionToPostfixNotation(String infix, String expected) {
        Assert.assertThat(ConvertUtil.fromInfixToPostfix(infix), is(expected));
    }
}