import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by trash on 19-Sep-17.
 */
public class ToposortTest {

    @Test
    public void testTopological_0() {

        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[0].add(1);
        adj[1].add(2);
        adj[2].add(3);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(0, 1, 2, 3));
        Assert.assertArrayEquals(expected.toArray(), Toposort.toposort(adj).toArray());
    }

    @Test
    public void testTopological_1() {

        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[3].add(0);
        adj[2].add(0);
        adj[0].add(1);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(3, 2, 0, 1));
        Assert.assertArrayEquals(expected.toArray(), Toposort.toposort(adj).toArray());
    }

    @Test
    public void testTopological_2() {

        ArrayList<Integer>[] adj = new ArrayList[4];
        adj[0] = new ArrayList<>();
        adj[1] = new ArrayList<>();
        adj[2] = new ArrayList<>();
        adj[3] = new ArrayList<>();
        adj[2].add(0);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(3, 2, 1, 0));
        Assert.assertArrayEquals(expected.toArray(), Toposort.toposort(adj).toArray());
    }

    @Test
    public void testTopological_3() {

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

        ArrayList<Integer> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(4, 3, 2, 1, 0));
        Assert.assertArrayEquals(expected.toArray(), Toposort.toposort(adj).toArray());
    }

}