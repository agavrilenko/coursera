import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by trash on 12-Jun-17.
 */
public class BinarySearchTest {

    @Test
    public void testBinarySearch() {
        int n = 5000;
        int[] in = new int[]{1, 10, 15, 23, 15, 5, 50, 150, 600, 200, 250, 333, 798, 800, 885, 900, 999, 55, 556, 885};
        for (int i = 0; i < in.length; i++) {
            for (int j = 1; j < n; j++) {
                int[] seq = generateSortedList(j);
                Assert.assertEquals(String.format("Failed on in[%s] = %s, j = %s, Array is %s ", i, in[i], j, Arrays.toString(seq)),
                        BinarySearch.linearSearch(seq, in[i]), BinarySearch.binarySearch(seq, in[i]));
            }
        }
    }

    @Test
    public void testFixedSequence_1() {
        int[] in = new int[]{0, 3, 5, 6, 7, 9, 13, 14, 15};
        Assert.assertEquals(8, BinarySearch.binarySearch(in, 15));
    }

    @Test
    public void testFixedSequence_2() {
        int[] in = new int[]{0, 3, 5};
        Assert.assertEquals(-1, BinarySearch.binarySearch(in, 1));
    }

    @Test
    public void testFixedSequence_3() {
        int[] in = new int[]{0, 3, 5};
        Assert.assertEquals(-1, BinarySearch.binarySearch(in, 10));
    }

    @Test
    public void testFixedSequence() {
        int[] in = new int[]{1, 5, 8, 12, 13};
        int[] seq = new int[]{8, 1, 23, 1, 11};
        int[] out = new int[]{2, 0, -1, 0, -1};
        for (int i = 0; i < seq.length; i++) {
            Assert.assertEquals("Failed on i = " + i, out[i], BinarySearch.binarySearch(in, seq[i]));
        }
    }

    private static int[] generateSortedList(int n) {
        List<Integer> out = new ArrayList<>();
        Random r = new Random(4425252l);
        for (int i = 0; i < n; i++) {
            if (r.nextBoolean()) {
                out.add(i);
            }
        }
        return out.stream().mapToInt(i -> i).toArray();
    }

}