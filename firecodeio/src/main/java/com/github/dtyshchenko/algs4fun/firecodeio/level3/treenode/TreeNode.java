package com.github.dtyshchenko.algs4fun.firecodeio.level3.treenode;

/**
 * As per TreeNode class provided by firecode.io
 * Created by denis on 10/18/16.
 */
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int data;

    public TreeNode(TreeNode left, int data, TreeNode right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public TreeNode(int data) {
        this(null, data, null);
    }
}