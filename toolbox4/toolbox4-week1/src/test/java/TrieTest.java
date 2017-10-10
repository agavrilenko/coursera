import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by trash on 09-Oct-17.
 */
public class TrieTest {

    @Test
    public void testTry() {

        String[] inFiles = new String[]{"trie/sample1", "trie/sample2", "trie/sample3"};
        String[] outFiles = new String[]{"trie/sample1.a", "trie/sample2.a", "trie/sample3.a"};

        for (int i = 0; i < inFiles.length; i++) {
            Scanner sc = new Scanner(TrieTest.class.getClassLoader().getResourceAsStream(inFiles[i]));
            int nextInt = Integer.parseInt(sc.nextLine());
            String[] in = new String[nextInt];
            int j = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                in[j] = line;
                j++;
            }
            sc = new Scanner(TrieTest.class.getClassLoader().getResourceAsStream(outFiles[i]));
            ArrayList<String> result = new ArrayList<>();
            while (sc.hasNextLine()) {
                result.add(sc.nextLine());
            }

            Trie trie = new Trie();
            List<Map<Character, Integer>> maps = trie.buildTrie(in);
            ArrayList<String> actual = new ArrayList<>();
            for (int k = 0; k < maps.size(); ++k) {
                Map<Character, Integer> node = maps.get(k);
                for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                    actual.add(k + "->" + entry.getValue() + ":" + entry.getKey());
                }
            }
            Collections.sort(result);
            Collections.sort(actual);
            Assert.assertArrayEquals(result.toArray(), actual.toArray());

        }

    }

}