package com.github.dtyshchenko.algs4fun.cache;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 1/30/17.
 */
@RunWith(JUnitParamsRunner.class)
public class LFUCacheTest {

    public static Object[][] provideDataForPut() {
        return new Object[][] {
                {1, singletonList("a"), singletonList("a"), emptyList()},
                {1, asList("a", "b", "c"), singletonList("c"), asList("a", "b")},
                {2, asList("a", "b", "c"), asList("b", "c"), singletonList("a")},
                {3, asList("a", "b", "c"), asList("a", "b", "c"), emptyList()}
        };
    }

    @Test
    @Parameters(method = "provideDataForPut")
    public void verifyEvictionOnContinuingPut(int capacity, List<String> pendingAdd,
                                              List<String> expectedRemaining, List<String> expectedEvicted) {
        LFUCache<String, String> cache = new LFUCache<>(capacity);

        pendingAdd.forEach(element -> cache.put(element, element));

        expectedRemaining.forEach(expected -> Assert.assertTrue(cache.contains(expected)));
        expectedRemaining.forEach(expected -> Assert.assertThat(cache.get(expected), is(expected)));

        expectedEvicted.forEach(expected -> Assert.assertFalse("Cache should not contain: " + expected, cache.contains(expected)));
        expectedEvicted.forEach(expected -> Assert.assertNull(cache.get(expected)));
    }

    @Test
    public void verifyLFUEvictionOnCacheOfThreeElements_NotAccessed() {
        LFUCache<String, String> cache = new LFUCache<>(3);

        cache.put("A", "a");
        cache.put("B", "b");
        cache.put("C", "c");
        //A should be evicted as least recently used
        cache.put("D", "d");

        Assert.assertFalse(cache.contains("A"));
        Assert.assertThat(cache.get("B"), is("b"));
        Assert.assertThat(cache.get("C"), is("c"));
        Assert.assertThat(cache.get("D"), is("d"));
    }

    @Test
    public void verifyLFUEvictionOnCacheOfThreeElements_Accessed() {
        LFUCache<String, String> cache = new LFUCache<>(3);

        cache.put("A", "a");
        cache.put("B", "b");
        cache.put("C", "c");

        cache.get("A");
        cache.get("C");

        //B should be evicted
        cache.put("D", "d");

        Assert.assertFalse(cache.contains("B"));
        Assert.assertThat(cache.get("A"), is("a"));
        Assert.assertThat(cache.get("C"), is("c"));
        Assert.assertThat(cache.get("D"), is("d"));
    }

    @Test
    public void verifyLFUEvictionOnCacheOfThreeElements_AllAccessedInReversedOrder() {
        LFUCache<String, String> cache = new LFUCache<>(3);

        cache.put("A", "a");
        cache.put("B", "b");
        cache.put("C", "c");

        cache.get("C");
        cache.get("B");
        cache.get("A");

        // "C" should be evicted as least recently used
        // amongst elements with equal frequencies
        cache.put("D", "d");

        Assert.assertFalse(cache.contains("C"));
        Assert.assertThat(cache.get("B"), is("b"));
        Assert.assertThat(cache.get("A"), is("a"));
        Assert.assertThat(cache.get("D"), is("d"));
    }

    @Test
    public void verifyLFUEvictionWithMultipleEqualFrequencies() {
        LFUCache<String, String> cache = new LFUCache<>(4);

        cache.put("A", "a");
        cache.put("B", "b");
        cache.put("C", "c");
        cache.put("D", "d");

        cache.get("D");
        cache.get("C");

        cache.get("B");
        cache.get("A");

        cache.get("D");
        cache.get("C");

        cache.put("E", "e"); // B should be evicted
        cache.put("F", "f"); // A should be evicted

        Assert.assertFalse(cache.contains("B"));
        Assert.assertFalse(cache.contains("A"));
        Assert.assertThat(cache.get("D"), is("d"));
        Assert.assertThat(cache.get("C"), is("c"));
        Assert.assertThat(cache.get("E"), is("e"));
        Assert.assertThat(cache.get("F"), is("f"));
    }
}