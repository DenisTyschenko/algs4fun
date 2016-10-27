package com.github.dtyshchenko.algs4fun;

/**
 * @author denis on 10/27/16.
 */
public class LinkedListUtil {
    /**
     * 1,2,3,4 -> 2,1,4,3
     */
    public static Entry inversePairs(Entry in) {
        Entry firstEntry = exch(in, in.next);
        Entry lastEntry = in;
        while (lastEntry.next != null && lastEntry.next.next != null) {
            Entry f = lastEntry.next;
            Entry s = lastEntry.next.next;
            exch(f, s);
            lastEntry.next = s;
            lastEntry = f;
        }
        return firstEntry;
    }


    public static Entry reverse(Entry in) {
        if (in == null) {
            return null;
        }

        Entry last = in;
        Entry i = in.next;
        //now first becomes the last element
        in.next = null;
        while (i != null) {
            Entry next = i.next;
            i.next = last;

            last = i;
            i = next;
        }
        return last;
    }

    /**
     * Very simple representation of node in singly linked list
     */
    public static class Entry {
        public Entry next;
        public int data;

        public static Entry of(int data, Entry next) {
            return new Entry(data, next);
        }

        public static Entry of(int data) {
            return of(data, null);
        }

        private Entry(int data, Entry next) {
            this.next = next;
            this.data = data;
        }

        @Override
        public String toString() {
            return data + " " + (next == null ? "nil" : next.toString());
        }
    }

    private static Entry exch(Entry first, Entry second) {
        Entry third = second.next;
        second.next = first;
        first.next = third;
        return second;
    }

}
