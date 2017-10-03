import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.my.coursera.utils.DataUtils.buildGraph;

/**
 * Created by trash on 02-Oct-17.
 */
public class NegativeCycleTest {

    @Test
    public void testNegativeCycle() {

        String[][] in = new String[][]{
                new String[]{"1 2 1", "2 3 2", "3 4 3", "4 5 4",  "5 6 5", "6 2 -15", "6 7 1"},
                new String[]{"1 2 1", "2 3 2", "3 4 3", "4 2 4", "1 5 5", "5 6 1", "6 7 2", "7 5 -4"},
                new String[]{"1 2 1", "4 1 2", "2 3 2", "3 1 -5"},
                new String[]{"1 2 -5", "4 1 2", "2 3 2", "3 1 1"},
                new String[]{"1 2 1", "4 1 2", "2 3 2", "1 3 5"},
                new String[]{"1 2 1", "2 3 1", "3 4 1", "4 5 1", "5 6 1", "1 6 4"},
                new String[]{"1 2 1", "2 3 0", "3 4 0", "4 5 1", "5 6 1", "1 6 4"},
        };
        int[] size = new int[]{
                7,
                7,
                4,
                4,
                4,
                6,
                6,
        };
        int[] out = new int[]{
                1,
                1,
                1,
                1,
                0,
                0,
                0,
        };

        for (int i = 0; i < in.length; i++) {
            ArrayList<Integer>[] adj = new ArrayList[size[i]];
            ArrayList<Integer>[] w = new ArrayList[size[i]];
            buildGraph(in[i], adj, w);
            Assert.assertEquals(String.format("Failed in TC [%s]", i), out[i], NegativeCycle.negativeCycle(adj, w));
        }
    }

}