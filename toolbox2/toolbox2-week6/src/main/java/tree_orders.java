import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class tree_orders {
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

    private static class Node {
        private Node left, right;
        private int key;

        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public Node(int key) {
            this.key = key;
        }


    }

    public static class TreeOrders {
        int n;
        int[] key, left, right;
        Node[] nodes;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }

        }

        public void build() {

            nodes = new Node[n];

            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(key[i]);
            }
            for (int i = 0; i < n; i++) {
                int ind = right[i];
                if (ind != -1) {
                    nodes[i].right = nodes[ind];
                } else {
                    nodes[i].right = null;
                }
                ind = left[i];
                if (ind != -1) {
                    nodes[i].left = nodes[ind];
                } else {
                    nodes[i].left = null;
                }
            }
            key = null;
            left = null;
            right = null;
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            inOrder(nodes[0], result);

            return result;
        }

        private void inOrder(Node node, List<Integer> result) {

            if (node == null) {
                return;
            }
            inOrder(node.left, result);
            result.add(node.key);
            inOrder(node.right, result);
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            preOrder(nodes[0], result);
            return result;
        }

        private void preOrder(Node node, List<Integer> result) {

            if (node == null) {
                return;
            }
            result.add(node.key);
            preOrder(node.left, result);
            preOrder(node.right, result);
        }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            postOrder(nodes[0], result);
            return result;
        }

        private void postOrder(Node node, List<Integer> result) {

            if (node == null) {
                return;
            }
            postOrder(node.left, result);
            postOrder(node.right, result);
            result.add(node.key);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        tree.build();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
