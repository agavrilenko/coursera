import edu.princeton.cs.algs4.Picture;

/**
 * Created by trash on 22-Jan-17.
 */
public class SeamCarver {
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
    }

    // current picture
    public Picture picture() {
        return null;
    }

    // width of current picture
    public int width() {
        return -1;
    }

    // height of current picture
    public int height() {
        return -1;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        return -1.0;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }
}