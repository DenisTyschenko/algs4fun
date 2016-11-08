package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/8/16.
 */
@RunWith(JUnitParamsRunner.class)
public class BalancedBracketsTest {

    public static Object[] data() {
        return new Object[][]{
                {"[]", true},
                {"[", false},
                {"[()]", true},
                {"[(])", false},
                {"([])", true},
                {"([)]", false},
                {"([)", false},
                {"[()", false},
                {"()]", false},
                {"(]", false},
                {"{{[[(())]]}}", true},
                {"{{[[({()})]]}}", true},
                {"{{[[({(}))]]}}", false},
                {"{[()]}", true},
                {"{[(])}", false},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verify(String expression, boolean expected) {
        Assert.assertThat(BalancedBrackets.isBalanced(expression), is(expected));
    }
}
