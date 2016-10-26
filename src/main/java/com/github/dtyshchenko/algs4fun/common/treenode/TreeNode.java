package com.github.dtyshchenko.algs4fun.common.treenode;

/**
 * As per TreeNode class provided by firecode.io
 * Created by denis on 10/18/16.
 */
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int data;

    public static TreeNode node(TreeNode left, int data, TreeNode right) {
        return new TreeNode(left, data, right);
    }

    public static TreeNode node(int data, TreeNode right) {
        return new TreeNode(null, data, right);
    }

    public static TreeNode node(TreeNode left, int data) {
        return new TreeNode(left, data, null);
    }

    public static TreeNode node(int data) {
        return new TreeNode(data);
    }

    public TreeNode(TreeNode left, int data, TreeNode right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public TreeNode(int data) {
        this(null, data, null);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return "[" + left + "(" + data + ")" + right + "]";
    }
}