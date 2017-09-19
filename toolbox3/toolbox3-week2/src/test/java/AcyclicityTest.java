import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by trash on 18-Sep-17.
 */
public class AcyclicityTest {

    @Test
    public void testAcyclic1() {
        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[0].add(1);
        adj[1] = new ArrayList<>();
        adj[1].add(2);
        adj[2] = new ArrayList<>();
        adj[2].add(0);
        adj[3] = new ArrayList<>();
        adj[3].add(1);
        Assert.assertEquals(1, Acyclicity.acyclic(adj));
    }

    @Test
    public void testAcyclic3() {
        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[0].add(1);
        adj[1].add(2);
        adj[2].add(3);
        adj[3].add(0);
        Assert.assertEquals(1, Acyclicity.acyclic(adj));
    }

    @Test
    public void testAcyclic4() {
        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[0].add(1);
        adj[1].add(2);
        adj[2].add(3);
        Assert.assertEquals(0, Acyclicity.acyclic(adj));
    }

    @Test
    public void testAcyclic2() {
        ArrayList<Integer>[] adj = new ArrayList[5];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[4] = new ArrayList<>();
        adj[0].add(1);
        adj[1].add(2);
        adj[0].add(2);
        adj[2].add(3);
        adj[0].add(3);
        adj[1].add(4);
        adj[2].add(4);
        Assert.assertEquals(0, Acyclicity.acyclic(adj));
    }

}