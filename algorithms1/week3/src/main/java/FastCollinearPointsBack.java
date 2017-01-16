import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by trash on 18-Nov-16.
 */
public class FastCollinearPointsBack {
    private Point[] points;
    private ArrayList<LineSegment> segments = new ArrayList<>();


    // finds all line segments containing 4 or more points
    public FastCollinearPointsBack(Point[] points) {
        this.points = points;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        //Going through all points
        for (int i = 0; i < points.length - 4; i++) {
            int subArrayLength = points.length - i - 1;
            Point[] locals = new Point[subArrayLength];
            //build sub-array of points which were not compared yet with particular point
            for (int j = 0; j < subArrayLength; j++) {
                locals[j] = points[i + j + 1];
            }
            //Sort given array of locals
            Arrays.sort(locals, points[i].slopeOrder());
            int k = 0;
            //split points by smaller arrays with the same slope
            //subArrayLength - 2 it is penultimate in subarray
            for (int j = 0; j < subArrayLength - 1; j++) {
                //find maximum and minimum
                double slopeToJ = points[i].slopeTo(locals[j]);
                double slopeToJ1 = points[i].slopeTo(locals[j + 1]);
                if (j == subArrayLength - 2 && j - k >= 2 && slopeToJ == slopeToJ1) {
                    Point[] subarray = new Point[j - k + 3];
                    subarray[0] = points[i];
                    int tmp = 1;
                    for (int t = k; t < j + 2; t++) {
                        subarray[tmp++] = locals[t];
                    }
                    Arrays.sort(subarray);
                    segments.add(new LineSegment(subarray[0], subarray[subarray.length - 1]));
                    k = j + 2;
                }
                if (slopeToJ != slopeToJ1 && j - k >= 3) {
                    Point[] subarray = new Point[j - k + 1];
                    subarray[0] = points[i];
                    int tmp = 1;
                    for (int t = k; t < j + 1; t++) {
                        subarray[tmp++] = locals[t];
                    }
                    Arrays.sort(subarray);
                    segments.add(new LineSegment(subarray[0], subarray[subarray.length - 1]));
                    k = j + 1;
                }
                if (slopeToJ != slopeToJ1) {
                    k = j + 1;
                }
            }
        }


        return segments.toArray(new LineSegment[]{});
    }

    private static class MyLineSegment implements Comparable<MyLineSegment> {
        private Point p;
        private Point q;
        private double a;
        private double b;
        private double length;

        public MyLineSegment(Point p, Point q) {
            this.p = p;
            this.q = q;
            a = p.slopeTo(q);
            String[] pCoord = p.toString().replaceAll("\\(", "").replaceAll("\\)", "").split(",");
            String[] qCoord = q.toString().replaceAll("\\(", "").replaceAll("\\)", "").split(",");
            double xP = Double.parseDouble(pCoord[0]);
            double xQ = Double.parseDouble(qCoord[0]);
            double yP = Double.parseDouble(pCoord[1]);
            double yQ = Double.parseDouble(qCoord[1]);
            b = xP - a * yP;
            double deltaX = xP - xQ;
            double deltaY = yP - yQ;
            length = deltaX * deltaX + deltaY * deltaY;

        }


        @Override
        public int compareTo(MyLineSegment o) {
            if (this.a == o.a && this.b == o.b) {
                return 0;
            }
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof MyLineSegment)) {
                return false;
            }
            MyLineSegment o = (MyLineSegment) obj;
            if (this.a == o.a && this.b == o.b) {
                return true;
            }
            return false;
        }


    }
}