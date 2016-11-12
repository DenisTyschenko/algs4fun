package com.github.dtyshchenko.algs4fun;

import java.util.HashSet;
import java.util.Set;

/**
 * @author denis on 11/11/16.
 */
public class PrimeNumberUtil {

    public static boolean isPrime(Long x) {
        Set<Long> primes = new HashSet<>();
        Long factor = 2L;
        while (factor * factor <= x) {
            if (x % factor == 0) {
                return false;
            }
            primes.add(factor);
            factor = improve(factor, primes);
        }
        return true;
    }

    static Long improve(Long factor, Set<Long> primes) {
        factor++;
        for (Long prime: primes) {
            if (factor % prime == 0) {
                return improve(factor, primes);
            }
        }
        return factor;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(19_911_111_271L));
    }

}
