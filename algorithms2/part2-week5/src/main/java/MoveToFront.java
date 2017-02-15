import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * Created by trash on 12-Feb-17.
 */
public class MoveToFront {
    public final static int R = 256;
    private char[] tmp = new char[256];
    private char[] pos = new char[256];

    private MoveToFront() {
        for (char i = 0; i < R; i++) {
            tmp[i] = i;
            pos[i] = i;
        }
    }

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encodeChar() {
        MoveToFront move = new MoveToFront();
        while (!BinaryStdIn.isEmpty()) {
            char c = move.encodeChar(BinaryStdIn.readChar());
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {

    }

    private char encodeChar(char c) {
        if (pos.length < 0) {
            throw new IllegalArgumentException("Character array is empty");
        }
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
    }
}
