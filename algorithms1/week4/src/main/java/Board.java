import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

/**
 * Created by trash on 22-Nov-16.
 */
public class Board {
    private int[][] blocks;
//    private Board parent = null;
    // private int num = 0;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = clone(blocks);
    }

//    private Board(int[][] blocks, Board parent) {
//        this.blocks = clone(blocks);
//        this.parent = parent;
    //   this.num = parent.num + 1;
//    }

    private int[][] clone(int[][] inBlocks) {
        int[][] newBlocks = new int[inBlocks.length][inBlocks.length];
        for (int i = 0; i < inBlocks.length; i++) {
            for (int j = 0; j < inBlocks.length; j++) {
                newBlocks[i][j] = inBlocks[i][j];
            }
        }
        return newBlocks;
    }

    // board dimension n
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                int valueAt = blocks[i][j];
                int expectedValue = i * blocks.length + j + 1;
                if (expectedValue != valueAt && expectedValue != (blocks.length * blocks.length)) {
                    hamming++;
                }
            }
        }
        return hamming; // + num;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;

        int y;
        int x;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {

                int valueAt = blocks[i][j];
                if (valueAt != 0) {
                    y = (valueAt - 1) / blocks.length;
                    x = valueAt - 1 - y * blocks.length;
                    int value = x - j;
                    manhattan += value >= 0 ? value : -value;
                    value = y - i;
                    manhattan += value >= 0 ? value : -value;
                }
            }
        }
        return manhattan; // + num;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return manhattan() == 0; // - num == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int x1 = StdRandom.uniform(blocks.length);
        int y1 = StdRandom.uniform(blocks.length);
        int x2 = StdRandom.uniform(blocks.length);
        int y2 = StdRandom.uniform(blocks.length);


        while (x1 == x2 && y1 == y2 || blocks[x1][y1] == 0 || blocks[x2][y2] == 0) {
            x1 = StdRandom.uniform(blocks.length);
            y1 = StdRandom.uniform(blocks.length);
            x2 = StdRandom.uniform(blocks.length);
            y2 = StdRandom.uniform(blocks.length);
        }

        Board twin = new Board(clone(blocks));
        int tmp = twin.blocks[x1][y1];
        twin.blocks[x1][y1] = twin.blocks[x2][y2];
        twin.blocks[x2][y2] = tmp;
        return twin;

    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (!this.getClass().equals(y.getClass())) {
            return false;
        }
        Board o = (Board) y;
        if (blocks.length != o.blocks.length || blocks[0].length != o.blocks[0].length) {
            return false;
        }
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j] != o.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        int x = -1;
        int y = -1;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[i][j] == 0) {
                    x = j;
                    y = i;
                }
            }
        }
        boolean sameAsParent = false;
        boolean wasParent = false;
        // upper block if present
        int[][] newBlocks;
        Board neighbor;
        if (y - 1 > -1) {
            newBlocks = clone(blocks);
            newBlocks[y][x] = newBlocks[y - 1][x];
            newBlocks[y - 1][x] = 0;
            neighbor = new Board(newBlocks);
//            if (!neighbor.equals(parent)) {
            neighbors.add(neighbor);
//                wasParent = false;
//            }

        }
        // lower block if present
        if (y + 1 < blocks.length) {
            newBlocks = clone(blocks);
            newBlocks[y][x] = newBlocks[y + 1][x];
            newBlocks[y + 1][x] = 0;
            neighbor = new Board(newBlocks);
//            sameAsParent = false;
//            if (!wasParent) {
//                sameAsParent = neighbor.equals(parent);
//                wasParent = sameAsParent;
//            }
//            if (!sameAsParent) {
            neighbors.add(neighbor);
//                sameAsParent = false;
//            }
        }
        // left block if present
        if (x - 1 > -1) {
            newBlocks = clone(blocks);
            newBlocks[y][x] = newBlocks[y][x - 1];
            newBlocks[y][x - 1] = 0;
            neighbor = new Board(newBlocks);
//            sameAsParent = false;
//            if (!wasParent) {
//                sameAsParent = neighbor.equals(parent);
//                wasParent = sameAsParent;
//            }
//            if (!sameAsParent) {
            neighbors.add(neighbor);
//            }
        }
        // right block if present
        if (x + 1 < blocks.length) {
            newBlocks = clone(blocks);
            newBlocks[y][x] = newBlocks[y][x + 1];
            newBlocks[y][x + 1] = 0;
            neighbor = new Board(newBlocks);
//            sameAsParent = false;
//            if (!wasParent) {
//                sameAsParent = neighbor.equals(parent);
//            }
//            if (!sameAsParent) {
            neighbors.add(neighbor);
//            }
        }
        return neighbors;
    }
    // string representation of this board (in the output format specified below)

    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append(dimension() + System.lineSeparator());
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                str.append(" " + blocks[i][j]);

            }
            str.append(System.lineSeparator());
        }
        return str.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}
