import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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
        DirectedCycle cycle = new DirectedCycle(g);
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException();
        }
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
            int next = iter.next();
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

    private static class DirectedCycle {
        private boolean[] marked;        // marked[v] = has vertex v been marked?
        private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
        private boolean[] onStack;       // onStack[v] = is vertex on the stack?
        private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

        /**
         * Determines whether the digraph {@code G} has a directed cycle and, if so,
         * finds such a cycle.
         *
         * @param G the digraph
         */
        public DirectedCycle(Digraph G) {
            marked = new boolean[G.V()];
            onStack = new boolean[G.V()];
            edgeTo = new int[G.V()];
            for (int v = 0; v < G.V(); v++)
                if (!marked[v] && cycle == null) dfs(G, v);
        }

        // check that algorithm computes either the topological order or finds a directed cycle
        private void dfs(Digraph G, int v) {
            onStack[v] = true;
            marked[v] = true;
            for (int w : G.adj(v)) {

                // short circuit if directed cycle found
                if (cycle != null) return;

                    // found new vertex, so recur
                else if (!marked[w]) {
                    edgeTo[w] = v;
                    dfs(G, w);
                }

                // trace back directed cycle
                else if (onStack[w]) {
                    cycle = new Stack<Integer>();
                    for (int x = v; x != w; x = edgeTo[x]) {
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);
                    assert check();
                }
            }
            onStack[v] = false;
        }

        /**
         * Does the digraph have a directed cycle?
         *
         * @return {@code true} if the digraph has a directed cycle, {@code false} otherwise
         */
        public boolean hasCycle() {
            return cycle != null;
        }

        /**
         * Returns a directed cycle if the digraph has a directed cycle, and {@code null} otherwise.
         *
         * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
         * and {@code null} otherwise
         */
        public Iterable<Integer> cycle() {
            return cycle;
        }


        // certify that digraph has a directed cycle if it reports one
        private boolean check() {

            if (hasCycle()) {
                // verify cycle
                int first = -1, last = -1;
                for (int v : cycle()) {
                    if (first == -1) first = v;
                    last = v;
                }
                if (first != last) {
                    System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                    return false;
                }
            }


            return true;
        }
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
