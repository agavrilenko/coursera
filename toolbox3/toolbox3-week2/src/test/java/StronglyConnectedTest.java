import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by trash on 19-Sep-17.
 */
public class StronglyConnectedTest {

    @Test
    public void testStronglyConnected() {
        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[0].add(1);
        adj[3].add(0);
        adj[1].add(2);
        adj[2].add(0);

        Assert.assertEquals(2, StronglyConnected.numberOfStronglyConnectedComponents(adj));

    }

    @Test
    public void testStronglyConnected_1() {
        ArrayList<Integer>[] adj = new ArrayList[5];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[4] = new ArrayList<>();
        adj[1].add(0);
        adj[2].add(1);
        adj[2].add(0);
        adj[3].add(2);
        adj[3].add(0);
        adj[4].add(1);
        adj[4].add(2);

        Assert.assertEquals(5, StronglyConnected.numberOfStronglyConnectedComponents(adj));

    }

}