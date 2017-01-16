package org.my.coursera.algs.week2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by trash on 06-Nov-16.
 */
public class Deque1Test {

    @Test(expected = NullPointerException.class)
    public void testAddFirstNull() {
        Deque1<String> dec = new Deque1<>();
        dec.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddLastNull() {
        Deque1<String> dec = new Deque1<>();
        dec.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstFromEmpty() {
        Deque1<String> dec = new Deque1<>();
        dec.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastFromEmpty() {
        Deque1<String> dec = new Deque1<>();
        dec.removeLast();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCallRemoveOnIter() {
        Deque1<String> dec = new Deque1<>();
        Iterator iter = dec.iterator();
        iter.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testCallNextOnEmpty() {
        Deque1<String> dec = new Deque1<>();
        dec.addFirst("Hi");
        Iterator iter = dec.iterator();
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("Hi", iter.next());
        iter.next();
    }

    @Test
    public void testQueueValues_RemoveFirst() {
        Deque1<String> dec = new Deque1<>();
        dec.addFirst("1");
        dec.addLast("4");
        dec.addFirst("2");
        dec.addLast("5");
        dec.addFirst("3");
        dec.addLast("6");
        Assert.assertEquals("3", dec.removeFirst());
        Assert.assertEquals("2", dec.removeFirst());
        Assert.assertEquals("1", dec.removeFirst());
        Assert.assertEquals("4", dec.removeFirst());
        Assert.assertEquals("5", dec.removeFirst());
        Assert.assertEquals("6", dec.removeFirst());

    }

    @Test
    public void testQueueValues_RemoveLast() {
        Deque1<String> dec = new Deque1<>();
        dec.addFirst("1");
        dec.addLast("4");
        dec.addFirst("2");
        dec.addLast("5");
        dec.addFirst("3");
        dec.addLast("6");
        Assert.assertEquals("6", dec.removeLast());
        Assert.assertEquals("5", dec.removeLast());
        Assert.assertEquals("4", dec.removeLast());
        Assert.assertEquals("1", dec.removeLast());
        Assert.assertEquals("2", dec.removeLast());
        Assert.assertEquals("3", dec.removeLast());

    }

    @Test(expected = NoSuchElementException.class)
    public void testQueueValues_Iterator() {
        Deque1<String> dec = new Deque1<>();
        dec.addFirst("1");
        dec.addLast("4");
        dec.addFirst("2");

        Iterator<String> iter = dec.iterator();

        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("4", iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("1", iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("2", iter.next());
        Assert.assertFalse(iter.hasNext());
        iter.next();

    }

    @Test(expected = NoSuchElementException.class)
    public void testQueueValues_IteratorLast() {
        Deque1<String> dec = new Deque1<>();
        dec.addLast("1");
        dec.addLast("4");
        dec.addLast("2");

        Iterator<String> iter = dec.iterator();

        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("2", iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("4", iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("1", iter.next());
        Assert.assertFalse(iter.hasNext());
        iter.next();

    }

    @Test
    public void testQueueValues_FirstLast() {
        Deque1<String> dec = new Deque1<>();
        dec.addLast("1");
        Assert.assertEquals("1",dec.removeFirst());
        dec.addLast("4");
        Assert.assertEquals("4",dec.removeFirst());
        dec.addLast("2");
        Assert.assertEquals("2",dec.removeFirst());

    }

    @Test
    public void testQueueValues_LastFirst() {
        Deque1<String> dec = new Deque1<>();
        dec.addFirst("1");
        Assert.assertEquals("1",dec.removeLast());
        dec.addFirst("4");
        Assert.assertEquals("4",dec.removeLast());
        dec.addFirst("2");
        Assert.assertEquals("2",dec.removeLast());

    }
}