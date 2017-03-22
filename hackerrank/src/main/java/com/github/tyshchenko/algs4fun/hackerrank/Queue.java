package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks">
 *     Queues: A Tale of Two Stacks</a>
 *
 *
 * Created by denis on 3/21/17.
 */
public class Queue<T> {

    private final Deque<T> enqueueStack = new LinkedList<>();
    private final Deque<T> dequeueStack = new LinkedList<>();

    public T dequeue() {
        bufferDequeItems();
        return dequeueStack.isEmpty() ? null :
                dequeueStack.pop();
    }

    public void enqueue(T x) {
        enqueueStack.push(x);
    }

    public T peek() {
        bufferDequeItems();
        return dequeueStack.isEmpty() ? null :
                dequeueStack.peek();
    }

    private void bufferDequeItems() {
        if (dequeueStack.isEmpty()) {
            moveItemsFromTo(enqueueStack, dequeueStack);
        }
    }

    private void moveItemsFromTo(Deque<T> src, Deque<T> dst) {
        while (!src.isEmpty()) {
            dst.push(src.pop());
        }
    }
}
