package com.github.dtyshchenko.algs4fun.basics.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author denis on 11/9/16.
 */
@RunWith(JUnitParamsRunner.class)
public class BreadthFirstTreeTraversalTest {

    public static Object[] data() {
        return new Object[][]{
                {},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyLevelNodes() {

    }
}
