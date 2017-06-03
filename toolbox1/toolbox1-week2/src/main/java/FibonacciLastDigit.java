import java.util.Scanner;

public class FibonacciLastDigit {
    static long getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;

        for (int i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }

    static int getFibonacciLastDigitAdv(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
        }

        return current;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitAdv(n);
        System.out.println(c);
    }
}

