import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * Created by trash on 12-Feb-17.
 */
public class MoveToFront {
    private static final int R = 256;
    private char[] tmp = new char[256];
    private char[] pos = new char[256];

    public MoveToFront() {
        for (char i = 0; i < R; i++) {
            tmp[i] = i;
            pos[i] = i;
        }
    }

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        MoveToFront move = new MoveToFront();
        String source = BinaryStdIn.readString();
        char[] src = source.toCharArray();
        char[] out = new char[src.length];
        for (int i = 0; i < src.length; i++) {
            char c = move.encode(src[i]);
            out[i] = c;
        }
        BinaryStdOut.write(new String(out));
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        MoveToFront move = new MoveToFront();
        String source = BinaryStdIn.readString();
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = move.decodeChar(chars[i]);
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    private char encode(char c) {
//        if (pos.length < 0) {
//            throw new IllegalArgumentException("Character array is empty");
//        }
        char temp = pos[0];
        for (char i = 0; i < pos.length; i++) {
            char ch = pos[i];
            pos[i] = temp;
            if (ch == c) {
                pos[0] = ch;
                return i;
            }
            temp = ch;
        }
        return 0;
    }

    private char decodeChar(int p) {
        if (pos.length < p) {
            throw new IllegalArgumentException("Character array is empty");
        }
        char ch = pos[p];
        System.arraycopy(pos, 0, pos, 1, p);
        pos[0] = ch;
        return ch;

    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        }
        if (args[0].equals("+")) {
            decode();
        }
    }
}
