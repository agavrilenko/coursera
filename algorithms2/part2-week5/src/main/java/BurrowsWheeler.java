import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

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

    public BurrowsWheeler() {
    }

    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        String s = BinaryStdIn.readString();
        BurrowsWheeler wheeler = new BurrowsWheeler();
        String encoded = wheeler.encode(s);
        BinaryStdOut.write(wheeler.first);
        BinaryStdOut.write(encoded);
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    private String encode(String s) {
        CircularSuffixArray circ = new CircularSuffixArray(s);
        char[] source = s.toCharArray();
        char[] result = new char[s.length()];
        for (int i = 0; i < result.length; i++) {
            int index = circ.index(i);
            if (index == 0) {
                result[i] = source[result.length - 1];
            } else {
                result[i] = source[index - 1];
            }
            if (index == 0) {
                first = i;
            }
        }

        StringBuilder out = new StringBuilder();
        out.append(result);
        return out.toString();
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        String original = decode(s, first);

        BinaryStdOut.write(original);
        BinaryStdOut.flush();
        BinaryStdOut.close();
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
        char ch;
        LinkedList<Integer>[] pos = (LinkedList<Integer>[]) Array.newInstance(LinkedList.class, 256);
        for (int i = 0; i < t.length; i++) {
            ch = t[i];
            if (pos[ch] != null) {
                pos[ch].addLast(i);
            } else {
                LinkedList<Integer> cur = new LinkedList<>();
                cur.addLast(i);
                pos[ch] = cur;
            }
        }
        for (int i = 0; i < t.length; i++) {
            ch = firstLetters[i];
            next[i] = pos[ch].pollFirst();
        }
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if (args[0].equals("+")) {
            decode();
        }
        if (args[0].equals("-")) {
            encode();
        }
    }
}
