import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 02-Jun-17.
 */
public class FibonacciTest {

    @Test
    public void testFib_20() {
        long timeExp = 0, timeAct = 0;
        for (int i = 0; i < 45; i++) {

            long start = System.currentTimeMillis();
            long expected = Fibonacci.calc_fib(i);
            timeExp += System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            long actual = Fibonacci.calc_fib_adv(i);
            timeAct += System.currentTimeMillis() - start;
            Assert.assertEquals(expected, actual);
        }
        System.out.println("Expected time is " + timeExp);
        System.out.println("Actual time is " + timeAct);
    }

    @Test
    public void testFib_100() {
        int num = 100;
        long actual = Fibonacci.calc_fib_adv(num);
        System.out.println(String.format("Fib value of %s is %s ", num, actual));
        actual = Fibonacci.calc_fib_adv(num);
        System.out.println(String.format("Fib value of %s is %s ", num, actual));

    }

}