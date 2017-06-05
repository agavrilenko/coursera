import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciHuge {
    static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }

    static long getFibonacciHugeAdv(long n, long m) {
        if (n <= 1) {
            return n;
        }
        if (m == 1) {
            return 0;
        }
        List<Long> cycle = findCycle(n, m);
        if (cycle.size() != 0) {
            return cycle.get((int) (n % cycle.size()));
        }
        long previous = 0;
        long current = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous % m;
            previous = current % m;
            current = (tmp_previous + current) % m;
        }
        return current;
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
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeAdv(n, m));
    }
}

