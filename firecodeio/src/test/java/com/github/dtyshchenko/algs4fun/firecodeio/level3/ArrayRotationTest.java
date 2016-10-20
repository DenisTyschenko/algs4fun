package com.github.dtyshchenko.algs4fun.firecodeio.level3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Created by denis on 10/17/16.
 */
@RunWith(Parameterized.class)
public class ArrayRotationTest {

    @Parameters(name = "Test {index}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[] {}, 2, new int[] {}},
                {new int[] {0}, 2, new int[] {0}},
                {new int[] {0}, 1, new int[] {0}},
                {new int[] {0,1,2,3,4}, 2, new int[] {2,3,4,0,1}},
                {new int[] {0,1,2,3,4}, 3, new int[] {3,4,0,1,2}},
                {new int[] {0,1,2,3,4}, 4, new int[] {4,0,1,2,3}},
                {new int[] {0,1,2,3,4}, 5, new int[] {0,1,2,3,4}},
                {new int[] {0,1,2,3,4}, 6, new int[] {1,2,3,4,0}},
                {new int[] {0,1,2,3,4}, 12, new int[] {2,3,4,0,1}},
                {new int[] {0,-1,2,-3,-4}, 12, new int[] {2,-3,-4,0,-1}},
                {new int[] {0,-1,-1,-1,-1}, 12, new int[] {-1,-1,-1,0,-1}},
                {new int[] {0,1,1,1,1}, 12, new int[] {1,1,1,0,1}},
        });
    }

    @Parameter
    public int[] inputArray;
    @Parameter(1)
    public int rotationIndex;
    @Parameter(2)
    public int[] expectedRotatedArray;

    @Test
    public void verifyArrayRotation() {
        int[] actualRotatedArray = ArrayRotation.rotateLeft(inputArray, rotationIndex);
        Assert.assertArrayEquals(
                String.format("Rotation is NOT correct for rotation index: %s, actual array: %s expected: %s",
                    rotationIndex, Arrays.toString(actualRotatedArray), Arrays.toString(expectedRotatedArray)),
                expectedRotatedArray, actualRotatedArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyIllegalInputArrayIsHandled() {
        ArrayRotation.rotateLeft(null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyIllegalRotateIndexIsHandled() {
        ArrayRotation.rotateLeft(new int[] {0,2}, -1);
    }
}
