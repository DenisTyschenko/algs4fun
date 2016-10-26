package com.github.dtyshchenko.algs4fun.basics.sorting;

import java.util.Comparator;

/**
 * @author denis on 10/25/16.
 */
public class InsertionSort {

    public static int[] sort(int[] els) {
        for (int i = 0; i < els.length; i++) {
            for (int j = i; j > 0 && els[j] < els[j - 1]; j--) {
                int swap = els[j];
                els[j] = els[j - 1];
                els[j - 1] = swap;
            }
        }
        return els;
    }

    public static <T extends Comparable<T>> T[] sort(T[] els) {
        return sort(els, Comparable::compareTo);
    }

    public static <T> T[] sort(T[] els, Comparator<T> comparator) {
        for (int i = 0; i < els.length; i++) {
            for (int j = i; j > 0 && comparator.compare(els[j], els[j - 1]) < 0; j--) {
                T swap = els[j];
                els[j] = els[j - 1];
                els[j - 1] = swap;
            }
        }
        return els;
    }

}
