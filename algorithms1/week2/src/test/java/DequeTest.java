import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by trash on 06-Nov-16.
 */
public class DequeTest {

    @Test(expected = NullPointerException.class)
    public void testAddFirstNull() {
        Deque<String> dec = new Deque<>();
        dec.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddLastNull() {
        Deque<String> dec = new Deque<>();
        dec.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstFromEmpty() {
        Deque<String> dec = new Deque<>();
        dec.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastFromEmpty() {
        Deque<String> dec = new Deque<>();
        dec.removeLast();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCallRemoveOnIter() {
        Deque<String> dec = new Deque<>();
        Iterator iter = dec.iterator();
        iter.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testCallNextOnEmpty() {
        Deque<String> dec = new Deque<>();
        dec.addFirst("Hi");
        Iterator iter = dec.iterator();
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("Hi", iter.next());
        iter.next();
    }

    @Test
    public void testQueueValues_RemoveFirst() {
        Deque<String> dec = new Deque<>();
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
        Deque<String> dec = new Deque<>();
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
        Deque<String> dec = new Deque<>();
        dec.addFirst("1");
        dec.addLast("4");
        dec.addFirst("2");

        Iterator<String> iter = dec.iterator();

        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("2", iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("1", iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("4", iter.next());
        Assert.assertFalse(iter.hasNext());
        iter.next();

    }

    @Test(expected = NoSuchElementException.class)
    public void testQueueValues_IteratorLast() {
        Deque<String> dec = new Deque<>();
        dec.addLast("1");
        dec.addLast("4");
        dec.addLast("2");

        Iterator<String> iter = dec.iterator();

        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("1", iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("4", iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("2", iter.next());
        Assert.assertFalse(iter.hasNext());
        iter.next();

    }

    @Test
    public void testQueueValues_FirstLast() {
        Deque<String> dec = new Deque<>();
        dec.addLast("1");
        Assert.assertEquals("1",dec.removeFirst());
        dec.addLast("4");
        Assert.assertEquals("4",dec.removeFirst());
        dec.addLast("2");
        Assert.assertEquals("2",dec.removeFirst());

    }

    @Test
    public void testQueueValues_LastFirst() {
        Deque<String> dec = new Deque<>();
        dec.addFirst("1");
        Assert.assertEquals("1",dec.removeLast());
        dec.addFirst("4");
        Assert.assertEquals("4",dec.removeLast());
        dec.addFirst("2");
        Assert.assertEquals("2",dec.removeLast());

    }

    @Test
    public void testIntermixedCalls(){
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.removeLast();//    ==> 1
        deque.addFirst(3);
        deque.addFirst(4);

        Iterator it = deque.iterator();
        Assert.assertEquals(4, it.next());
    }
}