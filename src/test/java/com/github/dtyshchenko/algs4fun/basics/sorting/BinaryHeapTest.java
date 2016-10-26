package com.github.dtyshchenko.algs4fun.basics.sorting;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 10/26/16.
 */
@RunWith(JUnitParamsRunner.class)
public class BinaryHeapTest {

    public static Object[][] dataForInsertRemove() {
        return new Object[][] {
                {1, new int[]{1}, new int[]{1}},
                {2, new int[]{1,2}, new int[]{2,1}},
                {3, new int[]{1,3,2}, new int[]{3,2,1}},
                {5, new int[]{1,3,0,1,2}, new int[]{3,2,1,1,0}},
        };
    }

    @Test
    @Parameters(method = "dataForInsertRemove")
    public void verifyBinaryHeapInsertionRemoval(int c, int[] itemsToBeAdded, int[] expectedRemovedItems) {
        BinaryHeap bh = new BinaryHeap(c);
        for (int item: itemsToBeAdded) {
            bh.offer(item);
        }

        Assert.assertThat(bh.size(), is(itemsToBeAdded.length));

        verifyRemovedItems(expectedRemovedItems, bh);
    }

    @Test
    @Parameters(method = "dataForInsertRemove")
    public void verifyBinaryHeapConstructionFromArray(int c, int[] items, int[] expectedRemovedItems) {
        verifyRemovedItems(expectedRemovedItems, new BinaryHeap(items));
    }
    @Test
    @Parameters(source = InsertionSortTest.PrimitiveDataForSortProvider.class)
    public void verifyHeapSort(int[] input, int[] expectedSorted) {
        Assert.assertArrayEquals(expectedSorted, BinaryHeap.heapsort(input));
    }

    private void verifyRemovedItems(int[] expectedRemovedItems, BinaryHeap bh) {
        List<Integer> actualRemovedItems = new ArrayList<>();
        while(!bh.isEmpty()) {
            actualRemovedItems.add(bh.poll());
        }
        int[] actualRemovedItemsArray = actualRemovedItems.stream().mapToInt(t->t).toArray();

        Assert.assertArrayEquals(expectedRemovedItems, actualRemovedItemsArray);
    }


}
