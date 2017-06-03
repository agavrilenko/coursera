import java.util.Scanner;

public class GCD {
    static int gcd_naive(int a, int b) {
        int current_gcd = 1;
        for (int d = 2; d <= a && d <= b; ++d) {
            if (a % d == 0 && b % d == 0) {
                if (d > current_gcd) {
                    current_gcd = d;
                }
            }
        }

        return current_gcd;
    }

    static int gcd_adv(int a, int b) {
        int second, first;
        if (a >= b) {
            first = a;
            second = b;
        } else {
            first = b;
            second = a;
        }
        int tmp;
        while (second > 0) {
            tmp = second;
            second = first % second;
            first = tmp;
        }
        return first;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(gcd_adv(a, b));
    }
}
