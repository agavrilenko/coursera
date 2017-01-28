import edu.princeton.cs.algs4.Picture;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by trash on 22-Jan-17.
 */
public class SeamCarver {
    private Picture picture;
    private double[][] energy;
    private boolean[] marked;

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
        return new Picture(picture);
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
        if (energy[x][y] != 0) {
            return energy[x][y];
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
        double[] distTo = new double[height() * width()];
        int[] edgeTo = new int[height() * width()];
        double minEnergy = Double.MAX_VALUE;
        int minIndex = -1;
        int[] minVPath = new int[width()];

        for (int i = 0; i < width() * height() - 1; i += width()) {
            boolean changed = false;
            for (int j = 0; j < distTo.length; j++) {
                distTo[j] = Double.MAX_VALUE;
                edgeTo[j] = -1;
            }
            buildSingleHPath(distTo, edgeTo, i);
            for (int j = width() - 1; j < (height() * width()); j += width()) {
                if (distTo[j] < minEnergy) {
                    changed = true;
                    minEnergy = distTo[j];
                    minIndex = j;

                }
            }
            if (changed) {
                for (int j = width() - 1; j >= 0; j--) {
                    minVPath[j] = minIndex / width();
                    minIndex = edgeTo[minIndex];
                }
            }
        }

        return minVPath;
    }

    // postorder could be built just by adding next vertices without dfs
    private LinkedList<Integer> buildVTopology(int v) {
        LinkedList<Integer> postorder = new LinkedList<>();
        marked = new boolean[width() * height()];
        vDfs(postorder, v);
        return postorder;
    }

    private LinkedList<Integer> buildHTopology(int v) {
        LinkedList<Integer> postorder = new LinkedList<>();
        marked = new boolean[width() * height()];
        hDfs(postorder, v);
        return postorder;
    }

    private void vDfs(Queue<Integer> postorder, int v) {

        marked[v] = true;
        int[] next = nextVVertices(v);
        for (int w : next) {
            if (!marked[w]) {
                vDfs(postorder, w);
            }
        }
        postorder.add(v);
    }

    private void hDfs(Queue<Integer> postorder, int v) {

        marked[v] = true;
        int[] next = nextHVertices(v);
        for (int w : next) {
            if (!marked[w]) {
                hDfs(postorder, w);
            }
        }
        postorder.add(v);
    }

    private int[] nextVVertices(int v) {
        if (width() == 1 && v < height() - 1) {
            return new int[]{v + 1};
        } else if (v >= (height() - 1) * width()) {
            return new int[]{};
        } else if (v % width() == 0) {
            return new int[]{v + width(), v + width() + 1};
        } else if ((v + 1) % width() == 0) {
            return new int[]{v + width() - 1, v + width()};
        } else {
            return new int[]{v + width() - 1, v + width(), v + width() + 1};
        }
    }

    private int[] nextHVertices(int v) {
        if (height() == 1 && v < width() - 1) {
            return new int[]{v + 1};
        } else if ((v + 1) % width() == 0) {
            return new int[]{};
        } else if (v < width()) {
            return new int[]{v + 1, v + width() + 1};
        } else if (v >= (height() - 1) * width()) {
            return new int[]{v - width() + 1, v + 1};
        } else {
            return new int[]{v - width() + 1, v + 1, v + width() + 1};
        }
    }

    private void buildSingleVPath(double[] distTo, int[] edgeTo, int v) {
        LinkedList<Integer> topology = buildVTopology(v);
        int last = topology.removeLast();
        double weight;
        edgeTo[last] = last;
        distTo[last] = getEnergyByIndex(last);
        double nextWeight;
        int[] next;
        while (topology.size() > 0) {
            weight = distTo[last];
            next = nextVVertices(last);
            for (int i : next) {
                nextWeight = weight + getEnergyByIndex(i);
                if (distTo[i] > nextWeight) {
                    distTo[i] = nextWeight;
                    edgeTo[i] = last;
                }
            }
            last = topology.removeLast();
        }
    }

    private void buildSingleHPath(double[] distTo, int[] edgeTo, int v) {
        LinkedList<Integer> topology = buildHTopology(v);
        int last = topology.removeLast();
        double weight;
        edgeTo[last] = last;
        distTo[last] = getEnergyByIndex(last);
        double nextWeight;
        int[] next;
        while (topology.size() > 0) {
            weight = distTo[last];
            next = nextHVertices(last);
            for (int i : next) {
                nextWeight = weight + getEnergyByIndex(i);
                if (distTo[i] > nextWeight) {
                    distTo[i] = nextWeight;
                    edgeTo[i] = last;
                }
            }
            last = topology.removeLast();
        }
    }


    private double getEnergyByIndex(int last) {
        int x = last % width();
        int y = last / width();
        return energy(x, y);
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[] distTo = new double[height() * width()];
        int[] edgeTo = new int[height() * width()];
        double minEnergy = Double.MAX_VALUE;
        int minIndex = -1;
        int[] minVPath = new int[height()];

        for (int i = 0; i < width(); i++) {
            boolean changed = false;
            for (int j = 0; j < distTo.length; j++) {
                distTo[j] = Double.MAX_VALUE;
                edgeTo[j] = -1;
            }
            buildSingleVPath(distTo, edgeTo, i);
            for (int j = (height() - 1) * width(); j < height() * width(); j++) {
                if (distTo[j] < minEnergy) {
                    changed = true;
                    minEnergy = distTo[j];
                    minIndex = j;

                }
            }
            if (changed) {
                for (int j = height() - 1; j >= 0; j--) {
                    minVPath[j] = minIndex % width();
                    minIndex = edgeTo[minIndex];
                }
            }
        }

        return minVPath;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new NullPointerException();
        }
        if (height() <= 1 || seam.length != width()) {
            throw new IllegalArgumentException();
        }

        int prev = seam[0];
        for (int i = 0; i < seam.length; i++) {
            int abs = prev > seam[i] ? prev - seam[i] : seam[i] - prev;
            if (abs > 1) {
                throw new IllegalArgumentException("on i = " + i);
            }
            if (seam[i] < 0 || seam[i] > height() - 1) {
                throw new IllegalArgumentException("on i = " + i);
            }
            prev = seam[i];
        }


        double[][] tmpEnergy = new double[width()][height() - 1];
        Picture tmpPic = new Picture(width(), height() - 1);
        int yIndex;
        for (int i = 0; i < width(); i++) {
            yIndex = 0;
            for (int j = 0; j < height(); j++) {
                if (seam[i] == j) {
                    continue;
                }
                tmpEnergy[i][yIndex] = energy[i][j];
                tmpPic.set(i, yIndex, picture.get(i, j));
                yIndex++;
            }
        }
        picture = tmpPic;
        energy = tmpEnergy;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new NullPointerException();
        }
        if (width() <= 1 || seam.length != height()) {
            throw new IllegalArgumentException();
        }

        int prev = seam[0];
        for (int i = 0; i < seam.length; i++) {
            int abs = prev > seam[i] ? prev - seam[i] : seam[i] - prev;
            if (abs > 1) {
                throw new IllegalArgumentException("on i = " + i);
            }
            if (seam[i] < 0 || seam[i] > width() - 1) {
                throw new IllegalArgumentException("on i = " + i);
            }
            prev = seam[i];
        }

        double[][] tmpEnergy = new double[width() - 1][height()];
        Picture tmpPic = new Picture(width() - 1, height());
        int xIndex;
        for (int j = 0; j < height(); j++) {
            xIndex = 0;
            for (int i = 0; i < width(); i++) {
                if (seam[j] == i) {
                    continue;
                }
                tmpEnergy[xIndex][j] = energy[i][j];
                tmpPic.set(xIndex, j, picture.get(i, j));
                xIndex++;
            }
        }
        picture = tmpPic;
        energy = tmpEnergy;
    }
}
