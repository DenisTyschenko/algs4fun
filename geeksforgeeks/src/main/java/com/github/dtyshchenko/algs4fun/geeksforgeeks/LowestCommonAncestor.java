package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;

/**
 * <a href="http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/">
 * Lowest Common Ancestor in a Binary Tree</a>
 *
 * O(n) solution, O(n) space due to stack caused by recursion calls
 *
 * @author denis on 10/30/16.
 */
public class LowestCommonAncestor {

    /**
     * For simplicity let us use data int field in TreeNode as id, so data for every node is unique
     */
    public static TreeNode lca(TreeNode root, TreeNode a, TreeNode b) {
        // this two additional checks do not introduce more complexity
        // as A or B subtree will be traversed only once during lca lookup
        if (isFirstAncestorOfSecond(a, b)) {
            return a;
        } else if (isFirstAncestorOfSecond(b, a)) {
            return b;
        } else {
            TreeNode lca = findLCA(root, a.data, b.data);
            // possible that one of or both nodes that are being searched are not present in tree
            // if nothing present than null returned
            // if b is not present but a is, than lookup method returns a node or b node otherwise
            // above checks already filtered out that neither a nor b is lca
            return lca == null || a.data == lca.data || b.data == lca.data ? null : lca;
        }
    }

    /**
     * Tree traversal assumes neither a is ancestor of b nor b is ancestor of a.
     */
    private static TreeNode findLCA(TreeNode node, int idA, int idB) {
        if (node == null) {
            return null;
        } else if (node.data == idA || node.data == idB) {
            //temp result, just indication that one of searched nodes was found
            return node;
        } else {
            TreeNode leftMatch = findLCA(node.left, idA, idB);
            TreeNode rightMatch = findLCA(node.right, idA, idB);

            if (leftMatch != null && rightMatch != null) {
                //if we have return value from left AND right recursion calls
                //then current noe is lowest common ancestor
                return node;
            } else if (leftMatch != null) {
                // if only one recursion branch reported a node than return is as is
                return leftMatch;
            } else if (rightMatch != null) {
                // if only one recursion branch reported a node than return is as is
                return rightMatch;
            } else {
                // if nothing found at all
                return null;
            }
        }
    }

    private static boolean isFirstAncestorOfSecond(TreeNode a, TreeNode b) {
        if (a == null) {
            //not found in left or right subtree
            return false;
        } else if (a.data == b.data) {
            //current traversed node is matches lookup
            return true;
        } else {
            return isFirstAncestorOfSecond(a.left, b) | isFirstAncestorOfSecond(a.right, b);
        }
    }
}
