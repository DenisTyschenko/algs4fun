package com.github.dtyshchenko.algs4fun.firecodeio.level3;

/**
 * Rotate an array to the left by k positions without using extra space.k can be greater than the size of the array.
 * Example:
 * rotateLeft({1,2,3,4,5},2) --> {3,4,5,1,2}
 *
 * Created by denis on 10/17/16.
 */
public class ArrayRotation {

    // java.util.* has been imported for this problem.
// You don't need any other imports.

    public static int[] rotateLeft(int[] arr, int k) {
        if (arr == null) throw new IllegalArgumentException("Input array must not be null");
        if (k < 0) throw new IllegalArgumentException("Rotation index must not be less than zero");
        if (arr.length == 0) return arr;

        for (int rotationIndex = k % arr.length, j = 0; rotationIndex < arr.length; rotationIndex++, j++) {
            for (int swapIndex = rotationIndex; swapIndex > j; swapIndex--) {
                swap(arr, swapIndex, swapIndex-1);
            }
        }

        return arr;
    }

    private static int[] swap(int[] arr, int ia, int ib) {
        if (ia == ib) return arr;
        arr[ia] += arr[ib];
        arr[ib] = arr[ia] - arr[ib]; //original arr[ia]
        arr[ia] -= arr[ib]; //original arr[ib]
        return arr;
    }
}
