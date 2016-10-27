package com.github.dtyshchenko.algs4fun.geeksforgeeks;


import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;

;import static java.lang.Math.max;

/**
 * <a href="http://www.geeksforgeeks.org/diameter-of-a-binary-tree/">Diameter of a Binary Tree</a>
 * and <a href="http://algorithms.tutorialhorizon.com/diameter-of-a-binary-tree/">Diameter Of a Binary Tree</a>
 *
 * @author denis on 10/26/16.
 */
public class DiameterOfBinaryTree {

    public static int diameter(TreeNode root) {
        return findDiameter(root).d;
    }


    private static Tuple findDiameter(TreeNode node) {
        if (node == null) {
            return new Tuple(0, 0);
        } else {
            Tuple leftRes = findDiameter(node.left);
            Tuple rightRes = findDiameter(node.right);

            int dnode = leftRes.h + rightRes.h + 1;
            dnode = max(dnode, max(leftRes.d, rightRes.d));
            int hnode = max(leftRes.h, rightRes.h) + 1;
            return new Tuple(dnode, hnode);
        }
    }

    private static class Tuple {
        int d;
        int h;

        public static Tuple of(int d, int h) {
            return new Tuple(d, h);
        }

        private Tuple(int d, int h) {
            this.d = d;
            this.h = h;
        }

    }
}
