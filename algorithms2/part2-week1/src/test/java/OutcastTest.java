import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by trash on 20-Jan-17.
 */
public class OutcastTest {
    @Test
    public void testOutcast_SimpleScenrio() {
        WordNet wordnet = new WordNet("input/synsets.txt", "input/hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        String[] args = new String[]{"input/outcast5.txt", "input/outcast8.txt", "input/outcast11.txt"};
        String[] expected = new String[]{"table", "bed", "potato"};
        for (int t = 0; t < args.length; t++) {
            In in = new In(new Scanner(OutcastTest.class.getClassLoader().getResourceAsStream(args[t])));
            String[] nouns = in.readAllStrings();
            assertEquals(expected[t], outcast.outcast(nouns));
        }
    }

}