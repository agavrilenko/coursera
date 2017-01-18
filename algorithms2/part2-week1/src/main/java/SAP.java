import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;


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
        boolean[] marked = new boolean[g.V()];
        int[] length = new int[g.V()];
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    q.enqueue(w);
                    marked[w] = true;
                    length[w] = length[v] + 1;
                    if (y == w) {
                        return length[w];
                    }
                }
            }
        }
        return -1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path

    public int ancestor(int x, int y) {
        boolean[] markedX = new boolean[g.V()];
        int[] lengthX = new int[g.V()];
        int[] edgeToX = new int[g.V()];
        Queue<Integer> q = new Queue<>();
        q.enqueue(x);
        markedX[x] = true;
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
        boolean[] markedY = new boolean[g.V()];
        int[] lengthY = new int[g.V()];
        int[] edgeToY = new int[g.V()];
        q = new Queue<>();
        q.enqueue(y);
        markedY[y] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!markedY[w]) {
                    q.enqueue(w);
                    markedY[w] = true;
                    edgeToY[w] = v;
                    lengthY[w] = lengthY[v] + 1;
                }
            }
        }
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

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }


    // do unit testing of this class
    public static void main(String[] args) {
        return;
    }
}
