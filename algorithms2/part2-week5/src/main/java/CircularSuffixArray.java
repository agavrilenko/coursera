/**
 * Created by trash on 13-Feb-17.
 */
public class CircularSuffixArray {
    private int n;
    private int[] ranks;
    private boolean fake;

    // circular suffix array of s
    // rank[i] - 1 index of symbol after encoding
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new NullPointerException("Incoming string is null");
        }
        init(s);

    }

    private void init(String s) {
        n = s.length();
        ranks = new int[n];
        char[] source = s.toCharArray();
        int[] aux = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ranks[i] = i;
            aux[i] = i;
        }
        sort(aux, source, ranks, 0, s.length() - 1, 0);
    }

    // length of s
    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException();
        }
        return ranks[i];
    }

    private static void sort(int[] aux, char[] source, int[] ind) {

        sort(aux, source, ind, 0, source.length - 1, 0);


    }

    public static void sort(int[] aux, char[] source, int[] ind, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }
        if (d > source.length) {
            return;
        }
        int lt = lo, gt = hi;
        int v = source[aux[lo]];
        int i = lo + 1;
        while (i <= gt) {
            int t = source[aux[i]];
            if (t < v) {
                exch(aux, lt++, i++, ind);
            } else if (t > v) {
                exch(aux, i, gt--, ind);
            } else {
                i++;
            }
        }

        sort(aux, source, ind, lo, lt - 1, d);
        if (v >= 0) {
            for (int j = lt; j <= gt; j++) {
                if (aux[j] == source.length - 1) {
                    aux[j] = 0;
                } else {
                    aux[j]++;
                }
            }
            sort(aux, source, ind, lt, gt, d + 1);
        }
        sort(aux, source, ind, gt + 1, hi, d);
    }

    public static void exch(int[] aux, int lo, int hi, int[] ind) {
        int t;
        t = aux[lo];
        aux[lo] = aux[hi];
        aux[hi] = t;

        t = ind[lo];
        ind[lo] = ind[hi];
        ind[hi] = t;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }

}


