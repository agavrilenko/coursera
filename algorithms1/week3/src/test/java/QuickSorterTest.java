import org.junit.Assert;
import org.junit.Test;
import org.my.coursera.utils.DataUtils;

/**
 * Created by trash on 15-Nov-16.
 */
public class QuickSorterTest {
    @Test
    public void testFourSort() {

        Integer[] toSort = new Integer[]{4, 2, 8, 1};
        Integer[] afterSort = new Integer[]{1, 2, 4, 8};
        sortAndAssert(toSort, afterSort);
    }

    @Test
    public void testOneSort() {

        Integer[] toSort = new Integer[]{2, 1};
        Integer[] afterSort = new Integer[]{1, 2};
        sortAndAssert(toSort, afterSort);
    }

    @Test
    public void testThreeSort() {

        Integer[] toSort = new Integer[]{2, 1, 3};
        Integer[] afterSort = new Integer[]{1, 2, 3};
        sortAndAssert(toSort, afterSort);
    }

    @Test
    public void testTenSort() {

        Integer[] toSort = new Integer[]{4, 2, 7, 8, 1, 3, 6, 9, 5, 10};
        Integer[] afterSort = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        sortAndAssert(toSort, afterSort);
    }

    @Test
    public void testTenSort_WithComparator() {

        Integer[] toSort = new Integer[]{4, 2, 7, 8, 1, 3, 6, 9, 5, 10};
        Integer[] afterSort = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long now = System.currentTimeMillis();

        new QuickSorter().sort(toSort, (x, y) -> ((Integer) x).compareTo((Integer) y));
        long delta = System.currentTimeMillis() - now;
        Assert.assertArrayEquals(afterSort, toSort);
        System.out.println("Took " + (delta / 1000.0) + " for " + toSort.length);
    }

    @Test
    public void testBigSort() throws InterruptedException {

        int length = 500000;
        Integer[] toSort = new Integer[2 * length];
        Integer[] afterSort = new Integer[2 * length];
        for (int i = 0; i < length; i++) {
            toSort[2 * i] = i;
            toSort[2 * i + 1] = i;
            afterSort[2 * i] = i;
            afterSort[2 * i + 1] = i;
        }
        DataUtils.shuffle(toSort);
        sortAndAssert(toSort, afterSort);
        /*100000000
        count 2532916619
        Took 74.293*/

        /*500000000
        count 13821380820
        Took 412.456*/
    }


    private void sortAndAssert(Integer[] toSort, Integer[] afterSort) {
        long now = System.currentTimeMillis();
        new QuickSorter().sort(toSort);
        long delta = System.currentTimeMillis() - now;
        Assert.assertArrayEquals(afterSort, toSort);
        System.out.println("Took " + (delta / 1000.0) + " for " + toSort.length);
    }


}