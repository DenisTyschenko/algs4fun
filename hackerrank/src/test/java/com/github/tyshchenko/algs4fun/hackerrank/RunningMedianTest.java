package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 2/12/17.
 */
@RunWith(JUnitParamsRunner.class)
public class RunningMedianTest {

    public static Object[][] data() {
        return new Object[][] {
                //iteratively decreasing
                {new Integer[] {1}, new BigDecimal[] {new BigDecimal("1.0")}},
                {new Integer[] {1,2}, new BigDecimal[] {
                        new BigDecimal("1.0"),
                        new BigDecimal("1.5")}},
                {new Integer[] {1,2,3}, new BigDecimal[] {
                        new BigDecimal("1.0"),
                        new BigDecimal("1.5"),
                        new BigDecimal("2.0")}},
                {new Integer[] {1,2,3,4}, new BigDecimal[] {
                        new BigDecimal("1.0"),
                        new BigDecimal("1.5"),
                        new BigDecimal("2.0"),
                        new BigDecimal("2.5")}},

                {new Integer[] {2,1,3,4}, new BigDecimal[] {
                        //2
                        new BigDecimal("2.0"),
                        //1, 2
                        new BigDecimal("1.5"),
                        //1,2,3
                        new BigDecimal("2.0"),
                        //1,2,3,4
                        new BigDecimal("2.5")}},

                {new Integer[] {6,1,5,2}, new BigDecimal[] {
                        //6
                        new BigDecimal("6.0"),
                        //1, 6
                        new BigDecimal("3.5"),
                        //1,5,6
                        new BigDecimal("5.0"),
                        //1,2,5,6
                        new BigDecimal("3.5")}}
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyMedianCalculation(Integer[] items, BigDecimal[] expectedMedian) {
        Assert.assertThat(RunningMedian.find(items), is(expectedMedian));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyExceptionIsThrownOnNullInput() {
        RunningMedian.find(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyExceptionIsThrownOnEmptyInput() {
        RunningMedian.find(new Integer[]{});
    }

}