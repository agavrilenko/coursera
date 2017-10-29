import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SuffixArrayLong {

    public static final int SIZE = 255;

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

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        int[] order;

        char[] s = text.toCharArray();
        order = sortCharacters(s);
        int[] classes = computeCharClasses(s, order);
        int l = 1;
        while (l < s.length) {

            order = sortDoubled(s, l, order, classes);
            classes = updateClasses(order, classes, l);
            l *= 2;
        }
        return order;
    }

    private int[] updateClasses(int[] newOrder, int[] classes, int l) {
        int n = newOrder.length;
        int newClass[] = new int[n];
        newClass[newOrder[0]] = 0;
        for (int i = 1; i < n; i++) {
            int cur = newOrder[i];
            int prev = newOrder[i - 1];
            int mid = (cur + l);
            int midPrev = (prev + l) % n;

            if (classes[cur] != classes[prev] || classes[mid] != classes[midPrev]) {
                newClass[cur] = newClass[prev] + 1;
            } else {
                newClass[cur] = newClass[prev];
            }

        }
        return newClass;
    }

    private int[] sortDoubled(char[] s, int l, int[] order, int[] classes) {

        int[] count = new int[s.length];
        int[] newOrder = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            count[classes[i]] = count[classes[i]] + 1;
        }
        for (int i = 1; i < s.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        for (int i = s.length - 1; i >= 0; i--) {
            int start = (order[i] - l + s.length) % s.length;
            int cl = classes[start];
            count[cl] = count[cl] - 1;
            newOrder[count[cl]] = start;
        }
        return newOrder;
    }

    private int[] computeCharClasses(char[] s, int[] order) {
        int[] classes = new int[s.length];
        classes[order[0]] = 0;

        for (int i = 1; i < s.length; i++) {

            if (s[order[i]] != s[order[i - 1]]) {
                classes[order[i]] = classes[order[i - 1]] + 1;

            } else {
                classes[order[i]] = classes[order[i - 1]];
            }

        }
        return classes;
    }

    private int[] sortCharacters(char[] s) {
        int order[] = new int[s.length];
        int count[] = new int[SIZE];
        for (int i = 0; i < s.length; i++) {
            count[s[i]]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        for (int i = order.length - 1; i >= 0; i--) {
            char c = s[i];
            count[c] = count[c] - 1;
            order[count[c]] = i;
        }
        return order;
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

    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
