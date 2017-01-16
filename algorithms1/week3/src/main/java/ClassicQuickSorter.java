/**
 * Created by trash on 16-Nov-16.
 */
public class ClassicQuickSorter {
    public void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j, hi);
    }

    private int partition(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return lo;
        }
        Comparable v = a[lo];
        int gt = hi;
        int lt = lo + 1;

        Comparable tmp ;
        while (gt >= lt) {
            int cmp = a[lt].compareTo(v);
            if (cmp > 0) {
                tmp = a[lt];
                a[lt] = a[gt];
                a[gt--] = tmp;
            } else {
                lt++;
            }
        }
        tmp = a[lo];
        a[lo] = a[gt];
        a[gt] = tmp;
        return gt;
    }
}
