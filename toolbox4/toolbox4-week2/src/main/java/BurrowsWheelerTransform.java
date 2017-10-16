import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BurrowsWheelerTransform {

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

    String BWT(String text) {
        StringBuilder result = new StringBuilder();

        char[] in = text.toCharArray();
        int[] order = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            order[i] = i;
        }

        sort(order, in);
        for (int i = 0; i < order.length; i++) {
            result.append(getCharAtPosition(in, order[i], in.length - 1));
        }
        return result.toString();
    }

    private void sort(int[] order, char[] in) {

        int from = 0;
        int to = order.length;
        int depth = 0;
        int[] previousOrder = new int[order.length];
        sort(order, in, from, to, depth, previousOrder);

    }

    //$-0, A-1, C-2, G-3, T-4
    private void sort(int[] order, char[] in, int from, int to, int depth, int[] previousOrder) {

        if (to - from <= 1) {
            return;
        }
        int[] aux = new int[5];

        for (int i = from; i < to; i++) {
            char at = getCharAtPosition(in, order[i], depth);
            previousOrder[i] = order[i];
            aux[getIndex(at)]++;
        }
        //needs 1 position more than in aux array
        int[] offset = new int[aux.length + 1];
        offset[0] = from;

        for (int i = 0; i < aux.length; i++) {
            offset[i + 1] = offset[i] + aux[i];
        }

        for (int i = from; i < to; i++) {
            char at = getCharAtPosition(in, previousOrder[i], depth);
            int index = getIndex(at);
            int newIndex = offset[index]++;
            order[newIndex] = previousOrder[i];
        }
        int count = from;
        for (int i = 0; i < aux.length; i++) {
            if (aux[i] > 0) {
                sort(order, in, count, count + aux[i], depth + 1, previousOrder);
                count += aux[i];
            }
        }

    }

    private int getIndex(char at) {
        switch (at) {
            case '$':
                return 0;
            case 'A':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
            case 'T':
                return 4;
            default:
                return 0;

        }

    }

    private char getCharAtPosition(char[] in, int line, int pos) {

        char c = in[(-line + pos + in.length - 1) % in.length];

        return c;
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
