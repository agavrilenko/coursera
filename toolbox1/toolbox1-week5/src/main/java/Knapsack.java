import java.util.Scanner;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int result = 0;
        int[] tmp = new int[w.length + 1];
        System.arraycopy(w, 0, tmp, 1, w.length);
        int[][] value = new int[W + 1][w.length + 1];
        for (int i = 0; i <= W; i++) {
            value[i][0] = 0;
        }
        for (int i = 0; i <= w.length; i++) {
            value[0][i] = 0;
        }

        for (int i = 1; i < tmp.length; i++) {
            for (int j = 1; j <= W; j++) {
                value[j][i] = value[j][i - 1];
                if (tmp[i] <= j) {
                    int val = value[j - tmp[i]][i - 1] + tmp[i];
                    if (value[j][i] < val) {
                        value[j][i] = val;
                    }
                }
            }
        }


        return value[W][w.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

