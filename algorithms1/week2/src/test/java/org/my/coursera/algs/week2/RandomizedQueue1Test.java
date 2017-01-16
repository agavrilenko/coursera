package org.my.coursera.algs.week2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by trash on 08-Nov-16.
 */
public class RandomizedQueue1Test {

    RandomizedQueue1<String> queue;

    @Before
    public void setup() {
        queue = new RandomizedQueue1<>();
    }

    @Test(expected = NullPointerException.class)
    public void testAddingNullItem() {
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueFromEmpty() {
        queue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testSampleFromEmpty() {
        queue.sample();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveOnEmptyIterator() {
        queue.enqueue("hi");
        Iterator iter = queue.iterator();
        iter.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyIterator() {
        Iterator iter = queue.iterator();
        iter.next();
    }

    @Test
    public void testSumOfElements() {
        RandomizedQueue1<Integer> ints = new RandomizedQueue1<>();
        ints.enqueue(1);
        ints.enqueue(2);
        ints.enqueue(3);
        ints.enqueue(5);
        ints.enqueue(4);

        Iterator<Integer> iter = ints.iterator();
        int sum = 0;
        while (iter.hasNext()) {
            sum += iter.next();
        }
        Assert.assertEquals(15, sum);
    }

    @Test
    public void testEnqueueDedueue() {
        RandomizedQueue1<Integer> ints = new RandomizedQueue1<>();

        ints.enqueue(4);
        ints.enqueue(1);
        ints.enqueue(3);
        ints.enqueue(2);
        int sum = 0;
        while (!ints.isEmpty()) {
            sum += ints.dequeue();
        }
        Assert.assertEquals(10, sum);

    }

    @Test
    public void testDedueueRandomIterators() {
        RandomizedQueue1<Integer> ints = new RandomizedQueue1<>();

        ints.enqueue(4);
        ints.enqueue(1);
        ints.enqueue(3);
        ints.enqueue(2);
        boolean notEqual = false;

        Iterator<Integer> iter1 = ints.iterator();
        Iterator<Integer> iter2 = ints.iterator();

        while (iter1.hasNext() && iter2.hasNext()) {
            Integer next2 = iter2.next();
            Integer next1 = iter1.next();
            System.out.println(String.format("First is %s second is %s", next1, next2));
            if (!next1.equals(next2)) {
                notEqual = true;
            }
        }
        Assert.assertTrue(notEqual);
    }
}