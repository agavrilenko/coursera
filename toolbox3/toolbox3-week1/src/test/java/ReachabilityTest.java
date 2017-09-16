import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by trash on 15-Sep-17.
 */
public class ReachabilityTest {

    @Test
    public void testReachability1() {

        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[0].add(1);
        adj[0].add(3);
        adj[1] = new ArrayList<>();
        adj[1].add(0);
        adj[1].add(2);
        adj[2] = new ArrayList<>();
        adj[2].add(1);
        adj[2].add(3);
        adj[3] = new ArrayList<>();
        adj[3].add(1);
        adj[3].add(3);
        Assert.assertEquals(1, Reachability.reach(adj, 0, 3));
    }

    @Test
    public void testReachability2() {

        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[0].add(1);
        adj[1] = new ArrayList<>();
        adj[1].add(0);
        adj[1].add(2);
        adj[2] = new ArrayList<>();
        adj[2].add(1);
        adj[3] = new ArrayList<>();
        Assert.assertEquals(0, Reachability.reach(adj, 0, 3));
    }

}