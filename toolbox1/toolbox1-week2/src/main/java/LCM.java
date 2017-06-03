import java.util.Scanner;

public class LCM {
    static long lcm_naive(int a, int b) {
        for (long l = 1; l <= (long) a * b; ++l)
            if (l % a == 0 && l % b == 0)
                return l;

        return (long) a * b;
    }

    static long lcm_adv(int a, int b) {
        return (long) a * (long) b / gcd_adv(a, b);
    }

    static long gcd_adv(int a, int b) {
        long second, first;
        if (a >= b) {
            first = a;
            second = b;
        } else {
            first = b;
            second = a;
        }
        long tmp;
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

        System.out.println(lcm_adv(a, b));
    }
}
