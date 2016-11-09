package com.github.dtyshchenko.algs4fun.basics.graphs;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Solution for <a href="http://www.geeksforgeeks.org/level-order-tree-traversal/">Level Order Tree Traversal</a>
 *
 * For example return nodes from k level only.
 * Or print nodes by level (as easy modification)
 * <pre>
 * {@code
 *
 *       0
 *     /  \
 *    1    2
 *   / \    \
 *  3   4    5
 *
 * return nodes on level 1: 0
 * return nodes on level 2: 1,2
 * return nodes on level 3: 3,4,5
 *
  * }
 * </pre>
 *
 * @author denis on 11/9/16.
 */
public class BreadthFirstTreeTraversal {

    public static List<TreeNode> nodesOnLevel(int level, TreeNode root) {
        LinkedList<TreeNode> levelNodes = new LinkedList<>();
        trackNodeOnLevel(levelNodes, root);
        int currentLevel = 1;
        while(!levelNodes.isEmpty()) {
            if (currentLevel == level) {
                return levelNodes;
            }
            LinkedList<TreeNode> nextLevel = new LinkedList<>();
            while (!levelNodes.isEmpty()) {
                TreeNode node = levelNodes.poll();
                trackNodeOnLevel(nextLevel, node.left);
                trackNodeOnLevel(nextLevel, node.right);
            }
            levelNodes = nextLevel;
            currentLevel++;
        }
        return Collections.emptyList();
    }

    private static void trackNodeOnLevel(Queue<TreeNode> nextLevel, TreeNode left) {
        if (left != null) {
            nextLevel.offer(left);
        }
    }

}
