package com.github.dtyshchenko.algs4fun.basics.graphs;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.dtyshchenko.algs4fun.common.treenode.TreeNode.node;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/9/16.
 */
@RunWith(JUnitParamsRunner.class)
public class BreadthFirstTreeTraversalTest {

    public static Object[] data() {
        return new Object[][]{
                {node(1), 1, new int[]{1}},
                {node(1), 2, new int[]{}},
                {node(node(2), 1, node(3)), 1, new int[]{1}},
                {node(node(2), 1, node(3)), 2, new int[]{2,3}},
                {node(node(2), 1), 2, new int[]{2}},
                {node(1, node(3)), 2, new int[]{3}},
                {node(1, node(3)), 1, new int[]{1}},
                {node(node(node(4), 2, node(5)), 1, node(node(6), 3)), 2, new int[]{2,3}},
                {node(node(node(4), 2, node(5)), 1, node(node(6), 3)), 3, new int[]{4,5,6}},
                {node(node(node(4), 2, node(5)), 1, node(node(6), 3, node(7))), 3, new int[]{4,5,6,7}},
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyLevelNodes(TreeNode root, int level, int[] expected) {
        List<TreeNode> levelNodes = BreadthFirstTreeTraversal.nodesOnLevel(root, level);
        int[] actualNodeIds = levelNodes.stream().mapToInt(node -> node.data).toArray();
        Assert.assertThat(actualNodeIds, is(expected));
    }
}
