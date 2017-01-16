import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 * Created by trash on 29-Nov-16.
 */
public class PointSET {
    // construct an empty set of points
    private KdTree tree;

    public PointSET() {
        tree = new KdTree();
    }

    // is the set empty?
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    // number of points in the set
    public int size() {
        return tree.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        tree.insert(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return tree.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        tree.draw();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return tree.range(rect);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        return tree.nearest(p);
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }

}
