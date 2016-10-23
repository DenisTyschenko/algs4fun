package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="http://www.geeksforgeeks.org/vertical-sum-in-a-given-binary-tree/">Vertical Sum in a given Binary Tree | Set 1</a>
 *
 * Given a Binary Tree, find vertical sum of the nodes that are in same vertical line. Print all sums through different vertical lines.
 *
 *       1
 *     /   \
 *    2      3
 *   / \    / \
 *  4   5  6   7
 *
 *  The tree has 5 vertical lines
 *  Vertical-Line-1 has only one node 4 => vertical sum is 4
 *  Vertical-Line-2: has only one node 2=> vertical sum is 2
 *  Vertical-Line-3: has three nodes: 1,5,6 => vertical sum is 1+5+6 = 12
 *  Vertical-Line-4: has only one node 3 => vertical sum is 3
 *  Vertical-Line-5: has only one node 7 => vertical sum is 7
 *
 *  @author denis on 10/20/16.
 */
public class VerticalSumInBST {

    private final Node[] bst;

    //hash data structure to store horizontal indexes:
    // pros
    // 1. we have negative horizontal indexes, can store as is as a key
    // 2. no need to predict the possible amount of different horizontal indexes
    // cons
    // 1. unordered - result sums output can be mixed up
    // alternative:
    // use simple array :
    // 1. solve negative indexes
    // 2. predict the number of elements - amount of unique horizontal indexes
    private final Map<Integer, Integer> verticalSums = new HashMap<>();

    /**
     * may return mixed up values with regards to ordering
     * no guarantee that the left most vertical sum is the first and the right most is the last in array
     */
    public static int[] verticalSumOfBSTUnordered(int[] bst) {
        return verticalSumOfBST(bst).values()
                .stream().mapToInt(t -> t).toArray();
    }

    public static Map<Integer, Integer> verticalSumOfBST(int[] bst) {
        VerticalSumInBST sumInBst = new VerticalSumInBST(bst);
        sumInBst.inorderVerticalSum(0, 0);
        return sumInBst.verticalSums;
    }
    public VerticalSumInBST(int[] bst) {
        this.bst = new Node[bst.length];
        for (int i=0; i < bst.length; i++) {
            this.bst[i] = new Node(bst[i], 0); //initially hd is zero
        }
    }

    /**
     *
     */
    private int inorderVerticalSum(int ni, int hd) {
        if (isLeaf(ni)) {
            bst[ni] = new Node(bst[ni].data, hd);
            computeSum(bst[ni], hd);
            return ni;
        } else {
            inorderVerticalSum(leftCi(ni), hd-1);
            inorderVerticalSum(rightCi(ni), hd+1);
            bst[ni] = new Node(bst[ni].data, hd);
            computeSum(bst[ni], hd);
            return ni;
        }
    }

    private void computeSum(Node node, int hd) {
        verticalSums.computeIfPresent(hd, (hdi, sum) -> sum + node.data);
        verticalSums.computeIfAbsent(hd, hdi -> node.data);
    }

    /**
     * Right Child Index
     */
    private int rightCi(int ni) {
       return ni*2 + 2;
    }

    /**
     * Left Child Index
     */
    private int leftCi(int ni) {
        return ni*2 + 1;
    }

    private boolean isLeaf(int ni) {
        return rightCi(ni) >= bst.length && leftCi(ni) >= bst.length;
    }

    private static class Node {
        int data;
        //horizontal index
        int hd;

        public Node(int data, int hd) {
            this.data = data;
            this.hd = hd;
        }

        @Override
        public String toString() {
            return "[d:"+data+",hd:"+hd+"]";
        }
    }

}
