package com.github.tyshchenko.algs4fun.hackerrank;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author denis on 11/4/16.
 */
public class CheckBSTUtil {

    public static boolean checkBST(TreeNode root) {
        //from the beginning assuming that tree is a BST
        AtomicBoolean isBST = new AtomicBoolean(true);
        traverseAndCheck(root, isBST);
        return isBST.get();
    }

    /**
     *
     * @param isBST - used as a mutable wrapper around immutable Boolean. Serves as indicator to detect when tree is not a BST.
     * Another approach is to use both int and boolean in return type, and properly analyze return type after each recursion return.
     * Will complicate implementation slightly.
     */
    public static int traverseAndCheck(TreeNode node, AtomicBoolean isBST) {
        if (node.left == null && node.right == null) {
            return node.data;
        } else {
            if (node.left != null && node.data <= traverseAndCheck(node.left, isBST)) {
                isBST.set(false);
            }
            if (node.right != null && node.data >= traverseAndCheck(node.right, isBST)) {
                isBST.set(false);
            }
            return node.data;
        }
    }

}
