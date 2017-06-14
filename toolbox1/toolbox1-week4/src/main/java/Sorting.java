import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Sorting {
    private static Random random = new Random();

    static int[] partition3(int[] a, int l, int r) {
        //write your code here


        int m1 = l;
        int m2 = r;
        int[] m = {m1, m2};
        return m;
    }

    static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    static void randomizedQuickSort3(int[] a) {
        shuffle(a);
        randomizedQuickSort3(a, 0, a.length - 1);
    }

    static void randomizedQuickSort3(int[] a, int l, int r) {
        if (l == r) {
            return;
        }
        int pivot = a[l];
        int left = l + 1, low = l, right = r;
        int tmp;
        while (left <= right) {
            if (pivot == a[left]) {
                left++;
                continue;
            }
            if (pivot < a[left]) {
                tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
                right--;
                continue;
            }
            if (pivot > a[left]) {
                tmp = a[left];
                a[left] = a[low];
                a[low] = tmp;
                left++;
                low++;
                continue;
            }
        }
        if (low > l) {
            randomizedQuickSort3(a, l, low - 1);
        }
        if (r > left) {
            randomizedQuickSort3(a, left, r);
        }
    }

    public static void shuffle(int[] toSort) {
        Random rnd = new Random(1341234314);
        for (int i = toSort.length; i > 1; i--) {
            int index = rnd.nextInt(i);
            Integer tmp = toSort[i - 1];
            toSort[i - 1] = toSort[index];
            toSort[index] = tmp;

        }
    }

    static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int m = partition2(a, l, r);
        randomizedQuickSort(a, l, m - 1);
        randomizedQuickSort(a, m + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort3(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

