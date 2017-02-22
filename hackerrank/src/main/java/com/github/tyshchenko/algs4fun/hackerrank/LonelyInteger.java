package com.github.tyshchenko.algs4fun.hackerrank;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-lonely-integer">Lonely Integer</a>
 *
 * <h3>Complexity:</h3>
 * Time O(n). No auxiliary space required!
 * <h3>Solution:</h3>
 * As soon as we know that sequence contains only one distinct element for sure
 * we can xor all the elements one by one
 * Eventually xor will give that one distinct element.
 *
 * If we would uncertain about the input containing distinct elements
 * we still can xor all the elements and if resulting xor is not equal
 * to zero, then that resulting xor is the distinct element
 *
 * Approach works only if there is one distinct element in sequence
 *
 * Created by denis on 2/21/17.
 */
public class LonelyInteger {


   public static int distinct(int[] input) {
       int xor = 0;
       for (int item: input) {
           xor ^= item;
       }
       return xor;
   }
}
