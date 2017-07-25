import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class is_bst {
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

    public static class IsBST {

        static class Node {
            int key;
            int left;
            int right;
            int max;
            int min;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
                this.max = Integer.MIN_VALUE;
                this.min = Integer.MAX_VALUE;

            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                Node node = new Node(in.nextInt(), in.nextInt(), in.nextInt());
                tree[i] = node;
            }
        }

        boolean isBinarySearchTree() {
            if (tree.length < 1) {
                return true;
            }
            return isBinarySearchTree(tree[0]);
        }

        boolean isBinarySearchTree(Node node) {

            try {
                checkMax(tree[0]);
                checkMin(tree[0]);
            } catch (RuntimeException e) {
                if (e.getMessage().equals("INCORRECT")) {
                    return false;
                }
                throw e;
            }
            return true;
        }

        private int checkMax(Node node) {

            if (node.max > Integer.MIN_VALUE) {
                return node.max;
            }
            int left = node.left;
            int right = node.right;
            int key = node.key;
            int max = key;
            if (left != -1) {
                max = checkMax(tree[left]);
                if (max > key) {
                    throw new RuntimeException("INCORRECT");
                }
                max = max > key ? max : key;
            }

            if (right != -1) {
                max = checkMax(tree[right]);
                max = max > key ? max : key;
            }
            node.max = max;
            return max;
        }

        private int checkMin(Node node) {

            if (node.min < Integer.MAX_VALUE) {
                return node.min;
            }
            int left = node.left;
            int right = node.right;
            int key = node.key;
            int min = key;

            if (right != -1) {
                min = checkMin(tree[right]);
                if (key > min) {
                    throw new RuntimeException("INCORRECT");
                }
                min = min < key ? min : key;
            }

            if (left != -1) {
                min = checkMin(tree[left]);
                min = min < key ? min : key;
            }
            return min;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
