package com.github.dtyshchenko.algs4fun;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by denis on 5/13/17.
 */
public class TwoSum {

    public static int[] twoSumQuadtratic(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (key == nums[j]) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[0];
    }

    public static int[] twoSum(int[] nums, int target) {
        //sort index array based on values from original array
        Integer[] sortedIndexes = getIndexArray(nums);
        Arrays.sort(sortedIndexes, Comparator.comparing(index -> nums[index]));

        for (int i = 0; i < sortedIndexes.length; i++) {
            int found = Arrays.binarySearch(sortedIndexes, i + 1, sortedIndexes.length,
                    target - nums[sortedIndexes[i]],
                    (index, searchKey) -> Integer.compare(nums[index], searchKey));
            //binary search returns positive if finds the key
            //avoid match with current index (by task definition use element only once)
            if (found >= 0 && found != i) {
                return new int[]{
                        min(sortedIndexes[i], sortedIndexes[found]),
                        max(sortedIndexes[i], sortedIndexes[found])
                };
            }
        }
        return new int[0];
    }

    /**
     * Get index array alongside with original
     */
    private static Integer[] getIndexArray(int[] nums) {
        Integer[] indexes = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        return indexes;
    }
}
