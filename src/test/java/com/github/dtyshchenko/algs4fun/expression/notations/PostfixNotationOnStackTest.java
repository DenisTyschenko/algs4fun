package com.github.dtyshchenko.algs4fun.expression.notations;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by denis on 1/28/17.
 */
@RunWith(JUnitParamsRunner.class)
public class PostfixNotationOnStackTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @Parameters(source = PostfixNotationTest.DataProvider.class)
    public void verifyEvaluation(String expression, Integer expectedResult) {
        Assert.assertThat(PostfixNotationOnStack.evaluate(expression), is(expectedResult));
    }

    @Test
    @Parameters(source = PostfixNotationTest.FailureDataProvider.class)
    public void verifyFailure(String expression) {
        expectedException.expect(
                anyOf(instanceOf(IllegalStateException.class),
                        instanceOf(IllegalArgumentException.class)));

        PostfixNotationOnStack.evaluate(expression);
    }

}
