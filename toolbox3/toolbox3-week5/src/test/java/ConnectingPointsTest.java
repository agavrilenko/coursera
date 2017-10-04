import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 03-Oct-17.
 */
public class ConnectingPointsTest {
    @Test
    public void testConnectedPoints() {
        int[][] x = new int[][]{
                new int[]{0, 0, 1, 1},
                new int[]{0, 0, 1, 3, 3},
        };
        int[][] y = new int[][]{
                new int[]{0, 1, 0, 1},
                new int[]{0, 2, 1, 0, 2},

        };
        double[] out = new double[]{
                3.000000000,
                7.064495102,
        };

        for (int i = 0; i < out.length; i++) {
            Assert.assertEquals(String.format("Failed on TC[%s] ", i), out[i],
                    ConnectingPoints.minimumDistance(x[i], y[i]), 0.0000001);
        }

    }

}