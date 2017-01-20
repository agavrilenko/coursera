import edu.princeton.cs.algs4.*;

import java.util.Arrays;
import java.util.Iterator;


/**
 * Created by trash on 16-Jan-17.
 */
public class SAP {

    private Digraph g;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        g = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int s, int y) {
        return length(Arrays.asList(s), Arrays.asList(y));
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path

    public int ancestor(int x, int y) {
        boolean[] markedX = new boolean[g.V()];
        int[] lengthX = new int[g.V()];
        int[] edgeToX = new int[g.V()];
        netFromVertex(Arrays.asList(x), markedX, lengthX, edgeToX);
        boolean[] markedY = new boolean[g.V()];
        int[] lengthY = new int[g.V()];
        int[] edgeToY = new int[g.V()];
        netFromVertex(Arrays.asList(y), markedY, lengthY, edgeToY);
        int aCandidate = -1;
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < g.V(); i++) {
            if (markedX[i] && markedY[i]) {
                if (lengthX[i] + lengthY[i] < sum) {
                    sum = lengthX[i] + lengthY[i];
                    aCandidate = i;
                }
            }
        }
        return aCandidate;

    }

    private void netFromVertex(Iterable<Integer> x, boolean[] markedX, int[] lengthX, int[] edgeToX) {
        Queue<Integer> q = new Queue<>();

        Iterator<Integer> iter = x.iterator();
        if (iter.hasNext()) {
            Integer next = iter.next();
            q.enqueue(next);
            markedX[next] = true;
        }
        while (iter.hasNext()) {
            q.enqueue(iter.next());
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!markedX[w]) {
                    q.enqueue(w);
                    markedX[w] = true;
                    edgeToX[w] = v;
                    lengthX[w] = lengthX[v] + 1;

                }
            }
        }
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        boolean[] markedX = new boolean[g.V()];
        int[] lengthX = new int[g.V()];
        int[] edgeToX = new int[g.V()];
        netFromVertex(v, markedX, lengthX, edgeToX);
        boolean[] markedY = new boolean[g.V()];
        int[] lengthY = new int[g.V()];
        int[] edgeToY = new int[g.V()];
        netFromVertex(w, markedY, lengthY, edgeToY);
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < g.V(); i++) {
            if (markedX[i] && markedY[i]) {
                if (lengthX[i] + lengthY[i] < sum) {
                    sum = lengthX[i] + lengthY[i];
                }
            }
        }
        return sum < Integer.MAX_VALUE ? sum : -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        boolean[] markedX = new boolean[g.V()];
        int[] lengthX = new int[g.V()];
        int[] edgeToX = new int[g.V()];
        netFromVertex(v, markedX, lengthX, edgeToX);
        boolean[] markedY = new boolean[g.V()];
        int[] lengthY = new int[g.V()];
        int[] edgeToY = new int[g.V()];
        netFromVertex(w, markedY, lengthY, edgeToY);
        int aCandidate = -1;
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < g.V(); i++) {
            if (markedX[i] && markedY[i]) {
                if (lengthX[i] + lengthY[i] < sum) {
                    sum = lengthX[i] + lengthY[i];
                    aCandidate = i;
                }
            }
        }
        return aCandidate;
    }


    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
