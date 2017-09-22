import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by trash on 21-Sep-17.
 */
public class BFSTest {
    @Test
    public void testBfs1() {
        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[0].add(1);
        adj[3].add(0);
        adj[1].add(2);
        adj[2].add(0);
        Assert.assertEquals(2, BFS.distance(adj, 1, 3));

    }

    @Test
    public void testBfs2() {
        ArrayList<Integer>[] adj = new ArrayList[5];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[4] = new ArrayList<>();
        adj[4].add(1);
        adj[0].add(2);
        adj[2].add(3);
        adj[0].add(3);


        Assert.assertEquals(-1, BFS.distance(adj, 2, 4));

    }

}