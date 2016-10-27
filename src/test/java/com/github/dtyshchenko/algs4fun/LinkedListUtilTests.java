package com.github.dtyshchenko.algs4fun;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.github.dtyshchenko.algs4fun.LinkedListUtil.Entry.of;
import static com.github.dtyshchenko.algs4fun.LinkedListUtil.inversePairs;
import static com.github.dtyshchenko.algs4fun.LinkedListUtil.reverse;

/**
 * @author denis on 10/27/16.
 */
@RunWith(JUnitParamsRunner.class)
public class LinkedListUtilTests {

    public Object[][] dataForInversions() {
        return new Object[][] {
                {of(1, of(2)),
                        new int[] {2,1}},
                {of(1, of(2, of(3, of(4)))),
                        new int[] {2,1,4,3}},
                {of(1, of(2, of(3))),
                        new int[] {2,1,3}},
                {of(1, of(2, of(3, of(4, of(5, of(6)))))),
                        new int[] {2,1,4,3,6,5}},
        };
    }

    public Object[][] dataForReversion() {
        return new Object[][] {
                {of(1, of(2)),
                        new int[] {2,1}},
                {of(1, of(2, of(3, of(4)))),
                        new int[] {4,3,2,1}},
                {of(1, of(2, of(3))),
                        new int[] {3,2,1}},
                {of(1, of(2, of(3, of(4, of(5, of(6)))))),
                        new int[] {6,5,4,3,2,1}},
        };
    }

    @Test
    @Parameters(method = "dataForInversions")
    public void verifyPairInversion(LinkedListUtil.Entry first, int[] expectedInversion) {
        verifyLinkedListElements(expectedInversion, inversePairs(first));
    }

    @Test
    @Parameters(method = "dataForReversion")
    public void verifyLinkedListReversion(LinkedListUtil.Entry first, int[] expectedInversion) {
        verifyLinkedListElements(expectedInversion, reverse(first));
    }

    private void verifyLinkedListElements(int[] expected, LinkedListUtil.Entry entry) {
        List<Integer> actualVals = new ArrayList<>();

        while (entry != null) {
            actualVals.add(entry.data);
            entry = entry.next;
        }

        int[] actualInversions = actualVals.stream().mapToInt(t -> t).toArray();
        Assert.assertArrayEquals(expected, actualInversions);
    }

}
