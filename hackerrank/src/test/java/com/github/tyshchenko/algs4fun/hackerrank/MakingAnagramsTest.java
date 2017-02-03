package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by denis on 2/3/17.
 */
@RunWith(JUnitParamsRunner.class)
public class MakingAnagramsTest {

    public static Object[][] data() {
        return new Object[][] {
                {"a", "b", 2},
                {"src", "dest", 5},
                {"abc", "cdk", 4},
                {"abc", "cba", 0},
                {"abab", "ab", 2},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyDeletionNumberCalculation(String first, String second, int expected) {
        Assert.assertThat(MakingAnagrams.numberNeeded(first, second), is(expected));
    }
}