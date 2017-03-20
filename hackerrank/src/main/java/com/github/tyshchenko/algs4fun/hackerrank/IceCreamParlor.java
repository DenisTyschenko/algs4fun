package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Comparator.comparing;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-ice-cream-parlor">
 *     Binary Search: Ice Cream Parlor</a>
 *
 * Created by denis on 2/22/17.
 */
public class IceCreamParlor {

    //object type needed to provide sort with comparator
    private final Integer[] prices;
    private final Integer[] priceIndexes;
    private final Comparator<Integer> priceIndexComparator;
    private final int sum;

    public IceCreamParlor(int[] prices, int sum) {
        this.prices = Arrays.stream(prices)
                .boxed()
                .toArray(Integer[]::new);
        this.priceIndexComparator = comparing(index -> prices[index]);
        this.priceIndexes = IntStream.range(0, prices.length)
                .boxed()
                //sort index array based on prices values
                .sorted(priceIndexComparator)
                .toArray(Integer[]::new);
        this.sum = sum;
    }

    public static String optimalPurchaseIds(String input) {
        Scanner in = new Scanner(input);
        int t = in.nextInt();
        StringBuilder solution = new StringBuilder();
        for(int a0 = 0; a0 < t; a0++){
            int sum = in.nextInt();
            int n = in.nextInt();
            int[] prices = new int[n];

            for(int i=0; i < n; i++) {
                prices[i] = in.nextInt();
            }
            IceCreamParlor icp = new IceCreamParlor(prices, sum);
            solution.append(icp.optimalPurchaseIdTuple()).append("\n");

        }
        in.close();

        return solution.toString();
    }

    public String optimalPurchaseIdTuple() {
        for (int i = 1; i < priceIndexes.length; i++) {
            Integer firstPriceIndex = priceIndexes[i - 1];
            int remainingSum = sum - prices[firstPriceIndex];
            Integer secondPriceIndex = binarySearch(i, priceIndexes.length, remainingSum);
            if (secondPriceIndex != null) {
                int first = min(firstPriceIndex, secondPriceIndex) + 1;
                int second = max(firstPriceIndex, secondPriceIndex) + 1;
                return first + " " + second;
            }
        }
        return null;
    }

    public Integer binarySearch(int fromIndex, int toIndex, int searchKey) {
        int lo = fromIndex;
        int hi = toIndex - 1;

        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            Integer price = prices[priceIndexes[mid]];
            if (searchKey > price) {
                lo = mid + 1;
            } else if (searchKey < price) {
                hi = mid - 1;
            } else {
                return priceIndexes[mid];
            }
        }
        return null;
    }
}
