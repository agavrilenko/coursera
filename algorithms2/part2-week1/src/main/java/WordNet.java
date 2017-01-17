import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by trash on 16-Jan-17.
 */
public class WordNet {

    private ArrayList<String>[] synsets;
    private LinkedList<Integer>[] hypernyms;
    private LinkedList<String> result;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In synFile = new In(new Scanner(WordNet.class.getResourceAsStream("classpath:" + synsets)));


    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        if (result == null) {
            result = new LinkedList<>();
            for (List<String> synset : synsets) {
                result.addAll(synset);
            }
        }
        return result;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {

        return;
    }
}