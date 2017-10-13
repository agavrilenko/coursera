import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TrieMatchingExtended implements Runnable {
//    private static class Node {
//        public static final int Letters = 4;
//        public static final int NA = -1;
//        public int next[];
//        public boolean patternEnd;
//
//        Node() {
//            next = new int[Letters];
//            Arrays.fill(next, NA);
//            patternEnd = false;
//        }
//    }
//
//    int letterToIndex(char letter) {
//        switch (letter) {
//            case 'A':
//                return 0;
//            case 'C':
//                return 1;
//            case 'G':
//                return 2;
//            case 'T':
//                return 3;
//            default:
//                assert (false);
//                return Node.NA;
//        }
//    }

    private Collection<? extends Integer> match(Node root, char[] in, int index) {

        List<Integer> result = new ArrayList<>();
        Node node = root;
        int i = index;
        while (true) {
            if (i == in.length) {
                break;
            }
            Node current = node.adj[ind.get(in[i++])];
            if (current == null) {
                break;
            }
            if (current.startsAt != -1) {
                result.add(index);
                break;
            }
            node = current;
        }
        return result;
    }

    private static class Node {
        public Node adj[] = new Node[4];
        char value;
        int startsAt = -1;

        public Node(char value) {
            this.value = value;

        }

        public Node() {
        }

        public Node(char value, int startsAt) {
            this.value = value;
            this.startsAt = startsAt;
        }

        @Override
        public String toString() {
            return value + "->" + startsAt;
        }
    }


    private final Map<Character, Integer> ind = new HashMap() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};


    List<Integer> solve(String text, int n, List<String> patterns) {
        List<Integer> result = new ArrayList<Integer>();


//		build trie from text
        Node root = new Node();
        for (int i = 0; i < patterns.size(); i++) {
            addToTrie(root, i, patterns.get(i).toCharArray());

        }

//		text patterns

        char[] in = text.toCharArray();
        for (int i = 0; i < in.length; i++) {
            result.addAll(match(root, in, i));
        }

        Collections.sort(result);        // write your code here

        return result;
    }

    private void addToTrie(Node root, int i, char[] in) {
        Node node = root;
        for (int j = 0; j < in.length; j++) {
            Integer currentSymbol = ind.get(in[j]);
            Node current = node.adj[currentSymbol];
            if (current == null) {
                current = new Node(in[j]);
                node.adj[currentSymbol] = current;
            }
            if (j == in.length - 1) {
                current.startsAt = i;
            }
            node = current;
        }
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String text = in.readLine();
            int n = Integer.parseInt(in.readLine());
            List<String> patterns = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                patterns.add(in.readLine());
            }

            List<Integer> ans = solve(text, n, patterns);

            for (int j = 0; j < ans.size(); j++) {
                System.out.print("" + ans.get(j));
                System.out.print(j + 1 < ans.size() ? " " : "\n");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Thread(new TrieMatchingExtended()).start();
    }
}
