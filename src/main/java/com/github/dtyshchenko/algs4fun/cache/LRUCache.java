package com.github.dtyshchenko.algs4fun.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of LRU Cache based on {@link HashMap} for elements storage
 * and custom doubly linked list for elements access tracking.
 *
 * @author denis on 11/15/16.
 */
public class LRUCache<K, V> {

    private final int maxSize;
    private final Map<K, Node<K, V>> storage;
    private Node<K, V> mostRecentlyUsed;
    private Node<K, V> leastRecentlyUsed;

    public LRUCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max number of elements in the cache " +
                    "should be greater than zero");
        }
        this.maxSize = maxSize;
        //by default hash map uses load factor 0,75
        //to avoid unnecessary resizing of backing storage either set load factor to 1
        //or double the number of buckets in backing storage which means we will
        //not use 25 percent of backing storage however we are keeping load factor as 0,75
        //i.e. higher chance for elements to be better distributed amongst buckets when reaching maxSize
        storage = new HashMap<>(1 << maxSize);
    }

    public V put(K k, V v) {
        if (maxSize <= storage.size()) {
            storage.remove(leastRecentlyUsed.key);
            removeFromLruList(leastRecentlyUsed);
        }
        Node<K, V> current = new Node<>(k, v);

        setAsMostRecentlyUsed(current);

        Node<K, V> unreachable = storage.put(k, current);
        if (unreachable == null) {
            return null;
        }
        //remove node from linked list if there was already such key present in map
        //backing map has overridden the key with new value, no need to store
        //previous node in linked list, it will never be accessed
        removeFromLruList(unreachable);
        return unreachable.value;
    }

    public V get(K k) {
        Node<K, V> node = storage.get(k);
        if (node == null) {
            return null;
        }
        //move node to the head of linked list as most recently use element
        removeFromLruList(node);
        setAsMostRecentlyUsed(node);
        return node.value;
    }

    public boolean contains(K k) {
        boolean isContained = storage.containsKey(k);
        if (isContained) {
            get(k);
        }
        return isContained;
    }

    private void setAsMostRecentlyUsed(Node<K, V> current) {
        if (mostRecentlyUsed != null) {
            mostRecentlyUsed.next = current;
            current.prev = mostRecentlyUsed;
        }
        if (leastRecentlyUsed == null) {
            leastRecentlyUsed = current;
        }
        mostRecentlyUsed = current;
    }

    /**
     * Removal from the LRU tracking linked list.
     * Updates LRU head and tail pointers in case corner node is being removed
     */
    private void removeFromLruList(Node<K, V> node) {
        if (leastRecentlyUsed == mostRecentlyUsed) {
            leastRecentlyUsed = mostRecentlyUsed = null;
            return;
        }
        if (node != leastRecentlyUsed) {
            node.prev.next = node.next;
        } else {
            leastRecentlyUsed = node.next;
            //forget link on node that is being removed
            leastRecentlyUsed.prev = null;
        }
        if (node != mostRecentlyUsed) {
            node.next.prev = node.prev;
        } else {
            mostRecentlyUsed = node.prev;
            //forget link on node that is being removed
            mostRecentlyUsed.next = null;
        }
    }

    private class Node<K, V> {
        private final K key;
        private final V value;
        private Node<K, V> next;
        private Node<K, V> prev;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
