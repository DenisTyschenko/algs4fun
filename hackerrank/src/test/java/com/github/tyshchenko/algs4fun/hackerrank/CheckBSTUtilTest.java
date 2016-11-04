package com.github.tyshchenko.algs4fun.hackerrank;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.dtyshchenko.algs4fun.common.treenode.TreeNode.node;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author denis on 11/4/16.
 */
@RunWith(JUnitParamsRunner.class)
public class CheckBSTUtilTest {

    public static Object[] data() {
        return new Object[][]{
                {node(1), true},
                {node(node(2), 1, node(0)), false},
                {node(node(1), 2, node(3)), true},
                //by task description same values in BST are not allowed
                {node(node(1), 1, node(3)), false},
                {node(node(2, node(3)), 4, node(node(node(5), 6, node(7)), 8)), true},
                {node(node(2, node(3)), 4, node(node(node(9), 6, node(7)), 8)), false},
                {node(node(2, node(1)), 4, node(node(node(5), 6, node(7)), 8)), false}
        };
    }

    @Test
    @Parameters(method = "data")
    public void verifyBSTCheck(TreeNode root, boolean expected) {
        Assert.assertThat(CheckBSTUtil.checkBST(root), is(expected));
    }
}
