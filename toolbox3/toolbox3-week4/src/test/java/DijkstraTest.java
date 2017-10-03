import org.junit.Assert;
import org.junit.Test;
import org.my.coursera.utils.DataUtils;

import java.util.ArrayList;

/**
 * Created by trash on 27-Sep-17.
 */
public class DijkstraTest {

    @Test
    public void test() {
        String[][] in = new String[][]{
                new String[]{"1 2 1", "4 1 2", "2 3 2", "1 3 5"},
                new String[]{"1 2 1", "2 3 1", "3 4 1", "4 5 1", "5 6 1", "1 6 4"},
                new String[]{"1 2 1", "2 3 0", "3 4 0", "4 5 1", "5 6 1", "1 6 4"},
        };
        int[] size = new int[]{
                4,
                6,
                6,
        };
        int[] out = new int[]{
                5,
                4,
                3,
        };
        int[][] points = new int[][]{
                new int[]{3, 2},
                new int[]{0, 5},
                new int[]{0, 5},
        };

        for (int i = 0; i < in.length; i++) {
            ArrayList<Integer>[] adj = new ArrayList[size[i]];
            ArrayList<Integer>[] w = new ArrayList[size[i]];
            DataUtils.buildGraph(in[i], adj, w);
            Assert.assertEquals(out[i], Dijkstra.distance(adj, w, points[i][0], points[i][1]));
        }

    }

    @Test
    public void testDijkstra_4() {
        ArrayList<Integer>[] adj = new ArrayList[6];
        ArrayList<Integer>[] w = new ArrayList[6];

        String[] toParse = new String[]{
                "1 2 2",
                "2 3 2",
                "3 4 2",
                "1 5 10",
                "5 6 1",
                "2 5 8",
                "5 2 2",
                "2 6 5",
                "3 5 1",
                "3 6 2",
                "6 4 0",

        };
        DataUtils.buildGraph(toParse, adj, w);
        Assert.assertEquals(6, Dijkstra.distance(adj, w, 0, 3));

    }


    @Test
    public void testDijkstra_1() {
        ArrayList<Integer>[] adj = new ArrayList[4];
        ArrayList<Integer>[] w = new ArrayList[4];
        String[] toParse = new String[]{
                "1 2 1",
                "4 1 2",
                "2 3 2",
                "1 3 5"
        };
        DataUtils.buildGraph(toParse, adj, w);
        Assert.assertEquals(5, Dijkstra.distance(adj, w, 3, 2));

    }

    @Test
    public void testDijkstra_3() {
        ArrayList<Integer>[] adj = new ArrayList[3];
        ArrayList<Integer>[] w = new ArrayList[3];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        w[0] = new ArrayList<>();
        w[1] = new ArrayList<>();
        w[2] = new ArrayList<>();
        adj[0].add(1);
        w[0].add(7);
        adj[0].add(2);
        w[0].add(5);
        adj[1].add(2);
        w[1].add(2);
        Assert.assertEquals(-1, Dijkstra.distance(adj, w, 2, 1));

    }

    @Test
    public void testDijkstra_2() {
        ArrayList<Integer>[] adj = new ArrayList[5];
        ArrayList<Integer>[] w = new ArrayList[5];
        w[0] = new ArrayList<>();
        w[1] = new ArrayList<>();
        w[2] = new ArrayList<>();
        w[3] = new ArrayList<>();
        w[4] = new ArrayList<>();
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[4] = new ArrayList<>();
        adj[0].add(1);
        w[0].add(4);
        adj[0].add(2);
        w[0].add(2);
        adj[1].add(2);
        w[1].add(2);
        adj[2].add(1);
        w[2].add(1);
        adj[1].add(3);
        w[1].add(2);
        adj[2].add(4);
        w[2].add(4);
        adj[4].add(3);
        w[4].add(1);
        adj[1].add(4);
        w[1].add(3);
        adj[2].add(3);
        w[2].add(4);

        Assert.assertEquals(6, Dijkstra.distance(adj, w, 0, 4));

    }

}