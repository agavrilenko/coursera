import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by trash on 12-Oct-17.
 */
public class TrieMatchingExtendedTest {
    @Test
    public void testTrieMatcher() {


        String[] inFiles = new String[]{
                "trie_matching_extended/sample1",
                "trie_matching_extended/sample2",
        };
        String[] outFiles = new String[]{
                "trie_matching_extended/sample1.a",
                "trie_matching_extended/sample2.a",
        };

        for (int i = 0; i < inFiles.length; i++) {
            Scanner sc = new Scanner(TrieTest.class.getClassLoader().getResourceAsStream(inFiles[i]));
            String text = sc.nextLine();
            int nextInt = Integer.parseInt(sc.nextLine());
            List<String> in = new ArrayList<>();
            while (sc.hasNextLine()) {
                in.add(sc.nextLine());
            }
            sc = new Scanner(TrieTest.class.getClassLoader().getResourceAsStream(outFiles[i]));
            ArrayList<Integer> result = new ArrayList<>();

            if (sc.hasNextLine()) {
                String[] outSt = sc.nextLine().split(" ");
                Arrays.stream(outSt).mapToInt(s -> Integer.valueOf(s)).sorted().forEach(val -> result.add(val));
            }

            TrieMatchingExtended trie = new TrieMatchingExtended();
            Assert.assertArrayEquals(String.format("Failed on TC #[%s]", i), result.toArray(), trie.solve(text, nextInt, in).toArray());

        }
    }

}