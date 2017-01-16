import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;

/**
 * Created by trash on 29-Nov-16.
 */
public class KdTree {
    private int size = 0;
    private Node root;
    private Node closest;
    private double dist;
    private int hops;

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size <= 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (size == 0) {
            root = new Node(p, true);
            size++;
            return;
        }
        add(root, p);
//        size++;
    }

    private void add(Node node, Point2D p) {
        if (node.p.equals(p)) {
            return;
        }
        if (node.vert) {
            if (node.p.x() > p.x()) {
                addToLeft(node, p);
            } else {
                addToRight(node, p);
            }
        } else {
            if (node.p.y() > p.y()) {
                addToLeft(node, p);
            } else {
                addToRight(node, p);
            }
        }
    }

    private void addToLeft(Node node, Point2D p) {
        if (node.left == null) {
            node.left = new Node(p, !node.vert);
            size++;
        } else {
            add(node.left, p);
        }
    }

    private void addToRight(Node node, Point2D p) {
        if (node.right == null) {
            node.right = new Node(p, !node.vert);
            size++;
        } else {
            add(node.right, p);
        }
    }

    // does the set contain p p?
    public boolean contains(Point2D p) {
        if (root == null) {
            return false;
        }
        Node node = search(root, p);
        return node != null;
    }

    private Node search(Node node, Point2D p) {
        if (node.p.equals(p)) {
            return node;
        }
        if (node.left == null && node.right == null) {
            return null;
        }
        if (node.vert) {
            if (node.p.x() > p.x()) {
                return searchLeft(node, p);
            } else {
                return searchRight(node, p);
            }
        } else {
            if (node.p.y() > p.y()) {
                return searchLeft(node, p);
            } else {
                return searchRight(node, p);
            }
        }
    }

    private Node searchLeft(Node node, Point2D p) {
        if (node.left != null) {
            return search(node.left, p);
        } else {
            return null;
        }
    }

    private Node searchRight(Node node, Point2D p) {
        if (node.right != null) {
            return search(node.right, p);
        } else {
            return null;
        }
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {

        ArrayList<Point2D> inside = new ArrayList<>();
        if (rect == null) {
            return inside;
        }
        Point2D rt = new Point2D(rect.xmax(), rect.ymax());
        Point2D lb = new Point2D(rect.xmin(), rect.ymin());

        Node cur = root;
        if (root == null) {
            return inside;
        }

        traverse(rect, inside, rt, lb, cur);
        return inside;
    }

    private void traverse(RectHV rect, ArrayList<Point2D> inside, Point2D rt, Point2D lb, Node cur) {
        if (rect.contains(cur.p)) {
            inside.add(cur.p);
        }
        Node left = cur.left;
        Node right = cur.right;
        if (left != null && cur.vert && lb.x() <= cur.p.x()) {
            traverse(rect, inside, rt, lb, left);
        }
        if (left != null && !cur.vert && lb.y() <= cur.p.y()) {
            traverse(rect, inside, rt, lb, left);
        }

        if (right != null && cur.vert && cur.p.x() <= rt.x()) {
            traverse(rect, inside, rt, lb, right);
        }
        if (right != null && !cur.vert && rt.y() >= cur.p.y()) {
            traverse(rect, inside, rt, lb, right);
        }
    }

    // a nearest neighbor in the set to p p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        closest = root;
        dist = root.p.distanceSquaredTo(p);
        hops = 1;
        searchClosest(p, cur);

        return closest.p;

    }

    private void searchClosest(Point2D p, Node cur) {
        double distToCurrent = cur.p.distanceSquaredTo(p);
        if (distToCurrent < dist) {
            dist = distToCurrent;
            closest = cur;
        }
        Node left = cur.left;
        Node right = cur.right;

        if (left != null && cur.vert && ((cur.p.x() < p.x() && sqrDist(cur.p.x(), p.x()) < dist) || p.x() <= cur.p.x())) {
            hops++;
            searchClosest(p, left);
        }

        if (left != null && !cur.vert && ((cur.p.y() < p.y() && sqrDist(cur.p.y(), p.y()) < dist) || p.y() <= cur.p.y())) {
            hops++;
            searchClosest(p, left);
        }
        if (right != null && cur.vert && ((p.x() < cur.p.x() && sqrDist(p.x(), cur.p.x()) < dist) || cur.p.x() <= p.x())) {
            hops++;
            searchClosest(p, right);
        }
        if (right != null && !cur.vert && ((p.y() < cur.p.y() && sqrDist(p.y(), cur.p.y()) < dist) || cur.p.y() <= p.y())) {
            hops++;
            searchClosest(p, right);
        }
    }

    private static double sqrDist(double a, double b) {
        double diff = a - b;
        return diff * diff;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }

    private static class Node {
        private Point2D p;
        private Node left;
        private Node right;

        /**
         * vert == true for vertical split,
         * vert == false for horizontal split
         */
        private boolean vert;

        public Node(Point2D p, boolean red) {
            this.p = p;
            this.vert = red;
        }

        @Override
        public String toString() {
            return "Node{" + p +
                    ", vert=" + vert +
                    '}';
        }
    }

}
