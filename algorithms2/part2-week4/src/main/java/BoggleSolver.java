import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by trash on 07-Feb-17.
 */
public class BoggleSolver {

    private TST<Integer> tst = new TST<Integer>();
    private Map<Integer, Integer> score;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        initScore();
        for (String word : dictionary) {
            tst.put(word, 0);
        }
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
        boolean[][] visited;
        Set<String> allWords = new HashSet<>();
        for (int i = 0; i < board.cols(); i++) {
            for (int j = 0; j < board.rows(); j++) {
                visited = new boolean[board.cols()][board.rows()];
                visited[i][j] = true;
                testString(String.valueOf(board.getLetter(i, j)), new Cell(i, j), visited, board, allWords);
            }
        }
        return allWords;
    }

    // exits when all neighbours are visited or don't lead to new prefix
    // mark visited true for all cells in the path
    private void testString(String str, Cell cell, boolean[][] visited, BoggleBoard board, Set<String> allWords) {
        List<Cell> adj = adj(cell.x, cell.y, visited, board);
        visited[cell.x][cell.y] = true;
        for (Cell c : adj) {
            if (!visited[c.x][c.y]) {
                String nextStr = str + board.getLetter(c.x, c.y);
                if (tst.containsPrefix(nextStr)) {
                    if (nextStr.length() > 2 && tst.contains(nextStr)) {
                        allWords.add(nextStr);
                    }
                    testString(nextStr, c, copyOfVisited(visited, board), board, allWords);
                }
            }
        }
    }

    private boolean[][] copyOfVisited(boolean[][] visited, BoggleBoard board) {
        boolean[][] newVisited = new boolean[board.cols()][board.rows()];
        for (int i = 0; i < board.cols(); i++) {
            for (int j = 0; j < board.rows(); j++) {
                newVisited[i][j] = visited[i][j];
            }
        }
        return newVisited;
    }

    private List<Cell> adj(int x, int y, boolean[][] visited, BoggleBoard board) {
        List<Cell> adjacent = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && j >= 0 && i < board.cols() && j < board.rows() && !visited[i][j]) {
                    adjacent.add(new Cell(i, j));
                }
            }
        }
        return adjacent;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
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
                return c < x.c ? this.get(x.left, key, d) : (c > x.c ? this.get(x.right, key, d) : (d < key.length() - 1 ? this.get(x.mid, key, d + 1) : x));
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
                x = new Node();
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
                Node x = this.root;
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
            Queue queue = new Queue();
            this.collect(this.root, new StringBuilder(), queue);
            return queue;
        }

        public Iterable<String> keysWithPrefix(String prefix) {
            if (prefix == null) {
                throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
            } else {
                Queue queue = new Queue();
                Node x = this.get(this.root, prefix, 0);
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
            Queue queue = new Queue();
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

        public boolean containsPrefix(String query) {
            if (query == null) {
                throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
            } else if (query.length() == 0) {
                return false;
            } else {
                int length = 0;
                Node x = this.root;
                int i = 0;

                while (x != null && i < query.length()) {
                    char c = query.charAt(i);
                    if (c < x.c) {
                        x = x.left;
                    } else if (c > x.c) {
                        x = x.right;
                    } else {
                        ++i;
                        length = i;
                        x = x.mid;
                    }
                }
                return query.length() == length;
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

}
