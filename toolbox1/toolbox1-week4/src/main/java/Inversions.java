import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Inversions {


    static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        //write your code here
        return numberOfInversions;
    }

    static long getNumberOfInversions(int a[]) {
        int low = 0, high = a.length - 1;
        int middle = (low + high) / 2;
        AtomicLong count = new AtomicLong(0L);
        int[] left = merge(a, low, middle, count);
        int[] right = merge(a, middle + 1, high, count);
        int[] sorted = sort(left, right, count);

        return count.get();
    }

    private static int[] sort(int[] left, int[] right, AtomicLong count) {
        int size = left.length + right.length;
        int l = 0, r = 0, i = 0;
        int[] result = new int[size];
        while (l < left.length && r < right.length) {
            if (left[l] > right[r]) {
                count.addAndGet(left.length - l);
                r++;
                continue;
            }
            l++;
        }
        l = 0;
        r = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                result[i++] = left[l++];
                continue;
            }
            if (left[l] == right[r]) {
                result[i++] = left[l++];
                result[i++] = right[r++];
                continue;
            }
            if (left[l] > right[r]) {
                result[i++] = right[r++];
                continue;
            }
        }
        if (l < left.length) {
            System.arraycopy(left, l, result, l + r, left.length - l);
        }
        if (r < right.length) {
            System.arraycopy(right, r, result, l + r, right.length - r);

        }
        return result;
    }

    private static int[] merge(int[] a, int low, int high, AtomicLong count) {
        if (low == high) {
            return new int[]{a[low]};
        }
        int middle = (low + high) / 2;
        int[] left = merge(a, low, middle, count);
        int[] right = merge(a, middle + 1, high, count);
        int[] sorted = sort(left, right, count);
        return sorted;
    }

    static long getNumberOfInversionsSimple(int[] a) {
        long invs = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    invs++;
                }
            }
        }
        return invs;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a));
    }
}

