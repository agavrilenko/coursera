package org.my.coursera.algs.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by trash on 06-Nov-16.
 * A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the back of the data structure. Create a generic data type
 * Deque that implements the following API:
 * <p>
 * Corner cases. Throw a java.lang.NullPointerException if the client attempts to add a null item;
 * throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
 * throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
 * throw a java.util.NoSuchElementException if the client calls the next() method in the
 * iterator and there are no more items to return.
 * <p>
 * Performance requirements.   Your deque implementation must support each deque operation in
 * constant worst-case time. A deque containing n items must use at most 48n + 192 bytes of memory.
 * and use space proportional to the number of items currently in the deque. Additionally, your iterator
 * implementation must support each operation (including construction) in constant worst-case time.
 */
public class Deque1<Item> implements Iterable<Item> {
    private int cnt;
    private Node tail = null;
    private Node head = null;

    // construct an empty deque
    public Deque1() {
        tail = head = null;
        cnt = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return cnt == 0;
    }

    // return the number of items on the deque
    public int size() {
        return cnt;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Item is null");
        }
        Node node = new Node();
        node.item = item;
        node.next = null;
        node.prev = head;
        if (head == null) {
            head = tail = node;
        } else {
            head.next = node;
        }

        head = node;

        cnt++;

    }

    // add the item to the end
    public void addLast(Item item) {

        if (item == null) {
            throw new NullPointerException("Item is null");
        }
        Node node = new Node();
        node.item = item;
        node.next = tail;
        node.prev = null;
        if (tail == null) {
            head = tail = node;
        } else {
            tail.prev = node;
        }
        tail = node;

        cnt++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (cnt <= 0) {
            throw new NoSuchElementException("Empty");
        }
        Item value = head.item;
        if (head.prev != null) {
            head = head.prev;
            head.next = null;
        } else {
            head = tail = null;
        }
        cnt--;
        return value;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (cnt <= 0) {
            throw new NoSuchElementException("Empty");
        }
        Item value = tail.item;
        if (tail.next != null) {
            tail = tail.next;
            tail.prev = null;
        } else {
            head = tail = null;
        }
        cnt--;
        return value;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iter(tail);
    }

    // unit testing
    public static void main(String[] args) {
        throw new UnsupportedOperationException();
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private class Iter implements Iterator {
        private Node last;

        private Iter(Node item) {
            this.last = item;
        }

        @Override
        public boolean hasNext() {
            return last != null;
        }

        @Override
        public Object next() {
            if (last == null) {
                throw new NoSuchElementException("Oops. No more elements");
            }
            Item item = last.item;
            last = last.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }
    }

}
