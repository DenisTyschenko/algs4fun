package com.github.dtyshchenko.algs4fun.geeksforgeeks;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.dtyshchenko.algs4fun.common.treenode.TreeNode.node;
import static com.github.dtyshchenko.algs4fun.geeksforgeeks.DiameterOfBinaryTree.diameter;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 10/26/16.
 */
@RunWith(JUnitParamsRunner.class)
public class DiameterOfBinaryTreeTest {

    public static Object[][] data() {
        return new Object[][] {
                {node(1), 1},
                {node(node(2), 1, node(3)), 3},
                {node(node(node(4), 3, node(5)), 1, node(4)), 4},
                {node(node(2), 1, node(node(4), 3, node(5))), 4},
                {node(node(2), 1, node(node(node(6), 4, node(7)), 3, node(5))), 5},
                {node(node(2), 1), 2},
                {node(1, node(2)), 2},
                {node(1, node(node(node(node(6), 5), 4), 3, node(7, node(8, node(9))))), 7},
        };
    }

    @Test
    @Parameters(method="data")
    public void verifyDiameterCalculation(TreeNode root, int expectedDiameter) {
        Assert.assertThat(diameter(root), is(expectedDiameter));
    }

}

