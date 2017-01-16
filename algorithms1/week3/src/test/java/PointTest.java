import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by trash on 19-Nov-16.
 */
public class PointTest {

    @Test
    public void testPointsPQR() {

        Point p = new Point(8, 1);
        Point q = new Point(5, 4);
        Point r = new Point(8, 1);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(r), 0.0);
        Assert.assertEquals(1, p.slopeOrder().compare(q, r));
        Assert.assertEquals(-1, p.slopeTo(q), 0.0);

    }

    @Test
    public void testPointsPQR_1() {

        Point p = new Point(447, 486);
        Point q = new Point(447, 232);
        Point r = new Point(145, 107);
        Assert.assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(q), 0.0);
        Assert.assertEquals(1.2549668874172186, p.slopeTo(r), 0.0);
        Assert.assertEquals(1, p.slopeOrder().compare(q, r));

    }

    @Test
    public void testPointsPQ() {
        Point p = new Point(4, 0);
        Point q = new Point(4, 0);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(q), 0.0);
    }

    @Test
    public void testPointsPQ_1() {
        Point p = new Point(6, 9);
        Point q = new Point(6, 5);
        Assert.assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(q), 0.0);
    }

    @Test
    public void testPointsPQ_() {
        Point p = new Point(3, 7);
        Point q = new Point(3, 8);
        Assert.assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(q), 0.0);
    }

    @Test
    public void test1(){
        double a=-0.0, b=+0.0;
        Double x = a;
        Double y = b;
        Assert.assertTrue(a==b);
        Assert.assertFalse(x.equals(y));
        Assert.assertTrue(x.compareTo(y) < 0);

    }
@Test
    public void test2(){
        double a=-0.0/0.0, b=+0.0/0.0;
        Double x = a;
        Double y = b;
        Assert.assertTrue(a!=b);
        Assert.assertFalse(!x.equals(y));

    }

}