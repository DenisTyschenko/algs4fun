package com.github.dtyshchenko.algs4fun.basics.sorting;

import java.util.Arrays;

/**
 * <p>
 * Simple binary heap of ints with parent element always greater or equals than left child and right child.
 * Insertion and removal is done in LogN. With N operations its O(NLogN)
 * Heap Construction from arbitrary array  is O(N) see <a href="http://algs4.cs.princeton.edu/24pq/">Priority Queue</a>
 * Exercises, item 20 for the proof.<br/>
 * Requires NO extra space O(1)
 *</p>
 * <p>
 * Also provides heapsort method to sort incoming array in (NLogN)
 * implemented as a combination of heap construction and then elements removal from constructed heap
 * </p>
 * <p>
 * Does not provide auto-resizable functionality
 * </p>
 *
 * @author denis on 10/25/16.
 */
public class BinaryHeap {
    //0 element is not touched to simplify index calculation
    private int[] heap;
    private int N;

    public static int[] heapsort(int[] items) {
        //notice constructor has copied items
        //once the above can be avoid we can do in place sort by
        // 1. picking max head and putting it as last element in array AND reduce size of the heap (N-1)
        // 2. pick last leaf in the heap and put to the head and call sink
        // 3. Until the last element remains in heap
        //So heap represents a decreasing window, where all elements
        //to the right of the window were retrieved as heads and already sorted
        BinaryHeap bh = new BinaryHeap(items);
        for (int i = items.length-1; i >=0; i--) {
            items[i] = bh.poll();
        }
        return items;
    }

    /**
     * As zero element is not touched for simplicity we need to copy original array
     * i.e. emulate shift on one element to the right
     * To avoid untouched zero element we need to change logic around index calculation
     * <p>
     * left child is 2*k+1
     * right child is 2*k+2
     * parent for the left/right child is (k/2 - (k+1)%2)
     * the last derived from observation:
     * <ul>
     * <li>1/2 = 0  1%2 = 1</li>
     * <li>2/2 = 1  2%2 = 0</li>
     * <li>3/2 = 1  3%2 = 1</li>
     * <li>4/2 = 2  4%2 = 0</li>
     * </ul>
     * Left child gives correct index, right child gives incorrect one. To avoid recognizing whether the node is left or right child:
     * <ul>
     * <li>Node with even k index gives wrong parent, this can be fixed if subtract (k+1)%2 from the next odd</li>
     * <li>In turn odd k gives correct index and, (k+1)%2 from next even gives zero, so does not change correct index</li>
     * </ul>
     */
    public BinaryHeap(int[] els) {
        this.heap = new int[els.length + 1];
        this.N = els.length;
        System.arraycopy(els, 0, heap, 1, els.length);
        heapify();
    }

    public BinaryHeap(int capacity) {
        this.heap = new int[capacity + 1]; // +1 as zero is reserved
    }

    /**
     * Add element to the end of the heap
     * O(log(n))
     */
    public void offer(int a) {
        heap[++N] = a;
        System.out.println("Array offer, before swim: " + a + " , " + Arrays.toString(heap));
        swim(N);
        System.out.println("Array after offer: " + a + " , " + Arrays.toString(heap));
    }

    /**
     * Removes the head
     * O(log(n))
     */
    public int poll() {
        int max = heap[1];
        //if it were handling Object types need to assign null to the last element
        int lastElement = heap[N];
        heap[N--] = 0;
        heap[1] = lastElement;
        sink(1);
        System.out.println("Array after poll: " + max + " , " + Arrays.toString(heap));
        return max;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * Rearranges backing array into binary heap
     * O(n)
     */
    private void heapify() {
        for (int i = N / 2; i >= 1; i--) {
            sink(i);
        }
    }

    /**
     * Parent index is simply k/2
     *
     * @param k - index of the element that may violate heap invariant
     */
    private void swim(int k) {
        while (k > 1 && heap[k] > heap[k / 2]) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void exch(int i, int j) {
        int tmp = heap[j];
        heap[j] = heap[i];
        heap[i] = tmp;
    }

    /**
     * left child index is (2*k) right is (2*k+1)
     *
     * @param k - index of the element that may violate heap invariant
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            //notice "<=" in outer loop condition
            //using "<" here to detect case when N is odd
            //and j = N-1 - there will be no right sibling
            if (j < N && heap[j] < heap[j + 1]) {
                j++;
            }
            //if positioned element becomes greater
            // than the largest sibling than we are done
            if (heap[k] >= heap[j]) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }
}
