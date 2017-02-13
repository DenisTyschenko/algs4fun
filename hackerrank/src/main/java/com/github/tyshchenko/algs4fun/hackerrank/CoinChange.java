
package com.github.tyshchenko.algs4fun.hackerrank;

/**
 *
 * <a href="https://www.hackerrank.com/challenges/ctci-coin-change">DP: Coin change</a>
 *
 * The solution below uses memoization to store already computed results during recursion traversal
 *
 * Created by denis on 2/12/17.
 */
public class CoinChange {

    private static final int NOT_VISITED = -1;

    public static long solutions(int sum, int[] coins) {
        long[][] results = new long[sum + 1][coins.length];
        //one element is ignored during calculation
        // sums 0 - is already a result
        // made sum + 1 elements for simplicity to avoid translation from sum to index in main subroutine
        for (int i = sum; i >= 0; i--) {
            for (int j = 0; j < coins.length; j++) {
                results[i][j] = -1;
            }
        }

        return solutions(sum, coins, 0, results);
    }

    private static long solutions(int sum, int[] coins, int index, long[][] results) {
        if (sum < 0) {
            return 0;
        }
        if (sum == 0) {
            return 1;
        }
        if (results[sum][index] != NOT_VISITED) {
            return results[sum][index];
        }

        long result = 0;
        for (int i = index; i < coins.length; i++) {
            result += solutions(sum - coins[i], coins, i, results);
        }

        results[sum][index] = result;

        return result;
    }

}
