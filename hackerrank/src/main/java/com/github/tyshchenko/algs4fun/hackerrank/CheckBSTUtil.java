package com.github.tyshchenko.algs4fun.hackerrank;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author denis on 11/4/16.
 */
public class CheckBSTUtil {

    public static boolean checkBST(TreeNode root) {
        //from the beginning assuming that tree is a BST
        BSTStatus bstStatus = new BSTStatus();
        traverseAndCheck(root, bstStatus);
        return bstStatus.isBST;
    }

    /**
     * @param bstStatus - used as a mutable wrapper around immutable Boolean. Serves as indicator to detect when tree is not a BST.
     *              Another approach is to use both int and boolean in return type, and properly analyze return type after each recursion return.
     *              Will complicate implementation slightly.
     */
    public static List<Integer> traverseAndCheck(TreeNode node, BSTStatus bstStatus) {
        if (node.left == null && node.right == null) {
            return singletonList(node.data);
        } else {
            List<Integer> currentNodeData = new ArrayList<>();
            if (node.left != null) {
                List<Integer> leftSubtreeData = traverseAndCheck(node.left, bstStatus);
                Integer leftSubtreeRightMostNodeData = leftSubtreeData.get(leftSubtreeData.size() - 1);
                Integer leftSubtreeLeftMostNodeData = leftSubtreeData.get(0);
                currentNodeData.add(leftSubtreeLeftMostNodeData);
                if (node.data <= leftSubtreeRightMostNodeData) {
                    bstStatus.notBST();
                }
            }
            // adding current node for the cases when current node has one sibling either from left or right side
            currentNodeData.add(node.data);

            if (node.right != null) {
                List<Integer> rightSubtreeData = traverseAndCheck(node.right, bstStatus);
                Integer rightSubtreeLeftMostNodeData = rightSubtreeData.get(0);
                Integer rightSubtreeRightMostNodeData = rightSubtreeData.get(rightSubtreeData.size() - 1);
                currentNodeData.add(rightSubtreeRightMostNodeData);
                if (node.data >= rightSubtreeLeftMostNodeData) {
                    //compare to the right most element
                    bstStatus.notBST();
                }
            }
            return currentNodeData;
        }
    }

    private static class BSTStatus {
        private boolean isBST = true;

        public void notBST() {
            isBST = false;
        }
    }
}
