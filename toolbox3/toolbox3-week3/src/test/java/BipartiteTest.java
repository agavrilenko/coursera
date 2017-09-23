import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by trash on 22-Sep-17.
 */
public class BipartiteTest {

    @Test
    public void testBipartite() {
        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[0].add(1);
        adj[3].add(0);
        adj[1].add(2);
        adj[2].add(0);
        Assert.assertEquals(0, Bipartite.bipartite(adj));
    }

    @Test
    public void testBipartite1() {
        ArrayList<Integer>[] adj = new ArrayList[5];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[4] = new ArrayList<>();
        adj[4].add(1);
        adj[3].add(1);
        adj[2].add(3);
        adj[0].add(3);
        Assert.assertEquals(1, Bipartite.bipartite(adj));
    }

    @Test
    public void testBipartite10() {
        ArrayList<Integer>[] adj = new ArrayList[10];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[4] = new ArrayList<>();
        adj[5] = new ArrayList<>();
        adj[6] = new ArrayList<>();
        adj[7] = new ArrayList<>();
        adj[8] = new ArrayList<>();
        adj[9] = new ArrayList<>();
        adj[0].add(1);
        adj[0].add(2);
        adj[0].add(3);
        adj[0].add(4);
        adj[9].add(5);
        adj[9].add(6);
        adj[9].add(7);
        adj[9].add(8);
        adj[1].add(8);
        adj[2].add(6);
        adj[3].add(6);
        adj[4].add(7);
        Assert.assertEquals(1, Bipartite.bipartite(adj));
    }

    @Test
    public void testBipartite2() {
        ArrayList<Integer>[] adj = new ArrayList[5];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[4] = new ArrayList<>();
        adj[4].add(1);
        adj[3].add(1);
        adj[2].add(3);
        adj[0].add(3);
        adj[0].add(2);
        Assert.assertEquals(0, Bipartite.bipartite(adj));
    }


}