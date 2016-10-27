package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 10/27/16.
 */
@RunWith(JUnitParamsRunner.class)
public class WordBreakProblemTest {

    public static Object[][] data() {
        ImmutableSet<String> set1 = of("aba", "ada", "qwe");
        ImmutableSet<String> set2 = of("aba", "ada", "qwe","q");
        ImmutableSet<String> set3 = of("aba", "ada", "qwe","q", "qw");
        return new Object[][] {
                {set1, "abaa", false},
                {set1, "abaqada", false},
                {set1, "qwabaqada", false},
                {set1, "qweabaada", true},

                {set2, "abaqada", true},
                {set3, "qwabaqada", true},
        };
    }

    @Test
    @Parameters(method= "data")
    public void verifyWordBreak(Set<String> dictionary, String word, boolean expected) {
        WordBreakProblem wb = new WordBreakProblem(dictionary);
        Assert.assertThat(wb.contains(word), is(expected));
    }
}
