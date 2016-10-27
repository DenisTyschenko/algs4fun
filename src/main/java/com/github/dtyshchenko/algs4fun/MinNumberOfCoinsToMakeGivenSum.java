package com.github.dtyshchenko.algs4fun;

import java.util.Arrays;

/**
 * @author denis on 10/27/16.
 */
public class MinNumberOfCoinsToMakeGivenSum {
    static int MARKER = -1;

    //TODO: get rid of marker
    //TODO: improve by following  dynamic programming approach
    public static int minNumberOfCoins(int[] coins, int sum) {
        if (sum < 0) return MARKER; // no sense to combine with already negative sum
        if (coins.length == 1) return sum - coins[0] == 0 ? 1 : MARKER;
        if (sum - coins[0] == 0) {
            // if the first coin makes solution, no sense to proceed
            // any other solutions from this layer
            // will have the same number of coins or worse
            return 1;
        } else {
            int[] coinsWithoutFirsOne = Arrays.copyOfRange(coins, 1, coins.length);
            int firstApplied = minNumberOfCoins(coinsWithoutFirsOne, sum - coins[0]);
            int firstNotApplied = minNumberOfCoins(coinsWithoutFirsOne, sum);

            if (firstApplied == MARKER && firstNotApplied == MARKER) {
                return MARKER;
            } else if (firstApplied == MARKER){
                return firstNotApplied;
            } else if (firstNotApplied == MARKER) {
                return firstApplied + 1;
            } else {
                return firstNotApplied <= firstApplied ?
                        firstNotApplied : firstApplied + 1;
            }
        }
    }
}
