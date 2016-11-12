package com.github.dtyshchenko.algs4fun.math;

/**
 * Largest palindrome product
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * @author denis on 11/11/16.
 */
public class LargestPalindromeProduct {

    public static int largestPalindromeProduct() {
        int maxPalindrome = 0;
        for (int x = 111; x < 999; x++) {
            for (int y = 111; y < 999; y++) {
                int product = x * y;
                if (isPalindrome(product)) {
                    maxPalindrome = Math.max(product, maxPalindrome);
                }
            }
        }
        return maxPalindrome;
    }

    public static boolean isPalindrome(int x) {
        int original = x;
        int mirrored = 0;
        while (x > 0) {
            int ld = x % 10;
            mirrored *= 10;
            mirrored += ld;
            x /= 10;
        }
        return original == mirrored;
    }

    public static void main(String[] args) {
        System.out.println(largestPalindromeProduct());
//        System.out.println(isPalindrome(1001));
//        System.out.println(isPalindrome(1011));
//        System.out.println(isPalindrome(1111));
//        System.out.println(isPalindrome(30303));

    }
}
