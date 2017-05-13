package com.github.dtyshchenko.algs4fun;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by denis on 5/13/17.
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        //sort index array based on values from original array
        Integer[] sortedIndexes = Arrays.stream(getIndexArray(nums))
                .boxed()
                .sorted(Comparator.comparing(index -> nums[index]))
                .toArray(Integer[]::new);

        for (int i = 0; i < sortedIndexes.length; i++) {
            int found = Arrays.binarySearch(sortedIndexes, target - nums[sortedIndexes[i]],
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
    private static int[] getIndexArray(int[] nums) {
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        return indexes;
    }
}
