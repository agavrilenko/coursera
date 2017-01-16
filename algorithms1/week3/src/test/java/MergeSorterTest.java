import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by trash on 14-Nov-16.
 */
public class MergeSorterTest {
    @Test
    public void testSimpleSort() {

        Integer[] toSort = new Integer[]{4, 2, 7, 8, 1};
        Integer[] afterSort = new Integer[]{1, 2, 4, 7, 8};
        new MergeSorter().sort(toSort);
        Assert.assertArrayEquals(afterSort, toSort);
    }

    @Test
    public void testTenSort() {

        Integer[] toSort = new Integer[]{4, 2, 7, 8, 1, 3, 6, 9, 5, 10};
        shuffle(toSort);
        Integer[] afterSort = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        new MergeSorter().sort(toSort);
        Assert.assertArrayEquals(afterSort, toSort);
    }

    @Test
    public void testFourSort() {

        Integer[] toSort = new Integer[]{4, 2, 8, 1};
        Integer[] afterSort = new Integer[]{1, 2, 4, 8};
        new MergeSorter().sort(toSort);
        Assert.assertArrayEquals(afterSort, toSort);
    }

    @Test
    public void testOneSort() {

        Integer[] toSort = new Integer[]{2, 1};
        Integer[] afterSort = new Integer[]{1, 2};
        new MergeSorter().sort(toSort);
        Assert.assertArrayEquals(afterSort, toSort);
    }

    @Test
    public void testThreeSort() {

        Integer[] toSort = new Integer[]{2, 1, 3};
        Integer[] afterSort = new Integer[]{1, 2, 3};
        new MergeSorter().sort(toSort);
        Assert.assertArrayEquals(afterSort, toSort);
    }

    @Test
    public void testBigSort() throws InterruptedException {

        int length = 500000000;
        int[] toSort = new int[length];
        int[] afterSort = new int[length];
        for (int i = 0; i < length; i++) {
            toSort[i] = i;
            afterSort[i] = i;
        }
        shuffle(toSort);
        long now = System.currentTimeMillis();
        new MergeSorter().sort(toSort);
        long delta = System.currentTimeMillis() - now;
        Assert.assertArrayEquals(afterSort, toSort);
        System.out.println("Took " + (delta / 1000.0));
        /*100000000
        count 2532916619
        Took 74.293*/

        /*500000000
        count 13821380820
        Took 412.456*/
    }

    private void shuffle(Integer[] toSort) {
        Random rnd = new Random(1341234314);
        for (int i = toSort.length; i > 1; i--) {
            int index = rnd.nextInt(i);
            Integer tmp = toSort[i - 1];
            toSort[i - 1] = toSort[index];
            toSort[index] = tmp;

        }
    }

    private void shuffle(int[] toSort) {
        Random rnd = new Random(1341234314);
        for (int i = toSort.length; i > 1; i--) {
            int index = rnd.nextInt(i);
            Integer tmp = toSort[i - 1];
            toSort[i - 1] = toSort[index];
            toSort[index] = tmp;

        }
    }

}