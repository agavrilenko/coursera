import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 08-Jul-17.
 */
public class TreeHeightTest {

    @Test
    public void testHeightTree() {
        int[][] in = new int[][]{
                new int[]{2, 3, 1, 4, 5, -1},
                new int[]{3, 6, 3, 6, 1, 1, -1},
                new int[]{4, -1, 4, 1, 1},
                new int[]{-1, 0, 4, 0, 3},
                new int[]{},
        };

        int[] out = new int[]{
                6,
                3,
                3,
                4,
                0,
        };

        for (int i = 0; i < in.length; i++) {
            Assert.assertEquals("Failed on TC " + i, out[i], new tree_height.TreeHeight(in[i]).computeHeightFast());
        }
    }

}