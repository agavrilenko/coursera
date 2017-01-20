import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by trash on 16-Jan-17.
 */
public class WordNet {

    //    private ArrayList<List<String>> synsets = new ArrayList<>();
//    private ArrayList<String> defintions = new ArrayList<>();
    private SAP sap;
    private Map<String, List<Integer>> synsets = new HashMap<>();
    private ArrayList<String> source = new ArrayList<>();

    // constructor takes the name of the two input files
    public WordNet(String synsFile, String hypsFile) {
        In synFile = new In(new Scanner(WordNet.class.getClassLoader().getResourceAsStream(synsFile)));
        In hypFile = new In(new Scanner(WordNet.class.getClassLoader().getResourceAsStream(hypsFile)));
        // read synsets and store it in collection

        while (synFile.hasNextLine()) {
            String line = synFile.readLine();
            String[] parts = line.split(",");
            Integer index = Integer.valueOf(parts[0]);
            List<String> synset = Arrays.asList(parts[1].split(" "));
            for (String key : synset) {
                List<Integer> indexes = synsets.get(key);
                if (indexes == null) {
                    indexes = new ArrayList<>();
                    synsets.put(key, indexes);
                }
                indexes.add(index);
            }
//            defintions.add(index, parts[2]);
            source.add(index, parts[1]);
        }
        Digraph g = new Digraph(synsets.size());

        // read hypernyms and store it in collection
        while (hypFile.hasNextLine()) {
            String nodes = hypFile.readLine();
            String[] hypernyms = nodes.split(",");
            Integer child = Integer.valueOf(hypernyms[0]);
            for (int i = 1; i < hypernyms.length; i++) {
                Integer parent = Integer.valueOf(hypernyms[i]);
                g.addEdge(child, parent);
            }
        }
        DirectedCycle cycle = new DirectedCycle(g);
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException("Grapgh contains cycles");
        }
        sap = new SAP(g);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return synsets.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return synsets.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        List<Integer> indexA = synsets.get(nounA);
        List<Integer> indexB = synsets.get(nounB);
        if (indexA == null || indexB == null) {
            throw new IllegalArgumentException();
        }
        return sap.length(indexA, indexB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        List<Integer> indexA = synsets.get(nounA);
        List<Integer> indexB = synsets.get(nounB);
        if (indexA == null || indexB == null) {
            throw new IllegalArgumentException();
        }
        Integer ancestor = sap.ancestor(indexA, indexB);
        return source.get(ancestor);

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

        return;
    }
}