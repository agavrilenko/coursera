import edu.princeton.cs.algs4.Picture;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by trash on 22-Jan-17.
 */
public class SeamCarver {
    private Picture picture;
    private double[][] energy;
    private int[] vMin;
    private int[] hMin;

    private Queue<Integer> preorder = new LinkedList<>();
    private boolean[] marked;
    private int[] verts;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new NullPointerException();
        }
        this.picture = new Picture(picture);
        energy = new double[this.picture.width()][this.picture.height()];
        for (int x = 0; x < width() - 1; x++) {
            for (int y = 0; y < height() - 1; y++) {
                energy(x, y);
            }
        }
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (height() <= y || width() <= x) {
            throw new IndexOutOfBoundsException();
        }

        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return 1000;
        }

        final int rX, gX, bX, rY, gY, bY;
        final double deltaXSq, deltaYSq;

        rX = picture.get(x + 1, y).getRed() - picture.get(x - 1, y).getRed();
        gX = picture.get(x + 1, y).getGreen() - picture.get(x - 1, y).getGreen();
        bX = picture.get(x + 1, y).getBlue() - picture.get(x - 1, y).getBlue();
        deltaXSq = rX * rX + gX * gX + bX * bX;

        rY = picture.get(x, y + 1).getRed() - picture.get(x, y - 1).getRed();
        gY = picture.get(x, y + 1).getGreen() - picture.get(x, y - 1).getGreen();
        bY = picture.get(x, y + 1).getBlue() - picture.get(x, y - 1).getBlue();

        deltaYSq = rY * rY + gY * gY + bY * bY;

        energy[x][y] = Math.sqrt(deltaXSq + deltaYSq);
        return energy[x][y];
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }

    Queue<Integer> buildVTopology(int v) {
        Queue<Integer> postorder = new LinkedList<>();
        postorder = new LinkedList<>();
        preorder = new LinkedList<>();
        marked = new boolean[width() * height()];
        dfs(postorder, v);
        return postorder;
    }

    private void dfs(Queue<Integer> postorder, int v) {

        marked[v] = true;
        int[] next;
        if (v >= (height() - 1) * width()) {
            next = new int[]{};
        } else if (v % width() == 0) {
            next = new int[]{v + width(), v + width() + 1};
        } else if ((v + 1) % width() == 0) {
            next = new int[]{v + width() - 1, v + width()};
        } else {
            next = new int[]{v + width() - 1, v + width(), v + width() + 1};
        }

        for (int w : next) {
            if (!marked[w]) {
                dfs(postorder, w);
            }
        }
        postorder.add(v);


    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new NullPointerException();
        }
        if (height() <= 1) {
            throw new IllegalArgumentException();
        }

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new NullPointerException();
        }
        if (width() <= 1) {
            throw new IllegalArgumentException();
        }

    }

    private static class Vertex {
        private int x;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int y;
    }
}
