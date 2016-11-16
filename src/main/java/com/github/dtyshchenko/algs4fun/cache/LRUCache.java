package com.github.dtyshchenko.algs4fun.cache;

/**
 *
 * @author denis on 11/15/16.
 */
public class LRUCache<K, V> {

    // just for our study 4K should be enough
    private static int MAXIMUM_CAPACITY = 1 << 12;
    private Entry head;
    private Entry tail;
    private Entry[] table;
    private float loadFactor;
    private int threshold;

    public LRUCache(int capacity, float loadFactor) {
        int m2cap = tableSizeFor(capacity);
        @SuppressWarnings({"unchecked", "rawtype"})
        Entry[] tab = (Entry[]) new Object[m2cap];
        this.table = tab;
        this.loadFactor = loadFactor;
        this.threshold = (int) loadFactor * m2cap;
    }

    public void put(K key, V value) {
        //TODO: add LRU eviction policy
        int bi = hash(key) & (table.length - 1);
        if (table[bi] == null) {
            table[bi] = newEntry(key, value);
        } else {
            addIfNewEntry(key, value, bi);
        }
    }

    public V get(K key) {
        int bi = hash(key) & (table.length - 1);
        for (Entry entry = table[bi]; entry != null; entry = entry.next) {
            if (keysMatch(entry, key)) {
                removeFromLruList(entry);
                addToTailOfLruList(entry);
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Assumption is that tail is not null
     */
    private void addToTailOfLruList(Entry entry) {

    }

    private boolean keysMatch(Entry existing, K key) {
        return existing.keyHash == key.hashCode() && (existing == key || existing.key.equals(key));
    }

    private void addIfNewEntry(K key, V value, int bi) {
        Entry first = table[bi];
        if (keysMatch(first, key)) {
            table[bi] = newEntry(key, value);
            removeFromLruList(first);
        }
        Entry lastEntry = first;
        for (Entry entry = first.next; entry != null; entry = entry.next) {
            if (keysMatch(entry, key)) {
                removeFromLruList(entry);
                lastEntry.next = entry.next;
            }
            lastEntry = entry;
        }
        lastEntry.next = newEntry(key, value);
    }

    private void removeFromLruList(Entry lruEntry) {
        if (lruEntry == head) {
            //lruEntry is a head
            if (lruEntry == tail) {
                //lruEntry is also a tail
                tail = null;
                head = null;
            } else {
                head = lruEntry.after;
                head.before = null;
            }
        } else {
            //lruEntry is not a head in lru doubly linked list
            if (lruEntry == tail) {
                //lruEntry is a tail in lru doubly linked list
                tail = lruEntry.before;
                tail.after = null;
            } else {
                lruEntry.before.after = lruEntry.after;
                lruEntry.after.before = lruEntry.before;
            }
        }
    }

    private Entry newEntry(K key, V value) {
        Entry newEntr = new Entry(key, value, tail);
        if (tail != null) {
            tail.after = newEntr;
            newEntr.before = tail;
        } else {
            //if tail is null head is null as well
            head = newEntr;
        }
        tail = newEntr;
        return newEntr;
    }

    private int hash(K key) {
        return (key.hashCode() >>> 16) ^ key.hashCode();
    }

    /**
     * from HashMap implementation, shifts give 11..111, the last return adds +1 => have 100..000, power of two
     * Returns a power of two size for the given target capacity.
     */
    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private class Entry {
        Entry next;
        Entry before;
        Entry after;
        K key;
        V value;
        int keyHash;

        public Entry(K key, V value, Entry before) {
            this(key, value, null, before, null);
        }

        public Entry(K key, V value, Entry next, Entry before, Entry after) {
            this.next = next;
            this.before = before;
            this.after = after;
            this.key = key;
            this.value = value;
            this.keyHash = key.hashCode();
        }
    }

}
