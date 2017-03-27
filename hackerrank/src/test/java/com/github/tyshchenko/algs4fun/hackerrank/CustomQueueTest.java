package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.tyshchenko.algs4fun.hackerrank.CustomQueueTest.Op.op;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by denis on 3/21/17.
 */
@RunWith(JUnitParamsRunner.class)
public class CustomQueueTest {

    public static Object[][] data() {
        return new Object[][]{
                {new CustomQueue<Integer>(), asList(
                        op(OpType.DEQUEUE, null),
                        op(OpType.PEEK, null),
                        op(OpType.ENQUEUE, 3),
                        op(OpType.PEEK, 3),
                        op(OpType.PEEK, 3),
                        op(OpType.DEQUEUE, 3),
                        op(OpType.PEEK, null),
                        op(OpType.DEQUEUE, null)),

                },
                {new CustomQueue<Integer>(), asList(
                        op(OpType.ENQUEUE, 3),
                        op(OpType.ENQUEUE, 4),
                        op(OpType.ENQUEUE, 5),

                        op(OpType.PEEK, 3),
                        op(OpType.DEQUEUE, 3),
                        op(OpType.PEEK, 4),
                        op(OpType.DEQUEUE, 4),
                        op(OpType.PEEK, 5),
                        op(OpType.DEQUEUE, 5))
                },
                {new CustomQueue<Integer>(), asList(
                        op(OpType.ENQUEUE, 3),
                        op(OpType.ENQUEUE, 4),
                        op(OpType.PEEK, 3),
                        op(OpType.ENQUEUE, 5),

                        op(OpType.PEEK, 3),
                        op(OpType.DEQUEUE, 3),
                        op(OpType.PEEK, 4),
                        op(OpType.DEQUEUE, 4),
                        op(OpType.PEEK, 5),
                        op(OpType.DEQUEUE, 5))
                },
                {new CustomQueue<String>(), asList(
                        op(OpType.ENQUEUE, "3a"),
                        op(OpType.ENQUEUE, "4a"),
                        op(OpType.ENQUEUE, "5a"),

                        op(OpType.PEEK, "3a"),
                        op(OpType.DEQUEUE, "3a"),
                        op(OpType.PEEK, "4a"),
                        op(OpType.DEQUEUE, "4a"),
                        op(OpType.PEEK, "5a"),
                        op(OpType.DEQUEUE, "5a"))
                }

        };
    }

    @Test
    @Parameters(method = "data")
    public <T> void verifyQueueOperations(CustomQueue<T> queue, List<Op<T>> operations) {
        for(Op<T> op: operations) {
            switch (op.type) {
                case ENQUEUE: {
                    queue.enqueue(op.item);
                    break;
                }
                case DEQUEUE: {
                    Assert.assertThat("DEQUEUE", queue.dequeue(), is(op.item));
                    break;
                }
                case PEEK:{
                    Assert.assertThat("PEEK", queue.peek(), is(op.item));
                    break;
                }
                default: throw new IllegalStateException("unknown operation type");
            }
        }
    }

    private enum OpType {
        ENQUEUE, DEQUEUE, PEEK
    }

    public static class Op<T> {
        private final T item;
        private final OpType type;

        static <T> Op op(OpType type, T item) {
            return new Op<>(type, item);
        }

        Op(OpType type, T item) {
            this.item = item;
            this.type = type;
        }
    }


}