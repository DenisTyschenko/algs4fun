package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 3/26/17.
 */
@RunWith(JUnitParamsRunner.class)
public class PrimalityTest {

    public static Object[] testData() {
        return new Object[][]{
                {new int[]{1},
                        new String[]{"Not prime"}},
                {new int[]{1, 2, 3},
                        new String[]{"Not prime", "Prime", "Prime"}},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8},
                        new String[]{"Not prime", "Prime", "Prime", "Not prime",
                                "Prime", "Not prime", "Prime", "Not prime"}},
                {new int[]{1_000_000_007},
                        new String[]{"Prime"}}
        };
    }

    @Test
    @Parameters(method = "testData")
    public void verifyPrimalityRecognition(int[] input, String[] expected) {
        Assert.assertThat(Primality.recognizePrimality(input), is(expected));
    }
}