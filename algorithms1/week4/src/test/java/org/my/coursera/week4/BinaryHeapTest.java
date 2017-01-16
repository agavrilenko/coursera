package org.my.coursera.week4;

import org.junit.Assert;
import org.junit.Test;
import org.my.coursera.utils.DataUtils;

/**
 * Created by trash on 22-Nov-16.
 */
public class BinaryHeapTest {
    @Test
    public void testHeap_Simple() {

        BinaryHeap heap = new BinaryHeap();
        heap.add(1);
        heap.add(2);

        Assert.assertEquals(2, heap.size());
        Assert.assertEquals(2, heap.removeMax());
        Assert.assertEquals(1, heap.removeMax());
    }

    @Test
    public void testHeap_Simple2() {

        BinaryHeap heap = new BinaryHeap();
        heap.add(4);
        heap.add(1);
        heap.add(3);
        heap.add(2);

        Assert.assertEquals(4, heap.size());
        Assert.assertEquals(4, heap.removeMax());
        Assert.assertEquals(3, heap.removeMax());
        Assert.assertEquals(2, heap.removeMax());
        Assert.assertEquals(1, heap.removeMax());
    }

    @Test
    public void testHeap() {
        int num = 1000;
        BinaryHeap heap = new BinaryHeap();
        for (int i = 1; i < num + 1; i++) {
            heap.add(i);
        }

        Assert.assertEquals(num, heap.size());
        for (int i = num; i > 0; i--) {
            Assert.assertEquals(i, heap.removeMax());
        }
    }

    @Test
    public void testHeapWithRandomImput() {
        int num = 1000000;
        BinaryHeap heap = new BinaryHeap();
        Integer[] array = new Integer[num];
        for (int i = 0; i < num; i++) {
            array[i] = i;
        }
        DataUtils.shuffle(array);
        for (int i = 0; i < num; i++) {
            heap.add(array[i]);
        }

        Assert.assertEquals(num, heap.size());
        for (int i = num - 1; i >= 0; i--) {
            Assert.assertEquals(i, heap.removeMax());
        }
    }

}