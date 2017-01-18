import edu.princeton.cs.algs4.Digraph;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 16-Jan-17.
 */
public class SAPTest {

    @Test
    public void testSimpleLength() {
        Digraph g = new Digraph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        SAP sap = new SAP(g);
        Assert.assertEquals(3, sap.length(0, 3));
        Assert.assertEquals(2, sap.length(0, 2));
        Assert.assertEquals(-1, sap.length(3, 0));
    }

    @Test
    public void testSimpleLength1() {
        Digraph g = new Digraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        SAP sap = new SAP(g);
        Assert.assertEquals(2, sap.length(0, 3));
        Assert.assertEquals(1, sap.length(0, 2));
        Assert.assertEquals(-1, sap.length(3, 0));
    }

    @Test
    public void testSimpleSAP_Five() {
        Digraph g = new Digraph(6);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 0);
        SAP sap = new SAP(g);
        Assert.assertEquals(5, sap.length(1, 0));
        Assert.assertEquals(5, sap.ancestor(1, 5));
    }

    @Test
    public void testSimpleSAP() {
        Digraph g = new Digraph(6);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 0);
        SAP sap = new SAP(g);
        Assert.assertEquals(1, sap.length(1, 0));
        Assert.assertEquals(0, sap.ancestor(1, 5));
    }

}