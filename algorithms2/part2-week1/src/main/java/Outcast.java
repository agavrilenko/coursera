import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by trash on 16-Jan-17.
 */
public class Outcast {
    private WordNet wn;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        wn = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int candidate = -1;
        int sum = 0;
        for (int i = 0; i < nouns.length; i++) {
            int tmp = 0;
            for (int j = 0; j < nouns.length; j++) {
                tmp += wn.distance(nouns[i], nouns[j]);
            }
            if (tmp > sum) {
                sum = tmp;
                candidate = i;
            }
        }

        return nouns[candidate];
    }

    // see test client below
    public static void main(String[] args) {

        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
