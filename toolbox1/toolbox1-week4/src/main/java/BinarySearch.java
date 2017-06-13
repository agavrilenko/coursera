import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch {

    static int binarySearch(int[] a, int x) {
        if (a.length == 0) {
            return -1;
        }
        if (a.length == 1) {
            return a[0] == x ? 0 : -1;
        }
        int left = 0, right = a.length - 1;
        //write your code here
        return find(a, x, left, right);
    }

    private static int find(int[] a, int x, int left, int right) {
        if (left == right) {
            return a[left] == x ? left : -1;
        }
        if (left - right == 1 || right - left == 1) {
            if (a[left] == x) {
                return left;
            }
            if (a[right] == x) {
                return right;
            }
            return -1;
        }
        int middle = (left + right) / 2;
        if (a[middle] == x) {
            return middle;
        }
        if (a[middle] < x) {
            return find(a, x, middle + 1, right);
        }
        return find(a, x, left, middle - 1);
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(binarySearch(a, b[i]) + " ");
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
