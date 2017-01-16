import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by trash on 04-Nov-16.
 */
public class Percolation {
    //    private QuickUnionUF uf;
    private WeightedQuickUnionUF uf;
    private int squareSize;
    private int[] aux;
    private int size;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(" n ≤ 0 ");
        }
        squareSize = n * n;
        uf = new WeightedQuickUnionUF(squareSize + 2);
        aux = new int[squareSize + 2];
        size = n;
        for (int i = 1; i < size + 1; i++) {
            uf.union(0, i);
        }

        for (int i = squareSize + 1 - size; i < squareSize + 1; i++) {
            uf.union(squareSize + 1, i);
        }
    }

    private int getSquareSize() {
        return squareSize;
    }

    private int getSize() {
        return size;
    }


    public boolean isOpen(int row, int col) {

        int cell = getCell(row, col);
        return aux[cell] == 1;
    }

    public boolean isFull(int row, int col) {
        int cell = getCell(row, col);
        if (aux[cell] == 0) {
            return false;
        }
        return uf.connected(0, cell);
    }


    public static void main(String[] args) {

    }

    public boolean percolates() {
        if (aux.length == 3) {
            return aux[1] == 1;
        }
        return uf.connected(0, squareSize + 1);
    }

    public void open(int row, int col) {


        int cell;
        cell = getCell(row, col);
        aux[cell] = 1;
        if (aux[cell + 1] == 1 && cell % size != 0 && !uf.connected(cell, cell + 1)) {
            uf.union(cell, cell + 1);

        }
        if (aux[cell - 1] == 1 && cell % size != 1 && !uf.connected(cell, cell - 1)) {
            uf.union(cell, cell - 1);

        }
        if (cell + size <= squareSize && aux[cell + size] == 1 && !uf.connected(cell, cell + size)) {
            uf.union(cell, cell + size);

        }
        if (cell - size >= 1 && aux[cell - size] == 1 && !uf.connected(cell, cell - size)) {
            uf.union(cell, cell - size);

        }
    }

    private int getCell(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IndexOutOfBoundsException();
        }
        return (row - 1) * size + (col - 1) + 1;
    }

    private static class QuickUnionUF {
        private int[] depth;
        private int[] id;

        public QuickUnionUF(int n) {
            id = new int[n];
            depth = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                depth[i] = 1;
            }
        }

        public QuickUnionUF init(int n) {
            return new QuickUnionUF(n);
        }

        boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        void union(int p, int q) {

            int rootQ = root(q);
            int rootP = root(p);
            if (depth[rootQ] > depth[rootP]) {
                id[rootP] = rootQ;
                depth[rootQ] += depth[rootP];
            } else {
                id[rootQ] = rootP;
                depth[rootP] += depth[rootQ];
            }
        }

        public int root(int p) {
            int i = p;
            while (i != id[i]) {
                i = id[i];
            }
            return i;
        }
    }

}
