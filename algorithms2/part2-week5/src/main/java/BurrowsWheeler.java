import edu.princeton.cs.algs4.BinaryStdIn;

import java.util.Arrays;

/**
 * Created by trash on 13-Feb-17.
 */
public class BurrowsWheeler {
    private int n;
    private char[] t;
    private int[] next;
    private int first;
    private char[] firstLetters;

    private BurrowsWheeler(int first, String source) {
        this.first = first;
        n = source.length();
        t = source.toCharArray();
        next = new int[t.length];
    }

    private BurrowsWheeler() {
    }

    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        String s = BinaryStdIn.readString();
        BurrowsWheeler wheeler = new BurrowsWheeler();
        String encoded = wheeler.encode(s);
    }

    private String encode(String s) {
        CircularSuffixArray circ = new CircularSuffixArray(s);
        char[] source = s.toCharArray();
        char[] result = new char[s.length()];
        int zero = 0;
        for (int i = 0; i < result.length; i++) {
            int index = circ.index(i);
            if (index == 0) {
                result[i] = source[result.length - 1];
            } else {
                result[i] = source[index - 1];
            }
            if (index == 0) {
                zero = i;
            }
        }


        String prefix = new String("" + zero);

        StringBuilder out = new StringBuilder("00000000".substring(0, 8 - prefix.length()) + prefix);
        out.append(result);
        return out.toString();
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        String s = BinaryStdIn.readString();
        int first = 0;
        String original = decode(s, first);

        System.out.println(original);

    }

    private static String decode(String s, int first) {
        BurrowsWheeler wheeler = new BurrowsWheeler(first, s);

        wheeler.buildNext();
        return wheeler.getOriginal();
    }

    private String getOriginal() {
        char[] original = new char[n];
        int nextSymb = next[first];
        for (int i = 0; i < n; i++) {
            original[i] = t[nextSymb];
            nextSymb = next[nextSymb];
        }
        return new String(original);
    }

    private void buildNext() {
        firstLetters = new char[t.length];
        System.arraycopy(t, 0, firstLetters, 0, t.length);
        Arrays.sort(firstLetters);
        boolean[] visited = new boolean[t.length];
        char ch;
        for (int i = 0; i < t.length; i++) {
            ch = firstLetters[i];
            for (int j = 0; j < t.length; j++) {
                if (next[j] > 0) {
//                    continue;
                }
                if (ch == t[j] && !visited[j]) {
                    next[i] = j;
                    visited[j] = true;
                    break;
                }
            }
        }
    }

    public int getLength() {
        return n;
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
    }
}
