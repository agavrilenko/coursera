import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 15-Jun-17.
 */
public class InversionsTest {
    @Test
    public void testInversionsSimple() {
        int[] in = new int[]{2, 3, 9, 2, 9};
        Assert.assertEquals(2, Inversions.getNumberOfInversionsSimple(in));
    }

    @Test
    public void testInversionsAdv() {
        int[] in = new int[]{2, 3, 9, 2, 9};
        Assert.assertEquals(2, Inversions.getNumberOfInversions(in));
    }

    @Test
    public void testInversions() {
        int[][] in = new int[][]{new int[]{2, 3, 9, 2, 9},
                new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                new int[]{9, 2, 3, 4, 5},
        };
        for (int i = 0; i < in.length; i++) {
            Assert.assertEquals(String.format("Failed on i = %s", i), Inversions.getNumberOfInversionsSimple(in[i]),
                    Inversions.getNumberOfInversions(in[i]));
        }
    }

    @Test
    public void testInversionsSimple_SortedDesc() {
        int[] in = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Assert.assertEquals(45, Inversions.getNumberOfInversionsSimple(in));
    }

    @Test
    public void testInversionsAdv_SortedDesc() {
        int[] in = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Assert.assertEquals(45, Inversions.getNumberOfInversions(in));
    }


    @Test
    public void testInversionsSimple_SortedAsc() {
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Assert.assertEquals(0, Inversions.getNumberOfInversionsSimple(in));
    }
}