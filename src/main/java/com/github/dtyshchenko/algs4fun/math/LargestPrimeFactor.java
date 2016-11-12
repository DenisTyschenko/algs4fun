package com.github.dtyshchenko.algs4fun.math;

/**
 *
 * Largest prime factor
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 *
 * @author denis on 11/11/16.
 */
public class LargestPrimeFactor {

    /**
     * Algorithm implemented assumes that input very large number should not be a prime number
     */
    public static long largestPrimeFactor(long x) {
        long factor = 2;
        while (x != 1) {
            if (x % factor == 0) {
                x /= factor;
            } else {
                factor++;
            }
        }
        return factor;
    }

    public static void main(String[] args) {
        System.out.println(largestPrimeFactor(600851475143L));
        System.out.println(largestPrimeFactor(8));
        //the below one is prime, takes infinite time to finish on my machine
        //algorithm implemented assumes that input very large number should not be a prime number
//        System.out.println(largestPrimeFactor(11111111159L));
    }
}
