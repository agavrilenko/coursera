import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by trash on 09-Oct-17.
 */
public class TrieMatchingTest {
    @Test
    public void testTrieMatcher() {


        String[] inFiles = new String[]{
                "trie_matching/sample3",
                "trie_matching/sample4",
                "trie_matching/sample1",
                "trie_matching/sample2",
        };
        String[] outFiles = new String[]{
                "trie_matching/sample3.a",
                "trie_matching/sample4.a",
                "trie_matching/sample1.a",
                "trie_matching/sample2.a",
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

            TrieMatching trie = new TrieMatching();
            Assert.assertArrayEquals(String.format("Failed on TC #[%s]", i), result.toArray(), trie.solve(text, nextInt, in).toArray());

        }
    }

}