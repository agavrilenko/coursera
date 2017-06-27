import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 24-Jun-17.
 */
public class KnapsackTest {

    @Test
    public void testKnapsack() {
        int[] W = new int[]{10, 5, 10};
        int[][] w = new int[][]{
                new int[]{1, 4, 8},
                new int[]{2, 3},
                new int[]{6, 3, 4, 2},
        };
        int[] out = new int[]{9, 5, 10};
        for (int i = 0; i < W.length; i++) {
            Assert.assertEquals("Failed on " + i, out[i], Knapsack.optimalWeight(W[i], w[i]));

        }
    }

}