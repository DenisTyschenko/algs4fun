package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <a href="https://www.hackerrank.com/challenges/morgan-and-a-string">
 *     Morgan And String</a>
 *
 * Created by denis on 8/1/17.
 */
public class MorganAndString {

    public static String lexiMinString(String input) {
        Scanner sc = new Scanner(input);
        int testCases = sc.nextInt();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < testCases; i++) {
            Queue<String> first = combineDescending(sc.next());
            Queue<String> second = combineDescending(sc.next());
            result.append(lexiMinString(first, second))
                    .append("\n");
        }
        return result.toString();
    }

    public static String lexiMinString(Queue<String> first, Queue<String> second) {
        StringBuilder result = new StringBuilder();
        while (!first.isEmpty() || !second.isEmpty()) {
            if (first.isEmpty()) {
                result.append(second.poll());
                continue;
            }
            if (second.isEmpty()) {
                result.append(first.poll());
                continue;
            }
            String fToken = first.peek();
            String sToken = second.peek();
            if (fToken.compareTo(sToken) == 0) {
                result.append(minFirstToken(first, second).poll());
            } else if (less(fToken, sToken)) {
                result.append(first.poll());
            } else if (less(sToken, fToken)){
                result.append(second.poll());
            }
        }
        return result.toString();
    }

    public static Queue<String> minFirstToken(Queue<String> first, Queue<String> second) {
        Iterator<String> fi = first.iterator();
        Iterator<String> si = second.iterator();
        String fToken = fi.next();
        String sToken = si.next();
        while (fToken.equals(sToken)) {
            if (!fi.hasNext() && !si.hasNext()) {
                // does not matter what sequence to return
                return first;
            } else if (!fi.hasNext() && si.hasNext()) {
                // sequence with no more elements always treat as greater
                return second;
            } else if (fi.hasNext() && !si.hasNext()) {
                // sequence with no more elements always treat as greater
                return first;
            }
            fToken = fi.next();
            sToken = si.next();
        }
        return less(fToken, sToken) ? first : second;
    }

    /**
     * Process input and combine descending chars into a string
     * 'D' 'C' 'B' 'A' into "DCBA"
     * This will allow much more quick comparison with for example 'F'
     * Once first 'D' compared to 'F' the entire descending string 'DCBA' can be reported to result
     */
    public static LinkedList<String> combineDescending(String input) {
        LinkedList<String> result = new LinkedList<>();
        if (input == null || input.isEmpty()) {
            return result;
        }
        if (input.length() == 1) {
            result.add(input);
            return result;
        }
        char[] chars = input.toCharArray();

        for (int cur = 0; cur < input.length(); cur++) {
            StringBuilder acc = new StringBuilder();
            for (int next = cur + 1;
                 next < input.length() && chars[cur] >= chars[next]; ) {
                acc.append(chars[cur]);
                cur = next++;
            }
            String token = acc.append(chars[cur]).toString();
            result.add(token);
        }
        return result;
    }

    private static boolean less(String tokenA, String tokenB) {
        if (tokenA.compareTo(tokenB) < 0) {
            return !tokenB.startsWith(tokenA);
        }
        return tokenA.startsWith(tokenB);
    }

}
