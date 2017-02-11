import edu.princeton.cs.algs4.Queue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by trash on 07-Feb-17.
 */
public class BoggleSolver {

        private TST<Integer> tst = new TST<Integer>();
//    private TrieST<Integer> tst = new TrieST<>();
    private Map<Integer, Integer> score;
    private Set<String> allWords;
    private BoggleBoard cachedBoard = new BoggleBoard();
//    private static long timingTST = 0;
//    private long timingAdj = 0;
//    private long timingLoad = 0;
//    private long total = 0;
//    private long timingRec = 0;
//    private long timingAdd = 0;
//    private static long timingCell = 0;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        initScore();
//        timingLoad = 0;
        long delta = System.currentTimeMillis();
        for (String word : dictionary) {
            tst.put(word, 0);
        }
//        timingLoad = timingLoad + System.currentTimeMillis() - delta;
    }

    private void initScore() {
        score = new HashMap<>();
        score.put(0, 0);
        score.put(1, 0);
        score.put(2, 0);
        score.put(3, 1);
        score.put(4, 1);
        score.put(5, 2);
        score.put(6, 3);
        score.put(7, 5);
        score.put(8, 11);
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
//        timingTST = 0;
//        timingAdj = 0;
//        timingRec = 0;
//        timingCell = 0;
//        total = System.currentTimeMillis();
        if (cachedBoard.equals(board)) {
            return allWords;
        }
        cachedBoard = board;
        boolean[][] visited;
        allWords = new HashSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                visited = new boolean[board.rows()][board.cols()];
                visited[i][j] = true;
                StringBuilder str = new StringBuilder(String.valueOf(board.getLetter(i, j)));
                if (str.toString().equals("Q")) {
                    str.append("U");
                }
                testString(str.toString(), new Cell(i, j), visited, board, allWords);
            }
        }
//        total = System.currentTimeMillis() - total;
//        System.out.print("Load = [" + timingLoad + "] ");
//        System.out.print("TST = [" + timingTST + "] ");
//        System.out.print("Adj = [" + timingAdj + "] ");
//        System.out.print("Rec = [" + timingRec + "] ");
//        System.out.print("Add = [" + timingAdd + "] ");
//        System.out.println("Total = [" + total + "] ");
        return allWords;
    }

    // exits when all neighbours are visited or don't lead to new prefix
    // mark visited true for all cells in the path
    private void testString(String str, Cell cell, boolean[][] visited, BoggleBoard board, Set<String> words) {
        visited[cell.x][cell.y] = true;
        List<Cell> adj = adj(cell.x, cell.y, visited, board);

        for (Cell c : adj) {
//            if (!visited[c.x][c.y]) {
//            long delta = System.currentTimeMillis();
            char currentLetter = board.getLetter(c.x, c.y);
//                StringBuilder nextStr = new StringBuilder(str).append(currentLetter);
            String query;
            if (currentLetter == 'Q') {
                query = new StringBuilder(str).append("QU").toString();
//                    nextStr.append('U');
            } else {
                query = new StringBuilder(str).append(currentLetter).toString();
            }
//                query = nextStr.toString();
//            timingRec = timingRec + System.currentTimeMillis() - delta;
            int result = tst.containsWord(query);
            if (result > -1) {
//                long deltaAdd = System.currentTimeMillis();
                if (result == 1 && query.length() > 2) {
                    words.add(query);
                }
//                timingAdd = timingAdd + System.currentTimeMillis() - deltaAdd;
                testString(query, c, visited, board, words);
            }
//            }
        }
        visited[cell.x][cell.y] = false;
    }

    private List<Cell> adj(int x, int y, boolean[][] visited, BoggleBoard board) {
        long delta = System.currentTimeMillis();
        List<Cell> adjacent = new LinkedList<>();

        if (x - 1 >= 0 && y - 1 >= 0 && x - 1 < board.rows() && y - 1 < board.cols() && !visited[x - 1][y - 1]) {
            adjacent.add(new Cell(x - 1, y - 1));
        }
        if (x - 1 >= 0 && y >= 0 && x - 1 < board.rows() && y < board.cols() && !visited[x - 1][y]) {
            adjacent.add(new Cell(x - 1, y));
        }
        if (x - 1 >= 0 && y + 1 >= 0 && x - 1 < board.rows() && y + 1 < board.cols() && !visited[x - 1][y + 1]) {
            adjacent.add(new Cell(x - 1, y + 1));
        }

        if (x >= 0 && y - 1 >= 0 && x < board.rows() && y - 1 < board.cols() && !visited[x][y - 1]) {
            adjacent.add(new Cell(x, y - 1));
        }
        if (x >= 0 && y + 1 >= 0 && x < board.rows() && y + 1 < board.cols() && !visited[x][y + 1]) {
            adjacent.add(new Cell(x, y + 1));
        }
        if (x + 1 >= 0 && y - 1 >= 0 && x + 1 < board.rows() && y - 1 < board.cols() && !visited[x + 1][y - 1]) {
            adjacent.add(new Cell(x + 1, y - 1));
        }
        if (x + 1 >= 0 && y >= 0 && x + 1 < board.rows() && y < board.cols() && !visited[x + 1][y]) {
            adjacent.add(new Cell(x + 1, y));
        }
        if (x + 1 >= 0 && y + 1 >= 0 && x + 1 < board.rows() && y + 1 < board.cols() && !visited[x + 1][y + 1]) {
            adjacent.add(new Cell(x + 1, y + 1));
        }
//        timingAdj = timingAdj + System.currentTimeMillis() - delta;
        return adjacent;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (tst.containsWord(word) != 1) {
            return 0;
        }
        return score.get(word.length() < 8 ? word.length() : 8);
    }

    private static class TST<Value> {
        private int n;
        private Node<Value> root;

        public TST() {
        }

        public int size() {
            return this.n;
        }

        public boolean contains(String key) {
            if (key == null) {
                throw new IllegalArgumentException("argument to contains() is null");
            } else {
                return this.get(key) != null;
            }
        }

        public Value get(String key) {
            if (key == null) {
                throw new IllegalArgumentException("calls get() with null argument");
            } else if (key.length() == 0) {
                throw new IllegalArgumentException("key must have length >= 1");
            } else {
                Node<Value> x = this.get(this.root, key, 0);
                return x == null ? null : x.val;
            }
        }

        private Node<Value> get(Node<Value> x, String key, int d) {
            if (x == null) {
                return null;
            } else if (key.length() == 0) {
                throw new IllegalArgumentException("key must have length >= 1");
            } else {
                char c = key.charAt(d);
                return c < x.c ? this.get(x.left, key, d) :
                        (c > x.c ? this.get(x.right, key, d) :
                                (d < key.length() - 1 ? this.get(x.mid, key, d + 1) : x));
            }
        }

        public void put(String key, Value val) {
            if (key == null) {
                throw new IllegalArgumentException("calls put() with null key");
            } else {
                if (!this.contains(key)) {
                    ++this.n;
                }

                this.root = this.put(this.root, key, val, 0);
            }
        }

        private Node<Value> put(Node<Value> x, String key, Value val, int d) {
            char c = key.charAt(d);
            if (x == null) {
                x = new Node<>();
                x.c = c;
            }

            if (c < x.c) {
                x.left = this.put(x.left, key, val, d);
            } else if (c > x.c) {
                x.right = this.put(x.right, key, val, d);
            } else if (d < key.length() - 1) {
                x.mid = this.put(x.mid, key, val, d + 1);
            } else {
                x.val = val;
            }

            return x;
        }

        public String longestPrefixOf(String query) {
            if (query == null) {
                throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
            } else if (query.length() == 0) {
                return null;
            } else {
                int length = 0;
                Node<Value> x = this.root;
                int i = 0;

                while (x != null && i < query.length()) {
                    char c = query.charAt(i);
                    if (c < x.c) {
                        x = x.left;
                    } else if (c > x.c) {
                        x = x.right;
                    } else {
                        ++i;
                        if (x.val != null) {
                            length = i;
                        }

                        x = x.mid;
                    }
                }

                return query.substring(0, length);
            }
        }

        public Iterable<String> keys() {
            Queue<String> queue = new Queue<>();
            this.collect(this.root, new StringBuilder(), queue);
            return queue;
        }

        public Iterable<String> keysWithPrefix(String prefix) {
            if (prefix == null) {
                throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
            } else {
                Queue<String> queue = new Queue<>();
                Node<Value> x = this.get(this.root, prefix, 0);
                if (x == null) {
                    return queue;
                } else {
                    if (x.val != null) {
                        queue.enqueue(prefix);
                    }

                    this.collect(x.mid, new StringBuilder(prefix), queue);
                    return queue;
                }
            }
        }

        private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
            if (x != null) {
                this.collect(x.left, prefix, queue);
                if (x.val != null) {
                    queue.enqueue(prefix.toString() + x.c);
                }

                this.collect(x.mid, prefix.append(x.c), queue);
                prefix.deleteCharAt(prefix.length() - 1);
                this.collect(x.right, prefix, queue);
            }
        }

        public Iterable<String> keysThatMatch(String pattern) {
            Queue<String> queue = new Queue<>();
            this.collect(this.root, new StringBuilder(), 0, pattern, queue);
            return queue;
        }

        private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
            if (x != null) {
                char c = pattern.charAt(i);
                if (c == 46 || c < x.c) {
                    this.collect(x.left, prefix, i, pattern, queue);
                }

                if (c == 46 || c == x.c) {
                    if (i == pattern.length() - 1 && x.val != null) {
                        queue.enqueue(prefix.toString() + x.c);
                    }

                    if (i < pattern.length() - 1) {
                        this.collect(x.mid, prefix.append(x.c), i + 1, pattern, queue);
                        prefix.deleteCharAt(prefix.length() - 1);
                    }
                }

                if (c == 46 || c > x.c) {
                    this.collect(x.right, prefix, i, pattern, queue);
                }

            }
        }

        public int containsWord(String query) {
            long delta = System.currentTimeMillis();
            if (query == null) {
                throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
            } else if (query.length() == 0) {
                return -1;
            } else {
                int length = 0;
                Node<Value> x = this.root;
                int i = 0;
                Value res = null;
                while (x != null && i < query.length()) {
                    char c = query.charAt(i);
                    if (c < x.c) {
                        x = x.left;
                    } else if (c > x.c) {
                        x = x.right;
                    } else {
                        ++i;
                        length = i;
                        res = x.val;
                        x = x.mid;
                    }
                }
//                timingTST = timingTST + System.currentTimeMillis() - delta;
                return query.length() != length ? -1 : res == null ? 0 : 1;
            }
        }


        private static class Node<Value> {
            private char c;
            private Node<Value> left;
            private Node<Value> mid;
            private Node<Value> right;
            private Value val;

            private Node() {
            }
        }
    }

    private static class Cell {
        private int x, y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    /* *************** */

    private static class TrieST<Value> {
        private static final int R = 26;
        private Node root;
        private int n;

        public TrieST() {
        }

//        public Value get(String key) {
//            TrieST.Node<Value> x = this.get(this.root, key, 0);
//            return x == null ? null : x.val;
//        }

//        public boolean contains(String key) {
//            return this.get(key) != null;
//        }

        public int containsWord(String query) {
            long delta = System.currentTimeMillis();
            // int length = this.longestPrefixOf(this.root, query, 0, -1);
            if (query == null) {
                throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
            } else if (query.length() == 0) {
                return -1;
            } else {
                int length = 0;
                int i = 0;
                Node<Value> x = this.root;
                Value res = null;
                while (x != null && i < query.length()) {
                    char c = query.charAt(i);
                    x = x.next[c - 65];
                    if (x != null) {
                        res = x.val;
                    } else {
                        res = null;
                    }
                    ++i;
                    length = i;
                }
//                timingTST = timingTST + System.currentTimeMillis() - delta;
                return res != null ? 1 : length != query.length() ? -1 : 0;
            }
        }

//        private Node<Value> get(Node<Value> x, String key, int d) {
//            if (x == null) {
//                return null;
//            } else if (d == key.length()) {
//                return x;
//            } else {
//                char c = key.charAt(d);
//                return this.get(x.next[c], key, d + 1);
//            }
//        }

        public void put(String key, Value val) {
//            if (val == null) {
//                this.delete(key);
//            } else {
            this.root = this.put(this.root, key, val, 0);
//            }

        }

        private Node put(Node x, String key, Value val, int d) {
            if (x == null) {
                x = new Node();
            }

            if (d == key.length()) {
                if (x.val == null) {
                    ++this.n;
                }

                x.val = val;
                return x;
            } else {
                char c = key.charAt(d);
                x.next[c - 65] = this.put(x.next[c - 65], key, val, d + 1);
                return x;
            }
        }

        public int size() {
            return this.n;
        }

        public boolean isEmpty() {
            return this.size() == 0;
        }

//        public Iterable<String> keys() {
//            return this.keysWithPrefix("");
//        }
//
//        public Iterable<String> keysWithPrefix(String prefix) {
//            Queue results = new Queue();
//            Node x = this.get(this.root, prefix, 0);
//            this.collect(x, new StringBuilder(prefix), results);
//            return results;
//        }

//        private void collect(Node x, StringBuilder prefix, Queue<String> results) {
//            if (x != null) {
//                if (x.val != null) {
//                    results.enqueue(prefix.toString());
//                }
//
//                for (char c = 0; c < 26; ++c) {
//                    prefix.append(c);
//                    this.collect(x.next[c], prefix, results);
//                    prefix.deleteCharAt(prefix.length() - 1);
//                }
//
//            }
//        }

//        public Iterable<String> keysThatMatch(String pattern) {
//            Queue results = new Queue();
//            this.collect(this.root, new StringBuilder(), pattern, results);
//            return results;
//        }

//        private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
//            if (x != null) {
//                int d = prefix.length();
//                if (d == pattern.length() && x.val != null) {
//                    results.enqueue(prefix.toString());
//                }
//
//                if (d != pattern.length()) {
//                    char c = pattern.charAt(d);
//                    if (c == 46) {
//                        for (char ch = 0; ch < 26; ++ch) {
//                            prefix.append(ch);
//                            this.collect(x.next[ch], prefix, pattern, results);
//                            prefix.deleteCharAt(prefix.length() - 1);
//                        }
//                    } else {
//                        prefix.append(c);
//                        this.collect(x.next[c], prefix, pattern, results);
//                        prefix.deleteCharAt(prefix.length() - 1);
//                    }
//
//                }
//            }
//        }

//        public String longestPrefixOf(String query) {
//            int length = this.longestPrefixOf(this.root, query, 0, -1);
//            return length == -1 ? null : query.substring(0, length);
//        }
//
//        private int longestPrefixOf(Node x, String query, int d, int length) {
//            if (x == null) {
//                return length;
//            } else {
//                if (x.val != null) {
//                    length = d;
//                }
//
//                if (d == query.length()) {
//                    return length;
//                } else {
//                    char c = query.charAt(d);
//                    return this.longestPrefixOf(x.next[c], query, d + 1, length);
//                }
//            }
//        }

//        public void delete(String key) {
//            this.root = this.delete(this.root, key, 0);
//        }


        private static class Node<Value> {
            private Value val;
            private Node[] next;

            private Node() {
                this.next = new Node[26];
            }
        }
    }


}
