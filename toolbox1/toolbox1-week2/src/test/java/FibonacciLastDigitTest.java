import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 02-Jun-17.
 */
public class FibonacciLastDigitTest {

    @Test
    public void testFib_20() {
        long timeExp = 0, timeAct = 0;
        for (int i = 0; i < 90; i++) {
            long start = System.currentTimeMillis();
            long expected = FibonacciLastDigit.getFibonacciLastDigitNaive(i);
            timeExp += System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            long actual = FibonacciLastDigit.getFibonacciLastDigitAdv(i);
            timeAct += System.currentTimeMillis() - start;
            Assert.assertEquals("Failed on i = " + i, expected, actual);
        }
        System.out.println("Expected time is " + timeExp);
        System.out.println("Actual time is " + timeAct);
    }

    @Test
    public void testLastDigit_3() {
        Assert.assertEquals(2, FibonacciLastDigit.getFibonacciLastDigitAdv(3));
    }

    @Test
    public void testLastDigit_331() {
        Assert.assertEquals(9, FibonacciLastDigit.getFibonacciLastDigitAdv(331));
    }

    @Test
    public void testLastDigit_327305() {
        Assert.assertEquals(5, FibonacciLastDigit.getFibonacciLastDigitAdv(327305));
    }

}