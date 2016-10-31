package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.github.dtyshchenko.algs4fun.geeksforgeeks.AnagramSubstringSearch.anagramIndexes;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 10/30/16.
 */
@RunWith(JUnitParamsRunner.class)
public class AnagramSubstringSearchTest {

    public static Object[][] data() {
        return new Object[][] {
                {"aabab", "ab", asList(1, 2, 3)},
                {"cdb", "bcd", singletonList(0)},
                {"bbbababaaabbbb", "ab", asList(2,3,4,5,6,9)},
                {"abdcghbaabcdij", "bcda", asList(0, 8)}
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyAnagramSubstringSearch(String text, String pattern, List<Integer> expected) {
        Assert.assertThat(anagramIndexes(text, pattern), is(expected));
    }
}
