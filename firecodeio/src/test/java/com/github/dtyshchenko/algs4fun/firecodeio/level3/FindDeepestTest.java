package com.github.dtyshchenko.algs4fun.firecodeio.level3;

import com.github.dtyshchenko.algs4fun.common.treenode.TreeNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

/**
 * Created by denis on 10/18/16.
 */
@RunWith(Parameterized.class)
public class FindDeepestTest {

    @Parameterized.Parameters(name = "Test {index}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {new TreeNode(1), 1},
                {new TreeNode(new TreeNode(2), 1, new TreeNode(3)), 3},
                {new TreeNode(new TreeNode(new TreeNode(4), 2, new TreeNode(5)), 1, new TreeNode(3)), 5}
        });
    }

    @Parameterized.Parameter
    public TreeNode inputTree;

    @Parameterized.Parameter(1)
    public int expectedDeepestNodeData;

    @Test
    public void verifyFindDeepest() {
        TreeNode actualDeepestNode = FindDeepest.findDeepestIterative(inputTree);

        Assert.assertThat("For the input provided expected not null result", actualDeepestNode, is(notNullValue()));
        Assert.assertThat(actualDeepestNode.left, is(nullValue()));
        Assert.assertThat(actualDeepestNode.right, is(nullValue()));
        Assert.assertThat(actualDeepestNode.data, is(expectedDeepestNodeData));

    }

    @Test
    public void verifyNullInput() {
        Assert.assertThat("For null input tree, null deepest node is expected",
                FindDeepest.findDeepestIterative(null), is(nullValue()));
    }
}
