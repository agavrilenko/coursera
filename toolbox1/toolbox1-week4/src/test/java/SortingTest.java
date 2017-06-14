import org.junit.Assert;
import org.junit.Test;
import org.my.coursera.utils.DataUtils;

import java.util.Arrays;

/**
 * Created by trash on 13-Jun-17.
 */
public class SortingTest {

    @Test
    public void testSorting3Way() {
        int n = 50;
        for (int i = 1; i < n; i++) {
            int[] in = DataUtils.generateUnsortedListWithRepeates(i * 100);
            int[] in1 = new int[in.length];
            System.arraycopy(in, 0, in1, 0, in.length);
            long start = System.currentTimeMillis();
            Sorting.randomizedQuickSort(in, 0, in.length - 1);
            long finish = System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            Sorting.randomizedQuickSort3(in1);
            long finish1 = System.currentTimeMillis() - start;
            System.out.println(String.format("Round %s Simple took %s Advanced took %s on array length %s", i, finish, finish1, in.length));

            Assert.assertArrayEquals(String.format("Failed on i = %s, in = {%s}, in1 = {%s} ", i, Arrays.toString(in), Arrays.toString(in1)), in, in1);
        }
    }

    @Test
    public void testSortingFixed() {
        int[] in = new int[]{2, 3, 9, 2, 2};
        int[] out = new int[]{2, 2, 2, 3, 9};
        Sorting.randomizedQuickSort3(in);
        Assert.assertArrayEquals(out, in);
    }

    @Test
    public void testSortingFixed_2() {
        int[] in = new int[]{2, -3, 9, -2, 2};
        int[] out = new int[]{-3, -2, 2, 2, 9};
        Sorting.randomizedQuickSort3(in);
        Assert.assertArrayEquals(String.format("in = %s, out = %s", Arrays.toString(in), Arrays.toString(out)), out, in);
    }

    @Test
    public void testSortingFixed_1() {
        int[] in = new int[]{-1908608475, -1867709936, -1446350551, -986359829, -984486090, -869741135, -769442723, -739409902, -708912816, -545417197, 141443035, 173355956, 231245047, 245090747, 623681463, 679344005, 781674701, 1273175838, 1786167784, 1837901778, 1986739485};
        int[] out = new int[]{-1908608475, -1867709936, -1446350551, -986359829, -984486090, -869741135, -769442723, -739409902, -708912816, -545417197, 141443035, 173355956, 231245047, 245090747, 623681463, 679344005, 781674701, 1273175838, 1786167784, 1837901778, 1986739485};
        Sorting.randomizedQuickSort3(in);
        Assert.assertArrayEquals(out, in);
    }

}