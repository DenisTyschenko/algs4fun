package com.github.dtyshchenko.algs4fun.cache;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode.com/problems/lfu-cache/
 *
 * http://dhruvbird.com/lfu.pdf
 * http://www.laurentluce.com/posts/least-frequently-used-cache-eviction-scheme-with-complexity-o1-in-python/
 *
 * Created by denis on 1/30/17.
 */
public class LFUCache<K, V> {
    private final int maxSize;
    private final Map<K, Node<K, V>> storage;
    private FreqNode<K, V> head;

    public LFUCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max number of cache elements should be greater than zero");
        }
        this.maxSize = maxSize;
        storage = new HashMap<>(1 << maxSize);
    }

    public V put(K key, V value) {
        if (maxSize <= storage.size()) {
            //trigger eviction
            Node<K, V> lru = head.removeLeastRecentlyUsed();
            storage.remove(lru.key);
        }
        Node<K, V> node = new Node<>(key, value);

        if (head == null) {
            head = new FreqNode<>(1);
        }
        //node with frequency 1 may be already removed
        //if all elements with frequency one were promoted
        if (head.freq != 1) {
            FreqNode<K, V> first = new FreqNode<>(1);
            head.prev = first;
            first.next = head;
            head = first;
        }

        head.addAsMostRecentlyUsed(node);

        Node<K, V> previousNode = storage.put(key, node);
        //TODO: ensure previousNode is not current node (requires adding equals to Node)
        // to avoid unnecessary downgrade of node from higher to lower frequency
        // with potential risk of removal on subsequent insertions
        if (previousNode == null) {
            return null;
        }

        previousNode.removeFromCurrentFrequency();

        return previousNode.value;
    }

    public V get(K key) {
        Node<K, V> node = storage.get(key);
        if (node  == null) {
            return null;
        }
        node.promote();
        return node.value;
    }

    public boolean contains(K key) {
        return storage.containsKey(key);
    }

    public V remove(K key) {
        return null;
    }

    private class FreqNode<K, V> {
        Node<K, V> mostRecentlyUsed;
        Node<K, V> leastRecentlyUsed;

        final int freq;

        FreqNode prev;
        FreqNode next;

        private FreqNode(int freq) {
            this.freq = freq;
        }

        void addAsMostRecentlyUsed(Node<K, V> node) {
            node.freqNode = this;
            if (leastRecentlyUsed == null) {
                mostRecentlyUsed = leastRecentlyUsed = node;
            } else {
                mostRecentlyUsed.moreRecentlyUsed = node;
                node.lessRecentlyUsed = mostRecentlyUsed;
                mostRecentlyUsed = node;
            }
        }

        Node<K, V> removeLeastRecentlyUsed() {
            Node<K, V> tmp = leastRecentlyUsed;
            removeFromLruList(leastRecentlyUsed);
            //TODO: clear frequency list if there is no elements any more
            return tmp;
        }

        void removeFromLruList(Node<K, V> node) {
            if (leastRecentlyUsed == mostRecentlyUsed) {
                leastRecentlyUsed = mostRecentlyUsed = null;
                return;
            }
            if (node != leastRecentlyUsed) {
                node.lessRecentlyUsed.moreRecentlyUsed = node.moreRecentlyUsed;
            } else {
                leastRecentlyUsed = node.moreRecentlyUsed;
                //forget link on node that is being removed
                leastRecentlyUsed.lessRecentlyUsed = null;
            }
            if (node != mostRecentlyUsed) {
                node.moreRecentlyUsed.lessRecentlyUsed = node.lessRecentlyUsed;
            } else {
                mostRecentlyUsed = node.lessRecentlyUsed;
                //forget link on node that is being removed
                mostRecentlyUsed.moreRecentlyUsed = null;
            }
        }
    }

    private class Node<K, V> {
        final K key;
        final V value;
        Node<K, V> moreRecentlyUsed;
        Node<K, V> lessRecentlyUsed;
        FreqNode<K, V> freqNode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        void removeFromCurrentFrequency() {
            freqNode.removeFromLruList(this);
        }

        void promote() {
            freqNode.removeFromLruList(this);
            int nextFreq = freqNode.freq + 1;

            if (freqNode.next != null) {
                if (freqNode.next.freq != nextFreq) {
                    FreqNode tmp = new FreqNode(nextFreq);
                    freqNode.next.prev = tmp;
                    tmp.next = freqNode.next;
                    freqNode.next = tmp;
                }
            } else {
                freqNode.next = new FreqNode(nextFreq);
                freqNode.next.prev = freqNode;
            }

            freqNode.addAsMostRecentlyUsed(this);
        }
    }
}
