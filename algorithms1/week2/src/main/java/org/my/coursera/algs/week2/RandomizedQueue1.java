package org.my.coursera.algs.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by trash on 06-Nov-16.
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from
 * items in the data structure. Create a generic data type RandomizedQueue that implements the following API:
 * <p>
 * Corner cases. The order of two or more iterators to the same randomized queue must be mutually independent;
 * each iterator must maintain its own random order.
 * Throw a java.lang.NullPointerException if the client attempts to add a null item;
 * throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item
 * from an empty randomized queue;
 * throw a java.lang.UnsupportedOperationException if the client calls the remove()
 * method in the iterator;
 * throw a java.util.NoSuchElementException if the client calls the next() method in the
 * iterator and there are no more items to return.
 * <p>
 * <p>
 * Performance requirements. Your randomized queue implementation must support each randomized queue operation
 * (besides creating an iterator) in constant amortized time. That is, any sequence of m randomized queue operations
 * (starting from an empty queue) should take at most cm steps in the worst case, for some constant c. A randomized
 * queue containing n items must use at most 48n + 192 bytes of memory. Additionally, your iterator implementation must
 * support operations next() and hasNext() in constant worst-case time; and construction in linear time; you may
 * (and will need to) use a linear amount of extra memory per iterator.
 */
public class RandomizedQueue1<Item> implements Iterable<Item> {

    private Item[] store;
    private int cnt;

    // construct an empty randomized queue
    public RandomizedQueue1() {
        store = (Item[]) new Object[1];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return cnt == 0;
    }

    // return the number of items on the queue
    public int size() {
        return cnt;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Null is not accepted");
        }
        if (cnt >= store.length) {
            resize(2 * store.length);
        }
        store[cnt++] = item;

    }

    // remove and return a random item
    public Item dequeue() {
        if (cnt <= 0) {
            throw new NoSuchElementException("Oops. Empty");
        }
        int index = StdRandom.uniform(cnt);
        Item item = store[index];
        store[index] = store[cnt - 1];
        store[cnt - 1] = null;
        cnt--;
        if (cnt > 0 && cnt == store.length / 4) {
            resize(store.length / 2);
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (cnt <= 0) {
            throw new NoSuchElementException("Oops. Empty");
        }
        return store[StdRandom.uniform(cnt)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iter(store, cnt);
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < cnt; i++) {
            copy[i] = store[i];
        }
        store = copy;
    }

    // unit testing
    public static void main(String[] args) {
        throw new UnsupportedOperationException();
    }

    private class Iter implements Iterator<Item> {
        int anInt;
        private Item[] items;
        private int[] aux;

        private Iter(Item[] store, int cnt) {
            this.anInt = new Integer(cnt);
            this.items = store;
            aux = new int[anInt];
            for (int i = 0; i < anInt; i++) {
                aux[i] = i;
            }
        }

        @Override
        public boolean hasNext() {
            return anInt > 0;
        }

        @Override
        public Item next() {
            if (anInt == 0) {
                throw new NoSuchElementException("OOps o_0. Empty");
            }
            int index = StdRandom.uniform(anInt);
            Item item = items[aux[index]];
            aux[index] = aux[anInt - 1];
            aux[anInt - 1] = 0;
            anInt--;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
