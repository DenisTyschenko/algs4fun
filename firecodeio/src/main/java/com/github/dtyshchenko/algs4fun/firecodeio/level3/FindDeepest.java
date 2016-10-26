package com.github.dtyshchenko.algs4fun.firecodeio.level3;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.TreeSet;

/**
 *
 * Given a binary tree, write a method to find and return its deepest node. Return null for an empty tree.
 * Example:
 *       1
 *      / \
 *     2   3     ==> deepest = 9
 *    / \ / \
 *   4  5 6  7
 *  / \
 * 8   9
 *
 * Created by denis on 10/18/16.
 */
public class FindDeepest {

    // java.util.* has been imported for this problem.
    // You don't need any other imports.


    public static void main(String[] args) {
        System.out.println(
                findDeepest(new TreeNode(
                        new TreeNode(new TreeNode(3),
                                2,
                                new TreeNode(4)),
                        1,
                        new TreeNode(5))).data);
        System.out.println(findDeepest(new TreeNode(new TreeNode(2), 1, new TreeNode(3))).data);
        System.out.println(findDeepest(
                new TreeNode(new TreeNode(2), 1,
                        new TreeNode(new TreeNode(4), 3, new TreeNode(5)))).data);
        System.out.println(findDeepest(
                new TreeNode(new TreeNode(2), 1,
                        new TreeNode(null , 3, new TreeNode(5)))).data);
        System.out.println(findDeepest(
                new TreeNode(new TreeNode(2), 1,
                        new TreeNode(null , 3, new TreeNode(new TreeNode(5), 4, new TreeNode(6))))).data);
        System.out.println(findDeepest(
                new TreeNode(new TreeNode(2), 1,
                        new TreeNode(null , 3, new TreeNode(new TreeNode(5), 4, null)))).data);
    }

    public static TreeNode findDeepest(TreeNode root) {
        if(root == null) return null;
        TreeNode curr = null;
        Deque<TreeNode> q =new ArrayDeque<>();
        q.push(root);
        while(!q.isEmpty()) {
            curr = q.pop();
            if(curr.right != null) {
                q.push(curr.right);
            }
            if(curr.left != null) {
                q.push(curr.left);
            }
        }
        return curr;
    }

    public static TreeNode findDeepestIterative(TreeNode root) {
        if (root == null) return null;
        TreeNode currentNode = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeSet<TreeNode> deepestNodes =
                new TreeSet<>(
                        Comparator.<TreeNode, Integer>comparing(treeNode -> treeNode.data).reversed());
        while(currentNode != null || !stack.isEmpty()) {
            if (currentNode != null) {
                if (currentNode.left == null && currentNode.right == null) {
                    //current node is a leaf that traversed only once
                    deepestNodes.add(currentNode);
                }
                stack.push(currentNode);
                currentNode = currentNode.left;
            } else {
                currentNode = stack.pop();
                currentNode = currentNode.right;
            }
        }

        return deepestNodes.first();
    }
}
