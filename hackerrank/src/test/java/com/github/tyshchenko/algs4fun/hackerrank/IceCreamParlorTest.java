package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 2/22/17.
 */
@RunWith(JUnitParamsRunner.class)
public class IceCreamParlorTest {

    public static Object[][] testData() {
        return new Object[][]{
                {
                        "2\n" +
                                "4\n" +
                                "5\n" +
                                "1 4 5 3 2\n" +
                                "4\n" +
                                "4\n" +
                                "2 2 4 3",

                        "1 4\n" +
                                "1 2\n"
                },
                {
                        "2\n" +
                                "3\n" +
                                "5\n" +
                                "1 4 5 3 2\n" +
                                "5\n" +
                                "4\n" +
                                "2 2 4 3",

                        "1 5\n" +
                                "1 4\n"
                },
                {
                    "3\n" +
                            "100\n" +
                            "3\n" +
                            "5 75 25\n" +
                            "200\n" +
                            "7\n" +
                            "150 24 79 50 88 345 3\n" +
                            "8\n" +
                            "8\n" +
                            "2 1 9 4 4 56 90 3",

                        "2 3\n" +
                                "1 4\n" +
                                "4 5\n"
                }
        };
    }

    @Test
    @Parameters(method = "testData")
    public void verifyOptimalPurchaseResolution(String input, String expected) {
        Assert.assertThat(IceCreamParlor.optimalPurchaseIds(input), is(expected));
    }
}