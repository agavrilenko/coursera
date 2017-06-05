import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 04-Jun-17.
 */
public class FibonacciSumLastDigitTest {

    @Test
    public void testFibSum() {
        int n = 50;
        for (int i = 0; i < n; i++) {
            Assert.assertEquals(String.format("Failed on i = %s", i), FibonacciSumLastDigit.getFibonacciSumNaive(i), FibonacciSumLastDigit.getFibonacciSumAdv(i));
        }
    }

    @Test
    public void testFibSum_3() {
        int i = 3;
        Assert.assertEquals(4, FibonacciSumLastDigit.getFibonacciSumAdv(i));
    }

    @Test
    public void testFibSum_100() {
        int i = 100;
        Assert.assertEquals(5, FibonacciSumLastDigit.getFibonacciSumAdv(i));
    }

    @Test
    public void testFibSum_832564823476() {
        long i = 832564823476l;
        Assert.assertEquals(5, FibonacciSumLastDigit.getFibonacciSumAdv(i));
    }

}