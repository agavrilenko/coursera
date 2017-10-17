import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class InverseBWT {
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

    long timeSpent = 0;

    private static class Pair {

        char c;
        int ind;

        public Pair(char c, int ind) {
            this.c = c;
            this.ind = ind;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;

            Pair pair = (Pair) o;

            if (c != pair.c) return false;
            return ind == pair.ind;
        }

        @Override
        public int hashCode() {
            int result = (int) c;
            result = 31 * result + ind;
            return result;
        }
    }

    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();

        char[] bw = bwt.toCharArray();

        Map<Pair, Integer> map = new HashMap<>(bw.length);
        int[] aux = new int[5];

        int curInd;
        for (int i = 0; i < bw.length; i++) {
            char c = bw[i];
            map.put(new Pair(c, aux[getIndex(c)]++), i);
        }
        char current = '$';
        Pair symbol = new Pair('$', 0);
        int tmp;
        int ind = 0;
        while (result.length() < bw.length) {
            //get char in sorted array
            curInd = map.get(symbol);
            tmp = curInd + 1;
            for (int i = 0; i < aux.length; i++) {
                if (tmp > aux[i]) {
                    tmp = tmp - aux[i];
                    continue;
                }
                current = getChar(i);
                ind = tmp - 1;
                break;
            }
            result.append(current);
            symbol = new Pair(current, ind);

        }

        return result.toString();
    }

    private int getIndex(char in) {
        switch (in) {
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

    private char getChar(int ind) {
        switch (ind) {
            case 0:
                return '$';
            case 1:
                return 'A';
            case 2:
                return 'C';
            case 3:
                return 'G';
            case 4:
                return 'T';
            default:
                return '$';
        }
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));

    }
}
