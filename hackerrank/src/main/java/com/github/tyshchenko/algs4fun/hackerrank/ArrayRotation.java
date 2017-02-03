package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

/**
 * @author denis on 11/4/16.
 */
public class ArrayRotation {

    /**
     * Bases on simple shifting of array elements to the left.
     * The left most shifted element is inserted from right side on vacant position.
     *
     * Takes quadratic O(n^2) time, but O(1) space
     *
     * Under firecode module there is alternative rotation implementation by using element swapping
     */
    public static int[] rotateLeft(int d, int[] els) {
        int N = els.length;
        for (int i = 0; i < d; i++) {
            int shifted = els[0];
            //shift by 1 to the left [1,2,3,4,5] -> [2,3,4,5,1]
            System.arraycopy(els, 1, els, 0, N - 1);
            els[N-1] = shifted;
        }
        return els;
    }

    /**
     * Takes O(n) time but O(2n) space
     */
    private static int[] rotateLeftLinear(int d, int[] src) {
        int[] rotated = new int[src.length];
        int r0 = src.length - d % src.length;
        int elsToTheRight = src.length - r0;

        System.arraycopy(src, 0, rotated, r0, elsToTheRight);
        System.arraycopy(src, elsToTheRight, rotated, 0, r0);

        return rotated;
    }
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5};

        String output = Arrays.stream(rotateLeft(1,input)).mapToObj(String::valueOf).collect(joining(" "));
        System.out.println(output);
    }
}
