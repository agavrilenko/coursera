import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class tree_height {
    static class FastScanner {
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

    public static class TreeHeight {
        int n;
        int parent[];

        public TreeHeight() {
        }

        public TreeHeight(int[] parent) {
            this.parent = parent;
        }

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeightFast() {
            if (parent == null) {
                return 0;
            }

            if (parent.length == 0) {
                return 0;
            }
            if (parent.length == 1 && parent[0] == -1) {
                return 1;
            }
            Node root = null;
            Node[] list = new Node[parent.length];
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == -1) {
                    if (list[i] != null) {
                        root = list[i];

                    } else {
                        root = new Node(i);
                        list[i] = root;
                    }
                    root.depth = 1;
                    continue;
                }
                Node next = list[i];
                if (next == null) {
                    next = new Node(i);
                    list[i] = next;
                }
                Node par = list[parent[i]];
                if (par == null) {
                    par = new Node(parent[i]);
                    list[parent[i]] = par;
                }
                par.children.addLast(next);
            }
            if (root == null) {
                return 0;
            }
            LinkedList<Node> queue = new LinkedList<>();
            queue.addLast(root);
            int height = 0;
            while (queue.size() > 0) {
                Node current = queue.removeFirst();
                height = current.depth > height ? current.depth : height;
                for (Node child : current.children) {
                    child.depth = current.depth + 1;
                    queue.add(child);
                }

            }
            return height;
        }

        private static class Node {
            int key;
            LinkedList<Node> children = new LinkedList<>();
            int depth;

            private Node(int key) {
                this.key = key;
            }
        }

        int computeHeight() {
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;
                for (int i = vertex; i != -1; i = parent[i])
                    height++;
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeightFast());
    }
}
