package com.github.tyshchenko.algs4fun.hackerrank;

import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;


/**
 * Created by dtyshenko on 4/18/2017.
 */
@RunWith(JUnitParamsRunner.class)
public class NonDivisibleSetTest {

    public static Object[][] data() {
        return new Object[][] {
                {Collections.emptySet(), 3, 0},
                {ImmutableSet.of(1), 2, 1},
                {ImmutableSet.of(1,7,2,4), 3, 3},
                {ImmutableSet.of(2,6,3,4,1), 3, 3},
        };
    }

    @Test
    @Parameters(method= "data")
    public void verifyMaximumElementsSubSetRecognition(Set<Integer> elements, int k, int expected) {
        Assert.assertThat(NonDivisibleSet.maximumElementsSubSet(elements, k), is(expected));
    }

}