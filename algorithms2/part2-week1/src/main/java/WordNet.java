import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by trash on 16-Jan-17.
 */
public class WordNet {

    //    private ArrayList<List<String>> synsets = new ArrayList<>();
    private ArrayList<String> defintions = new ArrayList<>();
    private SAP sap;
    private Map<String, Integer> synsets = new HashMap<>();
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
                synsets.put(key, index);
            }
            defintions.add(index, parts[2]);
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
        Integer indexA = synsets.get(nounA);
        Integer indexB = synsets.get(nounB);
        return sap.length(indexA, indexB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        Integer indexA = synsets.get(nounA);
        Integer indexB = synsets.get(nounB);
        Integer ancestor = sap.ancestor(indexA, indexB);
        return source.get(ancestor);

    }

    // do unit testing of this class
    public static void main(String[] args) {

        return;
    }
}