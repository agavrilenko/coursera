import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by trash on 18-Nov-16.
 */
public class FastCollinearPointsTest {

    @Test
    public void testCollinearPoints_Simple() {

        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4),
                new Point(5, 5),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(1, 1), new Point(5, 5));
        System.out.println(expected);
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(1, lineSegments.length);
        Assert.assertEquals(expected.toString(), lineSegments[0].toString());


    }

    @Test
    public void testCollinearPoints_SimpleMixed() {

        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(4, 4),
                new Point(3, 3),
                new Point(2, 2),
                new Point(5, 5),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(1, 1), new Point(5, 5));
        System.out.println(expected);
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(1, lineSegments.length);
        Assert.assertEquals(expected.toString(), lineSegments[0].toString());


    }

    @Test
    public void testCollinearPoints_Mixed() {

        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(4, 4),
                new Point(4, 3),
                new Point(3, 3),
                new Point(2, 2),
                new Point(5, 5),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(1, 1), new Point(5, 5));
        System.out.println(expected);
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(1, lineSegments.length);
        Assert.assertEquals(expected.toString(), lineSegments[0].toString());


    }

    @Test
    public void testCollinearPoints_Extended() {

        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(4, 4),
                new Point(3, 3),
                new Point(2, 2),
                new Point(6, 6),
                new Point(5, 5),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(1, 1), new Point(6, 6));
        System.out.println(expected);
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(1, lineSegments.length);
        Assert.assertEquals(expected.toString(), lineSegments[0].toString());
    }

    @Test
    public void testCollinearPoints_2Lines() {

        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(4, 4),
                new Point(3, 3),
                new Point(2, 2),
                new Point(5, 5),
                new Point(1, 0),
                new Point(4, 3),
                new Point(3, 2),
                new Point(2, 1),
                new Point(5, 4),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(1, 1), new Point(5, 5));
        LineSegment expected1 = new LineSegment(new Point(1, 0), new Point(5, 4));
        System.out.println(expected);
        System.out.println(expected1);
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(2, lineSegments.length);
        String stringRepresentation = Arrays.toString(lineSegments);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected1.toString()));
    }

    @Test
    public void testCollinearPoints_2LinesCrossed() {

        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(4, 4),
                new Point(3, 3),
                new Point(2, 2),
                new Point(5, 5),
                new Point(5, 6),
                new Point(3, 7),
                new Point(7, 7),
                new Point(0, 4),
                new Point(1, 3),
                new Point(3, 1),
                new Point(4, 0),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(1, 1), new Point(7, 7));
        LineSegment expected1 = new LineSegment(new Point(4, 0), new Point(0, 4));
        System.out.println(expected);
        System.out.println(expected1);
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(2, lineSegments.length);
        String stringRepresentation = Arrays.toString(lineSegments);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected1.toString()));
    }

    @Test
    public void testCollinearPoints_4LinesCrossed() {

        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(4, 4),
                new Point(3, 3),
                new Point(5, 5),
                new Point(5, 6),
                new Point(3, 7),
                new Point(7, 7),
                new Point(0, 4),
                new Point(1, 3),
                new Point(2, 2),
                new Point(3, 1),
                new Point(4, 0),
                new Point(1, 0),
                new Point(4, 3),
                new Point(2, 1),
                new Point(5, 4),
                new Point(7, 6),
                new Point(0, 5),
                new Point(1, 4),
                new Point(2, 3),
                new Point(3, 2),
                new Point(4, 1),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(1, 1), new Point(7, 7));
        LineSegment expected2 = new LineSegment(new Point(1, 0), new Point(7, 6));
        LineSegment expected1 = new LineSegment(new Point(4, 0), new Point(0, 4));
        LineSegment expected3 = new LineSegment(new Point(4, 1), new Point(0, 5));

        LineSegment expected4 = new LineSegment(new Point(1, 0), new Point(1, 4));
        LineSegment expected5 = new LineSegment(new Point(4, 0), new Point(4, 4));
        LineSegment expected6 = new LineSegment(new Point(1, 1), new Point(4, 1));
        LineSegment expected7 = new LineSegment(new Point(3, 1), new Point(3, 7));
        LineSegment expected8 = new LineSegment(new Point(1, 3), new Point(4, 3));
        LineSegment expected9 = new LineSegment(new Point(0, 4), new Point(5, 4));

        LineSegment[] lineSegments = segments.segments();
        String stringRepresentation = Arrays.toString(lineSegments);
        System.out.println(stringRepresentation);
        Assert.assertEquals(10, lineSegments.length);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected1.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected2.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected3.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected4.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected5.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected6.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected7.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected7.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected9.toString()));
    }


    @Test
    public void testSiteTest8() {
        Point[] points = new Point[]{
                new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(10000, 0), new Point(0, 10000));
        LineSegment expected1 = new LineSegment(new Point(3000, 4000), new Point(20000, 21000));
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(2, lineSegments.length);
        String stringRepresentation = Arrays.toString(lineSegments);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));
        Assert.assertTrue(stringRepresentation.contains(expected1.toString()));
    }

    @Test
    public void testSiteTest6() {
        Point[] points = new Point[]{
                new Point(19000, 10000),
                new Point(18000, 10000),
                new Point(32000, 10000),
                new Point(21000, 10000),
                new Point(1234, 5678),
                new Point(14000, 10000),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(14000, 10000), new Point(32000, 10000));
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(1, lineSegments.length);
        String stringRepresentation = Arrays.toString(lineSegments);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));
    }

    @Test
    public void testVerticalLine() {
        Point[] points = new Point[]{
                new Point(14407, 19953),
                new Point(14407, 17831),
                new Point(14407, 10367),
                new Point(14407, 17188),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(14407, 10367), new Point(14407, 19953));
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(1, lineSegments.length);
        String stringRepresentation = Arrays.toString(lineSegments);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));

    }

    @Test
    public void testFourRandom() {
        Point[] points = new Point[]{
                new Point(10365, 12204),
                new Point(5437, 1578),
                new Point(13053, 18000),
                new Point(11261, 14136),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(5437, 1578), new Point(13053, 18000));
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(1, lineSegments.length);
        String stringRepresentation = Arrays.toString(lineSegments);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));

    }

    @Test
    public void testFourEquidistant() {
        Point[] points = new Point[]{
                new Point(10000, 20000),
                new Point(30000, 0),
                new Point(0, 30000),
                new Point(20000, 10000),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(30000, 0), new Point(0, 30000));
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(1, lineSegments.length);
        String stringRepresentation = Arrays.toString(lineSegments);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));

    }

    @Test
    public void testEquidistant() {
        Point[] points = new Point[]{
                new Point(10000 ,  0),
                new Point(8000  ,2000),
                new Point(2000  ,8000),
                new Point(0     ,10000),
                new Point(20000 ,0),
                new Point(18000 ,2000),
                new Point(2000  ,18000),
                new Point(10000 ,20000),
                new Point(30000 ,0),
                new Point(0     ,30000),
                new Point(20000 ,10000),
                new Point(13000 ,0),
                new Point(11000 ,3000),
                new Point(5000  ,12000),
                new Point(9000  ,6000),
        };
        FastCollinearPoints segments = new FastCollinearPoints(points);
        LineSegment expected = new LineSegment(new Point(30000, 0), new Point(0, 30000));
        LineSegment[] lineSegments = segments.segments();
        Assert.assertEquals(4, lineSegments.length);
        String stringRepresentation = Arrays.toString(lineSegments);
        Assert.assertTrue(stringRepresentation.contains(expected.toString()));

    }


    @Test(expected = NullPointerException.class)
    public void testNull() {
        new FastCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullInArray() {
        new FastCollinearPoints(new Point[]{new Point(1, 1), null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicates() {
        new FastCollinearPoints(new Point[]{new Point(1, 1), new Point(1, 2), new Point(1, 1)});
    }

}
