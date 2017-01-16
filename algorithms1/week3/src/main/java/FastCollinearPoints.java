import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trash on 18-Nov-16.
 */
public class FastCollinearPoints {
    private static final int MINIMAL_LENGTH = 4;
    private Point[] points;
    private ArrayList<MyLineSegment> segments = new ArrayList<>();


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] p) {
        if (p == null || p.length == 0) {
            throw new java.lang.NullPointerException("Array is null");
        }
        points = new Point[p.length];
        for (int i = 0; i < p.length; i++) {
            points[i] = p[i];
        }
        Arrays.sort(points);
        for (int i = 0; i < p.length - 1; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("Null element in the array");
            }
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException("Equal elements in incoming array " + points[i].toString());
            }
        }
        process();
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] finalSegments = new LineSegment[segments.size()];
        for (int i = 0; i < segments.size(); i++) {
            finalSegments[i] = segments.get(i);
        }
        return finalSegments;
    }

    private void process() {

        // Going through all points
        for (int i = 0; i < points.length; i++) {
//            int subArrayLength = points.length - 1;
            Point[] locals = new Point[points.length - 1];
            // build sub-array of points which were not compared yet with particular point
            int index = 0;
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                locals[index++] = points[j];
            }
            // Sort given array of locals
            Arrays.sort(locals, points[i].slopeOrder());
            int k = 0;
            // split points by smaller arrays with the same slope
            // subArrayLength - 2 it is penultimate in subarray
            for (int j = 0; j < locals.length - 1; j++) {
                // find maximum and minimum
                double slopeToJ = points[i].slopeTo(locals[j]);
                double slopeToJ1 = points[i].slopeTo(locals[j + 1]);
                // penultimate element
                if (j == locals.length - 2 && j - k >= MINIMAL_LENGTH - 3 && slopeToJ == slopeToJ1) {
                    Point[] subarray = new Point[j + 1 - k + 2];
                    subarray[0] = points[i];
                    int tmp = 1;
                    for (int t = k; t <= j + 1; t++) {
                        subarray[tmp++] = locals[t];
                    }
                    Arrays.sort(subarray);
                    MyLineSegment lineSegment = new MyLineSegment(subarray[0], subarray[subarray.length - 1]);
                    if (!contains(segments, lineSegment)) {
                        segments.add(lineSegment);
                    }
                    k = j + 2;
                }
                // edge between two groups of equal elements
                if (slopeToJ != slopeToJ1) {
                    if (j - k >= MINIMAL_LENGTH - 2) {
                        Point[] subarray = new Point[j - k + 2];
                        subarray[0] = points[i];
                        int tmp = 1;
                        for (int t = k; t < j + 1; t++) {
                            subarray[tmp++] = locals[t];
                        }
                        Arrays.sort(subarray);

                        MyLineSegment lineSegment = new MyLineSegment(subarray[0], subarray[subarray.length - 1]);
                        if (!contains(segments, lineSegment)) {
                            segments.add(lineSegment);
                        }
                    }
                    k = j + 1;
                }
            }
        }


    }

    private static class MyLineSegment extends LineSegment {
        private Point p;
        private Point q;

        MyLineSegment(Point p, Point q) {
            super(p, q);
            this.p = p;
            this.q = q;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o.getClass() == MyLineSegment.class)) {
                return false;
            }
            MyLineSegment other = (MyLineSegment) o;
            return this.p.compareTo(other.p) == 0 && this.q.compareTo(other.q) == 0;
        }

        @Override
        public int hashCode() {
            return this.hashCode();
        }
    }

    private boolean contains(List<MyLineSegment> segs, MyLineSegment seg) {
        if (seg == null) {
            return false;
        }
        for (MyLineSegment s : segs) {
            if (seg.equals(s)) {
                return true;
            }
        }
        return false;
    }


}