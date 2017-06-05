import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciSumLastDigit {
    static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;
        long sum = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    static long getFibonacciSumAdv(long n) {
        if (n <= 1)
            return n;

        List<Long> cycle = findCycle(130, 10);
        long sum = 0;
        for (long i = 0; i <= n; i++) {
            sum += cycle.get((int) (i % cycle.size())) % 10;
            sum = sum % 10;
        }
        return sum;
    }

    private static List<Long> findCycle(long n, long m) {
        ArrayList<Long> cycle = new ArrayList<>();
        ArrayList<Long> cycleTest = new ArrayList<>();
        int cLen = 0, cInd = 0;
        long previous = 0;
        long current = 1;

        cycle.add(previous);


        for (long i = 1; i < n; i++) {
            cycle.add(current);
            long tmp_previous = previous % m;
            previous = current % m;
            if (cycle.get(cInd).equals(current)) {
                cInd++;
                cLen++;
                cycleTest.add(current);
                if (cLen * 2 == i + 1) {
                    return cycleTest;
                }
            } else {
                cInd = 0;
                cLen = 0;
                cycleTest = new ArrayList<>();
            }
            current = (tmp_previous + current) % m;
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumAdv(n);
        System.out.println(s);
    }
}

