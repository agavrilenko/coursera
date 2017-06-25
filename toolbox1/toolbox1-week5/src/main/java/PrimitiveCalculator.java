import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrimitiveCalculator {
    static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence_adv(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }

    static List<Integer> optimal_sequence_adv(int n) {
        List<Integer> result = new ArrayList<>();
        Pair[] pairs = new Pair[n + 1];
        pairs[0] = new Pair(null, 0, 0, 0);
        for (int i = 1; i <= n; i++) {
            int hops = 0;
            Pair tmp;
            String op;
            int from;
            //-
            tmp = pairs[i - 1];
            hops = tmp.count + 1;
            from = i - 1;
            op = "+1";
            // /2
            if (i % 2 == 0) {
                int ind = i / 2;
                tmp = pairs[ind];
                if (tmp.count < hops) {
                    hops = tmp.count + 1;
                    from = ind;
                    op = "x2";
                }
            }
            // /3
            if (i % 3 == 0) {
                int ind = i / 3;
                tmp = pairs[ind];
                if (tmp.count < hops) {
                    hops = tmp.count + 1;
                    from = ind;
                    op = "x3";
                }
            }
            pairs[i] = new Pair(op, hops, from, i);

        }
        Pair tmp = pairs[n];
        result.add(tmp.ind);
        while (tmp.ind > 1) {
            tmp = pairs[tmp.from];
            result.add(tmp.ind);
        }
        Collections.reverse(result);
        return result;
    }

    static class Pair {
        String op;
        int count;
        int from;
        int ind;

        private Pair() {
        }

        private Pair(String op, int count, int from, int ind) {
            this.op = op;
            this.count = count;
            this.from = from;
            this.ind = ind;
        }
    }
}

