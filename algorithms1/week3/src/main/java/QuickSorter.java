import java.util.Comparator;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Created by trash on 14-Nov-16.
 */
public class QuickSorter {

    public QuickSorter() {
    }

    public void sort(Comparable[] a) {
        if (a.length <= 1) {
            return;
        }
        sort(a, null);

    }

    public void sort(Object[] a, Comparator c) {
        if (a.length <= 1) {
            return;
        }
        shuffle(a);

        if (c == null && a instanceof Comparable[]) {
            sort(a, 0, a.length - 1, (Object x, Object y) -> ((Comparable) x).compareTo(y));
        } else if (c != null) {
            sort(a, 0, a.length - 1, (Object x, Object y) -> c.compare(x, y));
        }

    }

    private void sort(Object[] a, int lo, int hi, BiFunction<Object, Object, Integer> f) {

        if (lo >= hi) {
            return;
        }
        int i = lo;
        int gt = hi;
        int lt = lo;

        Object v = a[lo];
        Object tmp;
        while (i <= gt) {
            int cmp = f.apply(a[i], v);
            if (cmp > 0) {
                tmp = a[gt];
                a[gt--] = a[i];
                a[i] = tmp;
            } else if (cmp < 0) {
                tmp = a[lt];
                a[lt++] = a[i];
                a[i++] = tmp;
            } else {
                i++;
            }

        }
        sort(a, lo, lt - 1, f);
        sort(a, gt + 1, hi, f);

    }

    private void shuffle(Object[] toSort) {
        Random rnd = new Random(1341234314);
        for (int i = toSort.length; i > 1; i--) {
            int index = rnd.nextInt(i);
            Object tmp = toSort[i - 1];
            toSort[i - 1] = toSort[index];
            toSort[index] = tmp;

        }
    }

}
