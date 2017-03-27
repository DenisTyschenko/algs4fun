package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-big-o">
 * Time Complexity: Primality</a>
 * <p>
 * Uses O(sqrt(n)) time complexity to test whether "n" is a prime number
 * <p>
 * Created by denis on 3/24/17.
 */
public class Primality {

    public static String[] recognizePrimality(int[] items) {
        String[] result = new String[items.length];

        for (int i = 0; i < items.length; i++) {
            if (isPrime(items[i])) {
                result[i] = "Prime";
            } else {
                result[i] = "Not prime";
            }
        }
        return result;
    }

    private static boolean isPrime(int x) {
        if (x == 1) {
            return false;
        }
        List<Integer> primes = new ArrayList<>();
        int prime = 2;
        primes.add(prime);

        while (prime * prime <= x) {
            if (x % prime == 0) {
                return false;
            }
            prime = nextPrime(prime + 1, primes);
            primes.add(prime);
        }

        return true;
    }

    private static int nextPrime(int factor, List<Integer> primes) {

        while (!isPrime(factor, primes)) {
            factor++;
        }
        return factor;
    }

    private static boolean isPrime(int factor, List<Integer> primes) {
        return primes.stream().noneMatch(prime -> factor % prime == 0);
    }

}
