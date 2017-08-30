package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

@RunWith(JUnitParamsRunner.class)
public class ReverseShuffleMergeTest {

    public static Object[] testData() {
        return new Object[][] {
                {"eggegg", "egg"}
        };
    }

    @Test
    @Parameters(method = "testData")
    public void test(String input, String expected) {
        Assert.assertThat(ReverseShuffleMerge.findLexiMax(input), is(expected));
    }
}