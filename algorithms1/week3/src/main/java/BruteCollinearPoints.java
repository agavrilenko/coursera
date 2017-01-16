/**
 * Created by trash on 19-Nov-16.
 */
public class BruteCollinearPoints {
    private FastCollinearPoints collinearPoints;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        collinearPoints = new FastCollinearPoints(points);
    }

    // the number of line segments
    public int numberOfSegments() {
        return collinearPoints.numberOfSegments();
    }

    // the line segments
    public LineSegment[] segments() {
        return collinearPoints.segments();
    }
}
