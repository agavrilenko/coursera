import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Trie {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    private final HashMap<Character, Integer> positions = new HashMap() {
        {
            put('A', 0);
            put('T', 1);
            put('G', 2);
            put('C', 3);

        }
    };

    private static class N0de {
        N0de[] adj = new N0de[4];
        char value;
        int id;

        public N0de(char value, int id) {
            this.value = value;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "adj=" + Arrays.toString(adj) +
                    ", value=" + value +
                    ", id=" + id +
                    '}';
        }
    }

    private int cnt = 0;

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
        Map<Character, Integer> root = new HashMap<>();
        trie.add(root);
        for (String pattern : patterns) {
            Map<Character, Integer> currentNode = root;
            for (int i = 0; i < pattern.length(); i++) {
                Character currentSymbol = pattern.charAt(i);
                Set<Character> neighbours = currentNode.keySet();
                if (neighbours != null && neighbours.contains(currentSymbol)) {
                    currentNode = trie.get(currentNode.get(currentSymbol));
                } else {
                    Map<Character, Integer> newNode = new HashMap<>();
                    trie.add(newNode);
                    currentNode.put(currentSymbol, trie.size() - 1);
                    currentNode = newNode;
                }

            }
        }
        return trie;
    }

    List<Map<Character, Integer>> buildTrie1(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();

        N0de root = new N0de('0', cnt);
        cnt++;
        for (int i = 0; i < patterns.length; i++) {
            char[] symbols = patterns[i].toCharArray();
            buildTrie(symbols, 0, root, trie);
        }

//        traverse(root, trie);

        return trie;
    }

    private void traverse(N0de root, List<Map<Character, Integer>> trie) {
        Map<Character, Integer> map = new HashMap<>();
        Arrays.stream(root.adj).filter(Objects::nonNull)
                .forEach(node -> {
                    map.put(node.value, node.id);
                });
        trie.add(map);
        Arrays.stream(root.adj).filter(Objects::nonNull).forEach(node -> traverse(node, trie));
    }

    private void buildTrie(char[] symbols, int i, N0de n0de, List<Map<Character, Integer>> trie) {
        if (i == symbols.length) {
            return;
        }
        Character cur = symbols[i];
        Integer position = positions.get(cur);
        N0de node = n0de.adj[position];
        if (node == null) {
            node = new N0de(cur, cnt);
            n0de.adj[position] = node;
            cnt++;
        }
        buildTrie(symbols, i + 1, node, trie);
    }

    static public void main(String[] args) throws IOException {
        new Trie().run();
    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
}
