import java.util.Scanner;

import static java.lang.Math.min;

class EditDistance {
    static int EditDistance(String s, String t) {
        //write your code here

        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        int[][] D = new int[S.length + 1][T.length + 1];
        for (int i = 0; i < D.length; i++) {
            D[i][0] = i;
        }
        for (int i = 0; i < D[0].length; i++) {
            D[0][i] = i;
        }

        for (int j = 1; j < D[0].length; j++) {
            for (int i = 1; i < D.length; i++) {
                int ins = D[i][j - 1] + 1;
                int del = D[i - 1][j] + 1;
                int match = D[i - 1][j - 1];
                int mismatch = D[i - 1][j - 1] + 1;
                if (S[i - 1] == T[j - 1]) {
                    D[i][j] = min(ins, min(del, match));
                } else {
                    D[i][j] = min(ins, min(del, mismatch));
                }
            }
        }
        return D[S.length][T.length];
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
