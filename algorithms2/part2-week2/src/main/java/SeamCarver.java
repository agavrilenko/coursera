import edu.princeton.cs.algs4.Picture;

/**
 * Created by trash on 22-Jan-17.
 */
public class SeamCarver {
    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new NullPointerException();
        }
        this.picture = new Picture(picture);

    }

    // current picture
    public Picture picture() {
        return null;
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

        return Math.sqrt(deltaXSq + deltaYSq);
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
}
