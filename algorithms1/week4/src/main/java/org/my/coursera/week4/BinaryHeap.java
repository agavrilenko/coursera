package org.my.coursera.week4;

/**
 * Created by trash on 22-Nov-16.
 */
public class BinaryHeap {
    private Comparable[] store;
    private int size = 0;

    public BinaryHeap(int initCapacity) {
        store = new Comparable[initCapacity];
    }

    public BinaryHeap() {
        store = new Comparable[16];
    }

    public void add(Comparable elem) {
        if (elem == null) {
            throw new NullPointerException("I don't accept nulls");
        }
        if (size == 0) {
            store[++size] = elem;
            return;
        }
        store[++size] = elem;
        if (size >= store.length - 1) {
            resize(2 * store.length);
        }
        swim(size);
    }

    public Comparable removeMax() {
        if (size <= 0) {
            return null;
        }
        Comparable max = store[1];
        store[1] = store[size];
        store[size--] = null;
        sink(1);
        if (size > 0 && size == store.length / 4) {
            resize(store.length / 2);
        }
        return max;
    }

    private void swim(int index) {

        if (index <= 1) {
            return;
        }
        int parentInd = index / 2;

        while (parentInd >= 1) {
            if (store[index].compareTo(store[parentInd]) > 0) {
                exchange(index, parentInd);
            }
            index = parentInd;
            parentInd = parentInd / 2;
        }
    }

    private void exchange(int i, int j) {
        Comparable tmp = store[j];
        store[j] = store[i];
        store[i] = tmp;
    }

    private void sink(int index) {

        int left = index * 2;
        int right = index * 2 + 1;

        boolean hasLeft = left <= size;
        boolean hasRight = right <= size;

        while (hasLeft || hasRight) {
            left = index * 2;
            right = index * 2 + 1;
            hasLeft = left <= size;
            hasRight = right <= size;
            if (hasLeft) {
                int cmpLeft = store[index].compareTo(store[left]);
                if (hasRight) {
                    int cmpLeftRight = store[left].compareTo(store[right]);
                    if (cmpLeftRight < 0) {
                        int cmpRight = store[index].compareTo(store[right]);
                        if (cmpRight < 0) {
                            exchange(index, right);
                            index = right;
                            continue;
                        }
                    }
                }
                if (cmpLeft < 0) {
                    exchange(index, left);
                    index = left;
                    continue;
                }
            }
            break;
        }
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Comparable[] copy = new Comparable[capacity];

        for (int i = 1; i < size + 1; i++) {
            copy[i] = store[i];
        }
        store = copy;
    }
}
