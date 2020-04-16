/**
 * Created by trash on 14-Nov-16.
 */
public class MergeSorter {
    private long cnt = 0;

    public void sort(Comparable[] a) {
        //aux is important to create it here. otherwise a big cost for re-creation
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
        System.out.println("count " + cnt);

    }

    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int med = lo + (hi - lo) / 2;
        sort(a, aux, lo, med);
        sort(a, aux, med + 1, hi);
        merge(a, aux, lo, med, hi);

    }

    private void merge(Comparable[] a, Comparable[] aux, int lo, int med, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = med + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > med) {
                a[k] = aux[j++];
                continue;
            }
            if (j > hi) {
                a[k] = aux[i++];
                continue;
            }
            if (aux[i].compareTo(aux[j]) > 0) {
//            if (greaterThan(aux[i], aux[j])) {
                a[k] = aux[j++];
                cnt++;
            } else {
                a[k] = aux[i++];
                cnt++;
            }

        }
    }

    private boolean greaterThan(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    public void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
        System.out.println("count " + cnt);

    }

    private void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int med = lo + (hi - lo) / 2;
        sort(a, aux, lo, med);
        sort(a, aux, med + 1, hi);
        merge(a, aux, lo, med, hi);

    }

    private void merge(int[] a, int[] aux, int lo, int med, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = med + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > med) {
                a[k] = aux[j++];
                continue;
            }
            if (j > hi) {
                a[k] = aux[i++];
                continue;
            }
//            if (aux[i].compareTo(aux[j]) > 0) {
            if (greaterThan(aux[i], aux[j])) {
                a[k] = aux[j++];
                cnt++;
            } else {
                a[k] = aux[i++];
                cnt++;
            }

        }
    }

    private boolean greaterThan(int a, int b) {
        return a > b;
    }
}
