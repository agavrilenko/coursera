package my.org.coursera.week1;

import java.util.Scanner;

/**
 * Created by trash on 30-Oct-16.
 */
public class QuickUnionUF extends UF {
    int[] depth;

    public QuickUnionUF(int n) {
        id = new int[n];
        depth = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            depth[i] = 1;
        }
    }

    public UF init(int n) {
        return new QuickUnionUF(n);
    }

    boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    @Override
    String printMessage(int p) {
        return "Depth at " + p + " is " + depth[p];
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

    int rootWithDepth(int p) {
        int i = p;
        while (i != id[i]) {
            i = id[i];
            depth[i]++;
        }
        return i;
    }

    public int root(int p) {
        int i = p;
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }


}
