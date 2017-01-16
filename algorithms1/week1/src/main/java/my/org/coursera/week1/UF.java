package my.org.coursera.week1;

import java.util.Scanner;

/**
 * Created by trash on 30-Oct-16.
 */
public abstract class UF {
    protected int[] id;

    abstract public UF init(int n);

    abstract int root(int p);

    abstract String printMessage(int i);

    public void print() {
        for (int i = 0; i < id.length; i++) {
            System.out.print(id[i] + " ");
        }
        System.out.println();
    }

    private void printArryas(String initial) {
        System.out.println(initial);
        System.out.println();
        for (int i = 0; i < id.length; i++) {
            System.out.print(id[i] + " ");
        }
        System.out.println();
    }

    abstract boolean connected(int p, int q);

    abstract void union(int p, int q);


}
