package com.github.dtyshchenko.algs4fun.math;

import com.google.common.collect.ImmutableMap;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/12/16.
 */
@RunWith(JUnitParamsRunner.class)
public class SmallestMultipleTest {

    public static Object[] dataForFactor() {
        return new Object[][] {
                {2, ImmutableMap.builder().put(2,1).build()},
                {4, ImmutableMap.builder().put(2,2).build()},
                {3, ImmutableMap.builder().put(3,1).build()},
                {6, ImmutableMap.builder().put(2,1).put(3,1).build()},
                {20, ImmutableMap.builder().put(2,2).put(5,1).build()},
        };
    }
    public static Object[] dataForSmallestMultiple() {
        return new Object[][] {
                {10, 2520},
                {20, 232792560}
        };
    }

    @Test
    @Parameters(method = "dataForFactor")
    public void verifyFactorization(int x, Map<Integer, Integer> expected) {
        Assert.assertThat(SmallestMultiple.factorsOf(x), is(expected));
    }


    @Test
    @Parameters(method = "dataForSmallestMultiple")
    public void verifySmallestMultiple(int x, int expected) {
        Assert.assertThat(SmallestMultiple.findSmallestMultipleOf(x), is(expected));
    }

}
