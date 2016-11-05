package com.github.tyshchenko.algs4fun.hackerrank;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;

/**
 *
 * <a href="https://www.hackerrank.com/challenges/ctci-is-binary-search-tree">
 *     Trees: Is This a Binary Search Tree?</a>
 *
 *     For the purposes of this challenge, we define a binary search tree to be a binary tree with the following ordering properties:
 *     <ul>
 *         <li>The {@code data} value of every node in a node's left subtree is less than the data value of that node.</li>
 *         <li>The {@code data} value of every node in a node's right subtree is greater than the data value of that node.</li>
 *     </ul>
 *     Given the root node of a binary tree, can you determine if it's also a binary search tree?
 *     Complete the function in your editor below, which has {@code 1} parameter: a pointer to the root of a binary tree. It must return a boolean denoting whether or not the binary tree is a binary search tree. You may have to write one or more helper functions to complete this challenge.
 *     Note: A binary tree is not a binary search if there are duplicate values.


 * @author denis on 11/4/16.
 */
public class CheckBSTUtil {

    public static boolean checkBST(TreeNode root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean checkBST(TreeNode node, int leftConstraint, int rightConstraint) {
        if (node == null) {
            return true;
        }
        if (node.data >= rightConstraint || node.data <= leftConstraint) {
            return false;
        }
        return checkBST(node.left, leftConstraint, node.data) &&
                checkBST(node.right, node.data, rightConstraint);
    }

}
