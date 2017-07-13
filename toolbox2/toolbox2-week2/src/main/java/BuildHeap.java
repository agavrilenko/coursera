import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    int[] data;
    List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    void generateSwaps() {
        swaps = new ArrayList<Swap>();
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            siftDown(data, i, swaps);
        }
    }

    private void siftDown(int[] data, int i, List<Swap> swaps) {

        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int minInd = i;

        if (l < data.length && data[l] < data[minInd]) {
            minInd = l;
        }
        if (r < data.length && data[r] < data[minInd]) {
            minInd = r;
        }
        if (i != minInd) {
            int tmp = data[i];
            data[i] = data[minInd];
            data[minInd] = tmp;
            swaps.add(new Swap(i, minInd));
            siftDown(data, minInd, swaps);
        }

//        int parent = (i - 1) / 2;
//        if (data[parent] > data[i]) {
//            int tmp = data[parent];
//            data[parent] = data[i];
//            data[i] = tmp;
//            siftDown(data, parent, swaps);
//        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Swap)) return false;

            Swap swap = (Swap) o;

            if (index1 != swap.index1) return false;
            return index2 == swap.index2;
        }

        @Override
        public int hashCode() {
            int result = index1;
            result = 31 * result + index2;
            return result;
        }

        @Override
        public String toString() {
            return "Swap{" +
                    "index1=" + index1 +
                    ", index2=" + index2 +
                    '}';
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
