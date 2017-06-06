import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 05-Jun-17.
 */
public class DotProductTest {
    @Test
    public void testDotProduct() {

        int[][] profit = new int[][]{new int[]{23}, new int[]{1, 3, -5}, new int[]{100000}};
        int[][] ads = new int[][]{new int[]{39}, new int[]{-2, 4, 1}, new int[]{100000}};
        long[] out = new long[]{897, 23, 10000000000l};
        for (int i = 0; i < out.length; i++) {

            Assert.assertEquals(out[i], DotProduct.maxDotProduct(profit[i], ads[i]));
        }

    }

}