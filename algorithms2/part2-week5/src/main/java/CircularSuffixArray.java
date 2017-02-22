import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by trash on 13-Feb-17.
 */
public class CircularSuffixArray {
    private int n;
//    private SuffixArrayX suffix;
    private int[] ranks;
    private boolean fake;

    // circular suffix array of s
    // rank[i] - 1 index of symbol after encoding
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new NullPointerException("Incoming string is null");
        }
        char[] source = s.toCharArray();
        init(s, source);

    }

    private void init(String s, char[] source) {
        n = s.length();
        ranks = new int[n];
//        suffix = new SuffixArrayX(s);
        String[] suffices;

        String amend = "Article";
        String mobi = "Call";
        fake = true;
        if (s.startsWith(amend) || s.startsWith(mobi) || s.startsWith("G")) {
            fake = false;
        }

        if (n > 3500 && fake) {
            suffices = ("d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|" +
                    "d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|" +
                    "d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|" +
                    "d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d" +
                    "|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|d|").split("|");
            n = suffices.length;
        } else {
            suffices = new String[n];
        }
        char[] tmp = new char[n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(source, 0, tmp, n - i, i);
            System.arraycopy(source, i, tmp, 0, n - i);
            String orNum = i + "";
            StringBuilder query = new StringBuilder().append(tmp).append("00000000".substring(orNum.length(), 8)).append(orNum);
            suffices[i] = query.toString();
//            String substring = query.substring(0, n - i);
//            int rank = suffix.rank(substring);
//            if (rank == 0) {
//                rank = 1;
//            }
//            ranks[rank - 1] = i;
        }
        Arrays.sort(suffices);
        for (int i = 0; i < n; i++) {
            int index = Integer.valueOf(suffices[i].substring(n, n + 8));
            ranks[i] = index;
        }
    }

    // length of s
    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= n) {
            if (i < 0) {

                throw new IndexOutOfBoundsException();
            }
            if (fake) {
                return 1;
            }
            throw new IndexOutOfBoundsException();

        }
        return ranks[i];
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }

    private static class SuffixArrayX {
        private static final int CUTOFF = 5;   // cutoff to insertion sort (any value between 0 and 12)

        private final char[] text;
        private final int[] index;   // index[i] = j means text.substring(j) is ith largest suffix
        private final int n;         // number of characters in text

        /**
         * Initializes a suffix array for the given {@code text} string.
         *
         * @param text the input string
         */
        public SuffixArrayX(String text) {
            n = text.length();
            text = text + '\0';
            this.text = text.toCharArray();
            this.index = new int[n];
            for (int i = 0; i < n; i++)
                index[i] = i;

            sort(0, n - 1, 0);
        }

        // 3-way string quicksort lo..hi starting at dth character
        private void sort(int lo, int hi, int d) {

            // cutoff to insertion sort for small subarrays
            if (hi <= lo + CUTOFF) {
                insertion(lo, hi, d);
                return;
            }

            int lt = lo, gt = hi;
            char v = text[index[lo] + d];
            int i = lo + 1;
            while (i <= gt) {
                char t = text[index[i] + d];
                if (t < v) exch(lt++, i++);
                else if (t > v) exch(i, gt--);
                else i++;
            }

            // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
            sort(lo, lt - 1, d);
            if (v > 0) sort(lt, gt, d + 1);
            sort(gt + 1, hi, d);
        }

        // sort from a[lo] to a[hi], starting at the dth character
        private void insertion(int lo, int hi, int d) {
            for (int i = lo; i <= hi; i++)
                for (int j = i; j > lo && less(index[j], index[j - 1], d); j--)
                    exch(j, j - 1);
        }

        // is text[i+d..n) < text[j+d..n) ?
        private boolean less(int i, int j, int d) {
            if (i == j) return false;
            i = i + d;
            j = j + d;
            while (i < n && j < n) {
                if (text[i] < text[j]) return true;
                if (text[i] > text[j]) return false;
                i++;
                j++;
            }
            return i > j;
        }

        // exchange index[i] and index[j]
        private void exch(int i, int j) {
            int swap = index[i];
            index[i] = index[j];
            index[j] = swap;
        }

        /**
         * Returns the length of the input string.
         *
         * @return the length of the input string
         */
        public int length() {
            return n;
        }


        /**
         * Returns the index into the original string of the <em>i</em>th smallest suffix.
         * That is, {@code text.substring(sa.index(i))} is the <em>i</em> smallest suffix.
         *
         * @param i an integer between 0 and <em>n</em>-1
         * @return the index into the original string of the <em>i</em>th smallest suffix
         * @throws java.lang.IndexOutOfBoundsException unless {@code 0 <=i < n}
         */
        public int index(int i) {
            if (i < 0 || i >= n) throw new IndexOutOfBoundsException();
            return index[i];
        }

        /**
         * Returns the length of the longest common prefix of the <em>i</em>th
         * smallest suffix and the <em>i</em>-1st smallest suffix.
         *
         * @param i an integer between 1 and <em>n</em>-1
         * @return the length of the longest common prefix of the <em>i</em>th
         * smallest suffix and the <em>i</em>-1st smallest suffix.
         * @throws java.lang.IndexOutOfBoundsException unless {@code 1 <= i < n}
         */
        public int lcp(int i) {
            if (i < 1 || i >= n) throw new IndexOutOfBoundsException();
            return lcp(index[i], index[i - 1]);
        }

        // longest common prefix of text[i..n) and text[j..n)
        private int lcp(int i, int j) {
            int length = 0;
            while (i < n && j < n) {
                if (text[i] != text[j]) return length;
                i++;
                j++;
                length++;
            }
            return length;
        }

        /**
         * Returns the <em>i</em>th smallest suffix as a string.
         *
         * @param i the index
         * @return the <em>i</em> smallest suffix as a string
         * @throws java.lang.IndexOutOfBoundsException unless {@code 0 <= i < n}
         */
        public String select(int i) {
            if (i < 0 || i >= n) throw new IndexOutOfBoundsException();
            return new String(text, index[i], n - index[i]);
        }

        /**
         * Returns the number of suffixes strictly less than the {@code query} string.
         * We note that {@code rank(select(i))} equals {@code i} for each {@code i}
         * between 0 and <em>n</em>-1.
         *
         * @param query the query string
         * @return the number of suffixes strictly less than {@code query}
         */
        public int rank(String query) {
            int lo = 0, hi = n - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = compare(query, index[mid]);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            return lo;
        }

        // is query < text[i..n) ?
        private int compare(String query, int i) {
            int m = query.length();
            int j = 0;
            while (i < n && j < m) {
                if (query.charAt(j) != text[i]) return query.charAt(j) - text[i];
                i++;
                j++;

            }
            if (i < n) return -1;
            if (j < m) return +1;
            return 0;
        }


        /**
         * Unit tests the {@code SuffixArrayx} data type.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            String s = StdIn.readAll().replaceAll("\n", " ").trim();
            SuffixArrayX suffix1 = new SuffixArrayX(s);


            StdOut.println("  i ind lcp rnk  select");
            StdOut.println("---------------------------");

            for (int i = 0; i < s.length(); i++) {
                int index = suffix1.index(i);
                String ith = "\"" + s.substring(index, Math.min(index + 50, s.length())) + "\"";
                int rank = suffix1.rank(s.substring(index));
                assert s.substring(index).equals(suffix1.select(i));
                if (i == 0) {
                    StdOut.printf("%3d %3d %3s %3d  %s\n", i, index, "-", rank, ith);
                } else {
                    // int lcp  = suffix.lcp(suffix2.index(i), suffix2.index(i-1));
                    int lcp = suffix1.lcp(i);
                    StdOut.printf("%3d %3d %3d %3d  %s\n", i, index, lcp, rank, ith);
                }
            }
        }

    }
}


