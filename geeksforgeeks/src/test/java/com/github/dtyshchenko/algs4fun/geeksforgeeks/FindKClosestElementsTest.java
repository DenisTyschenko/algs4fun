package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.dtyshchenko.algs4fun.geeksforgeeks.FindKClosestElements.findClosestElements;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/2/16.
 */
@RunWith(JUnitParamsRunner.class)
public class FindKClosestElementsTest {

    public static Object[] data() {
        return new Object[][]{
                {1, 3, asList(1, 2, 3), singletonList(2)},
                {1, 2, asList(1, 2, 3), singletonList(1)},
                {1, 1, asList(1, 2, 3), singletonList(2)},
                {1, 4, asList(1, 2, 3, 4), singletonList(3)},
                {1, 3, asList(1, 2, 3, 4), singletonList(2)},
                {1, 2, asList(1, 2, 3, 4), singletonList(1)},
                {1, 1, asList(1, 2, 3, 4), singletonList(2)},
                {2, 1, asList(1, 2, 3, 4), asList(2, 3)},
                {2, 3, asList(1, 2, 3, 4), asList(2, 4)},
                {2, 2, asList(1, 4, 6, 8), asList(1, 4)},
                {3, 2, asList(1, 4, 6, 8), asList(1, 4, 6)},
                {4, 2, asList(1, 4, 6, 8), asList(1, 4, 6, 8)},
                {4, 7, asList(1, 4, 6, 8), asList(6, 8, 4, 1)},

                // if k > input.size()
                {4, 1, asList(1, 2, 3), asList(2, 3)},

                {1, 0, asList(1, 2, 3), singletonList(1)},
                {1, 4, asList(1, 2, 3), singletonList(3)},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyFindOfKClosestElements(int k, int x, List<Integer> els, List<Integer> expectedClosestElements) {
        Assert.assertThat(findClosestElements(k, x, els), is(expectedClosestElements));
    }
}
