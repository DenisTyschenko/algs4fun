package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.tyshchenko.algs4fun.hackerrank.BearAndSteadyGene.minSubstringLengthToReplaceIn;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 5/13/17.
 */
@RunWith(JUnitParamsRunner.class)
public class BearAndSteadyGeneTest {

    public static Object[] data() {
        return new Object[][] {
                {"AABBCCDD", 0},
                {"AABBCCDA", 1},
                {"DAAACAAA", 5},
        };
    }

    public static Object[] originalData() {
        return new Object[][] {
                {"AACCTTGG", 0},
                {"AACCTTGA", 1},
                {"GAAATAAA", 5},
                {"TGATGCCGTCCCCTCAACTTGAGTGCTCCTAATGCGTTGC", 5}
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyMinSubstringSearch(String input, int expectedLength) {
        Assert.assertThat(minSubstringLengthToReplaceIn(input.toCharArray()), is(expectedLength));
    }

    @Test
    @Parameters(method = "originalData")
    public void verifyMinSubstringSearchOnOriginalData(String input, int expectedLength) {
        Assert.assertThat(minSubstringLengthToReplaceIn(input), is(expectedLength));
    }

}