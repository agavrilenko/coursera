import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by trash on 30-Nov-16.
 */
public class KdTreeTest {

    @Test
    public void testAdd_Simple() {

        KdTree tree = new KdTree();
        tree.insert(new Point2D(1, 2));
        Assert.assertEquals(1, tree.size());
        Assert.assertTrue(!tree.isEmpty());
    }

    @Test
    public void testContains_Simple() {

        KdTree tree = new KdTree();
        tree.insert(new Point2D(1, 2));
        Assert.assertEquals(1, tree.size());
        Assert.assertTrue(tree.contains(new Point2D(1, 2)));
    }

    @Test
    public void testContins_TreeNodes() {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(4, 5));
        tree.insert(new Point2D(1, 2));
        tree.insert(new Point2D(2, 3));
        tree.insert(new Point2D(6, 7));
        tree.insert(new Point2D(4.5, 2.5));
        Assert.assertEquals(5, tree.size());
        Assert.assertTrue(tree.contains(new Point2D(4, 5)));
        Assert.assertTrue(tree.contains(new Point2D(6, 7)));
        Assert.assertTrue(tree.contains(new Point2D(4.5, 2.5)));

    }

    @Test
    public void testRange_Simple() {
        KdTree tree = new KdTree();
        Point2D p = new Point2D(1, 1);
        tree.insert(p);
        Iterator<Point2D> range = tree.range(new RectHV(0, 0, 2, 2)).iterator();
        Assert.assertTrue(range.hasNext());
        Assert.assertEquals(p, range.next());

    }

    @Test
    public void testRange_FivePoints() {
        KdTree tree = new KdTree();
        Point2D p = new Point2D(2.5, 2.5);
        Point2D p1 = new Point2D(2.8, 2.6);
        tree.insert(new Point2D(4, 5));
        tree.insert(p1);
        tree.insert(new Point2D(5, 5));
        tree.insert(new Point2D(2.75, 5));
        tree.insert(p);
        tree.insert(new Point2D(1, 1));
        ArrayList<Point2D> range = (ArrayList<Point2D>) tree.range(new RectHV(2, 2, 3, 3));
        Assert.assertEquals(2, range.size());
        Assert.assertTrue(range.contains(p));
        Assert.assertTrue(range.contains(p1));
    }

    @Test
    public void testRange_MultiplePoints() {
        KdTree tree = new KdTree();
        Point2D p = new Point2D(2.5, 2.5);
        Point2D p1 = new Point2D(2.8, 2.6);
        int side = 6;
        int lo = 1;
        for (int i = lo; i <= side; i++) {
            for (int j = lo; j <= side; j++) {
                tree.insert(new Point2D(j, i));
            }
        }
        tree.insert(p1);
        tree.insert(p);
        ArrayList<Point2D> range = (ArrayList<Point2D>) tree.range(new RectHV(1.5, 1.5, 4.5, 5.5));
        Assert.assertEquals(38, tree.size());
        Assert.assertEquals(14, range.size());
        Assert.assertTrue(range.contains(p));
        Assert.assertTrue(range.contains(p1));
    }

    @Test
    public void testRange_MultiplePoints_1() {
        KdTree tree = new KdTree();
        int side = 4;
        int lo = 1;
        for (int i = lo; i <= side; i++) {
            for (int j = lo; j <= side; j++) {
                tree.insert(new Point2D(j, i));
            }
        }
        ArrayList<Point2D> range = (ArrayList<Point2D>) tree.range(new RectHV(1.5, 1.5, 2.5, 2.5));
        Assert.assertEquals(16, tree.size());
        Assert.assertEquals(1, range.size());
    }

    @Test
    public void testRange_100Points() {
        KdTree tree = new KdTree();
        Point2D p = new Point2D(7.5, 5.5);
        Point2D p1 = new Point2D(6.8, 6.6);
        int side = 10;
        int lo = 1;
        for (int i = lo; i <= side; i++) {
            for (int j = lo; j <= side; j++) {
                tree.insert(new Point2D(j, i));
            }
        }
        tree.insert(p1);
        tree.insert(p);
        ArrayList<Point2D> range = (ArrayList<Point2D>) tree.range(new RectHV(5.5, 1.5, 9.5, 8.5));
        Assert.assertEquals(102, tree.size());
        Assert.assertEquals(30, range.size());
        Assert.assertTrue(range.contains(p));
        Assert.assertTrue(range.contains(p1));
    }

    @Test
    public void testNearest_Simple() {
        KdTree tree = new KdTree();
        Point2D p = new Point2D(1, 1);
        tree.insert(p);
        Point2D nearest = tree.nearest(new Point2D(2, 1));
        Assert.assertEquals(p, nearest);
    }

    @Test
    public void testNearest_TwoDots() {
        KdTree tree = new KdTree();
        Point2D p = new Point2D(1, 1);
        Point2D p5 = new Point2D(5, 5);
        tree.insert(p5);
        tree.insert(p);
        tree.insert(new Point2D(1, 2));
        tree.insert(new Point2D(2, 1));
        tree.insert(new Point2D(2, 2));
        Point2D nearest = tree.nearest(new Point2D(5.1, 5.1));
        Assert.assertEquals(p5, nearest);
    }

    @Test
    public void testNearest_1MPoints() {
        KdTree tree = new KdTree();
        int side = 1000;
        int lo = 1;
        tree.insert(new Point2D(500, 500));
        for (int i = lo; i <= side; i++) {
            for (int j = lo; j <= side; j++) {
                tree.insert(new Point2D(j, i));
            }
        }
        Point2D nearest = tree.nearest(new Point2D(5.1, 5.1));
        Assert.assertEquals(new Point2D(5, 5), nearest);
        nearest = tree.nearest(new Point2D(1.1, 6.1));
        Assert.assertEquals(new Point2D(1, 6), nearest);
        nearest = tree.nearest(new Point2D(6.1, 6.1));
        Assert.assertEquals(new Point2D(6, 6), nearest);
        nearest = tree.nearest(new Point2D(100.1, 150.1));
        Assert.assertEquals(new Point2D(100, 150), nearest);
        nearest = tree.nearest(new Point2D(900.1, 750.1));
        Assert.assertEquals(new Point2D(900, 750), nearest);
        ArrayList<Point2D> range = (ArrayList<Point2D>) tree.range(new RectHV(500.5, 550.5, 700.5, 600.5));
        Assert.assertEquals(10000, range.size());
    }

    @Test
    public void testDups() {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(1, 1));
        tree.insert(new Point2D(1, 1));
        Assert.assertEquals(1, tree.size());

    }


}