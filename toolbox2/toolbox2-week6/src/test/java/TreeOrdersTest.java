import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 20-Jul-17.
 */
public class TreeOrdersTest {

    @Test
    public void testTreeOrdersSimple() {

        int[][] key = new int[][]{
                new int[]{4, 2, 5, 1, 3},
                new int[]{0, 10, 20, 30, 40, 50, 60, 70, 80, 90},

        };

        int[][] left = new int[][]{
                new int[]{1, 3, -1, -1, -1},
                new int[]{7, -1, -1, 8, 3, -1, 1, 5, -1, -1},
        };

        int[][] right = new int[][]{
                new int[]{2, 4, -1, -1, -1},
                new int[]{2, -1, 6, 9, -1, -1, -1, 4, -1, -1},
        };

        int[][] inOrder = new int[][]{
                new int[]{1, 2, 3, 4, 5},
                new int[]{50, 70, 80, 30, 90, 40, 0, 20, 10, 60},
        };
        int[][] preOrder = new int[][]{
                new int[]{4, 2, 1, 3, 5},
                new int[]{0, 70, 50, 40, 30, 80, 90, 20, 60, 10},
        };
        int[][] postOrder = new int[][]{
                new int[]{1, 3, 2, 5, 4},
                new int[]{50, 80, 90, 30, 40, 70, 10, 60, 20, 0},
        };

        for (int i = 0; i < key.length; i++) {
            tree_orders.TreeOrders trees = new tree_orders.TreeOrders();
            trees.key = key[i];
            trees.left = left[i];
            trees.right = right[i];
            trees.n = trees.key.length;
            trees.build();

            Assert.assertArrayEquals("Failed on inOrder " + i, inOrder[i], trees.inOrder().stream().mapToInt(j -> j.intValue()).toArray());
            Assert.assertArrayEquals("Failed on preOrder " + i, preOrder[i], trees.preOrder().stream().mapToInt(j -> j.intValue()).toArray());
            Assert.assertArrayEquals("Failed on postOrder " + i, postOrder[i], trees.postOrder().stream().mapToInt(j -> j.intValue()).toArray());

        }


    }

}