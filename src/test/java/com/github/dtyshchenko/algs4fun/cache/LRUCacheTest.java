package com.github.dtyshchenko.algs4fun.cache;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by denis on 1/23/17.
 */
@RunWith(JUnitParamsRunner.class)
public class LRUCacheTest {

    public Object[][] data() {
        return new Object[][]{
                {singletonList("A"), "C"},
                {asList("A", "B"), "C"},
                {asList("A", "B", "C"), "D"},
                {asList("A", "B", "C", "D"), "E"},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyEvictionWithoutElementAccess(List<String> elsToInsert, String last) {
        LRUCache<String, String> lru = initCache(elsToInsert.size(), elsToInsert);
        lru.put(last, last);

        verifyElementsAfterEviction(elsToInsert, last, lru);
    }

    @Test
    @Parameters(method = "data")
    public void verifyEvictionWithAscendingElementAccess(List<String> elsToInsert, String last) {
        LRUCache<String, String> lru = initCache(elsToInsert.size(), elsToInsert);

        elsToInsert.forEach(e -> Assert.assertThat(lru.get(e), is(e)));
        //trigger eviction
        lru.put(last, last);

        verifyElementsAfterEviction(elsToInsert, last, lru);
    }

    @Test
    @Parameters(method = "data")
    public void verifyEvictionWithDescendingElementAccess(List<String> elsToInsert, String last) {
        LRUCache<String, String> lru = initCache(elsToInsert.size(), elsToInsert);

        Collections.reverse(elsToInsert);

        elsToInsert.forEach(e -> Assert.assertThat(lru.get(e), is(e)));
        lru.put(last, last);

        verifyElementsAfterEviction(elsToInsert, last, lru);
    }

    @Test
    public void verifySurvivalOfMostAccessedElement() {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("A", "aaa");
        cache.put("B", "bbb");
        cache.put("C", "ccc");

        cache.get("B");

        cache.put("D", "ddd");
        cache.put("E", "eee");

        Assert.assertThat(cache.get("B"), is("bbb"));
        Assert.assertThat(cache.get("D"), is("ddd"));
        Assert.assertThat(cache.get("E"), is("eee"));
    }

    @Test
    public void verifySurvivalOfMostAccessedElement_NegativeCheck() {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("A", "aaa");
        cache.put("B", "bbb");
        cache.put("C", "ccc");

        cache.get("B");

        cache.put("D", "ddd");
        cache.put("E", "eee");
        cache.put("F", "fff");

        Assert.assertThat(cache.get("B"), nullValue());
        Assert.assertThat(cache.get("D"), is("ddd"));
        Assert.assertThat(cache.get("E"), is("eee"));
        Assert.assertThat(cache.get("F"), is("fff"));
    }

    private LRUCache<String, String> initCache(int capacity, List<String> elsToInsert) {
        LRUCache<String, String> lru = new LRUCache<>(capacity);
        elsToInsert.forEach(e -> lru.put(e, e));
        return lru;
    }

    private void verifyElementsAfterEviction(List<String> elsToInsert, String last, LRUCache<String, String> lru) {
        String evicted = elsToInsert.get(0);
        Assert.assertFalse("Element not evicted: " + evicted, lru.contains(evicted));
        IntStream.range(1, elsToInsert.size())
                .forEach(i -> Assert.assertTrue(lru.contains(elsToInsert.get(i))));
        Assert.assertTrue(lru.contains(last));
    }
}