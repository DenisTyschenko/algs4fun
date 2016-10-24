package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * @author denis on 10/24/16.
 */
@RunWith(JUnitParamsRunner.class)
public class PalindromPartitioningTest {

    public Collection<Object[]> data() {
        return asList(new Object[][] {
                //notice that palindrome detection starts from the end of the string
                {"ababbbabbababa", asList("a", "bab", "bbabb", "ababa")},
                {"aba", singletonList("aba")},
                {"abcd", asList("a", "b", "c", "d")},
                {"abb", asList("a", "bb")},
                {"", emptyList()},
        });
    }

    @Test
    @Parameters(method = "data")
    public void verifyPartitioningWithMinimumCuts (String word, List<String> exptected) {
        List<String> actual = PalindromePartitioningWithMinimumCuts.palindromePartition(word);
        Assert.assertEquals(exptected, actual);
    }
    @Test
    @Parameters(method = "data")
    public void verifyPartitioningWithMinimumCutsRecursive (String word, List<String> exptected) {
        List<String> actual = PalindromePartitioningWithMinimumCuts.palindromePartitionRecursive(word);
        Assert.assertEquals(exptected, actual);
    }
}
