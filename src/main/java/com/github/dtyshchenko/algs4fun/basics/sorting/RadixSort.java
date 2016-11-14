package com.github.dtyshchenko.algs4fun.basics.sorting;

/**
 * @author denis on 11/14/16.
 */
public class RadixSort {
    private static final int MASK = 0xFF; // mask to extract byte words from integer
    private static final int SIGN_INVERT_MASK = 0x80; // mask to invert sign bit in last byte with XOR

    /**
     * Method to sort 32 bit integers, both negative and positives
     */
    public static int[] sort(int[] a) {
        int R = 1 << 8; // number of combinations(range) for a byte word
        int W = 4; // number of byte words for 32 bit integer
        System.out.println(R);
        int[] aux = new int[a.length];

        for (int d = 1; d <= W; d++) {
            int[] count = new int[R];
            for (int i = 0; i < a.length; i++) {
                int ci = (d == W) ? extractByteAndInvertLastBit(a[i], d) :
                        extractByte(a[i], d);
                count[ci]++;
            }
            //computing sum of frequencies -> gives position of element i (count[i]) in original array
            for (int i = 1; i < R; i++) {
                count[i] += count[i - 1];
            }
            //form result, preserve stability
            for (int i = a.length - 1; i >= 0; i--) {
                int element = a[i];
                int ci = (d == W) ? extractByteAndInvertLastBit(element, d) :
                        extractByte(element, d);
                aux[count[ci] - 1] = element;
                count[ci]--;
            }
            //copy back to original
            for (int i = 0; i < a.length; i++) {
                a[i] = aux[i];
            }
        }
        return a;
    }

    private static int extractByteAndInvertLastBit(int element, int d) {
        return extractByte(element, d) ^ SIGN_INVERT_MASK;
    }

    private static int extractByte(int element, int d) {
        return (element >>> 8 * d) & MASK;
    }

}