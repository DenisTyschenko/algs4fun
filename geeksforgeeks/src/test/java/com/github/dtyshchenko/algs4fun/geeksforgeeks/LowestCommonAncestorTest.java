package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.dtyshchenko.algs4fun.common.treenode.TreeNode.node;
import static com.github.dtyshchenko.algs4fun.geeksforgeeks.LowestCommonAncestor.lca;
import static org.hamcrest.core.Is.is;

/**
 * @author denis on 10/30/16.
 */
@RunWith(JUnitParamsRunner.class)
public class LowestCommonAncestorTest {

    public static Object[] data() {
        return new Object[][]{
                {node(node(2), 1, node(3)), node(2), node(3), 1},
                {node(node(2), 1, node(node(4), 3, node(node(6), 5, node(7)))), node(7), node(4), 3}
        };
    }

    public static Object[][] nodeNotPresent() {
        return new Object[][] {
                //there is no node 4 in tree, thus expecting null as the output
                {node(node(2), 1, node(3)), node(2), node(4)},
                //both nodes are not present
                {node(node(2), 1, node(node(4), 3, node(node(6), 5, node(7)))), node(9), node(10)}

        };
    }
    @Test
    @Parameters(method = "data")
    public void verifyLcaLookup(TreeNode tree, TreeNode aNode, TreeNode bNode, Integer expectedLcaNodeId) {
        TreeNode lca = lca(tree, aNode, bNode);
        Assert.assertNotNull(lca);
        Assert.assertThat(lca.data, is(expectedLcaNodeId));
    }

    @Test
    @Parameters(method = "nodeNotPresent")
    public void verifyLcaLookupWhenNodesAreNotPresent(TreeNode tree, TreeNode aNode, TreeNode bNode) {
        TreeNode lca = lca(tree, aNode, bNode);
        Assert.assertNull(lca);
    }
}
