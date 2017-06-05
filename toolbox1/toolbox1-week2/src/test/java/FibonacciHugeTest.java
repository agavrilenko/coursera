import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 03-Jun-17.
 */
public class FibonacciHugeTest {

    @Test
    public void testPisano() {
        int n = 40;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                Assert.assertEquals(String.format("Failed on i=%s, j=%s", i, j), FibonacciHuge.getFibonacciHugeNaive(i, j), FibonacciHuge.getFibonacciHugeAdv(i, j));
            }
        }
    }

    @Test
    public void testPisano_1_239() {
        long i = 1, j = 239;
        Assert.assertEquals(1, FibonacciHuge.getFibonacciHugeAdv(i, j));
    }

    @Test
    public void testPisano_2_2() {
        long i = 2, j = 2;
        Assert.assertEquals(1, FibonacciHuge.getFibonacciHugeAdv(i, j));
    }

    @Test
    public void testPisano_4_2() {
        long i = 4, j = 2;
        Assert.assertEquals(1, FibonacciHuge.getFibonacciHugeAdv(i, j));
    }

    @Test
    public void testPisano_239_1000() {
        long i = 239, j = 1000;
        Assert.assertEquals(161, FibonacciHuge.getFibonacciHugeAdv(i, j));
    }

    @Test
    public void testPisano_1000_100() {
        long i = 1000, j = 100;
        Assert.assertEquals(75, FibonacciHuge.getFibonacciHugeAdv(i, j));
    }

    @Test
    public void testPisano_1000_10() {
        long i = 1000, j = 10;
        Assert.assertEquals(5, FibonacciHuge.getFibonacciHugeAdv(i, j));
    }

    @Test
    public void testPisano_2015_3() {
        long i = 2015, j = 3;
        Assert.assertEquals(1, FibonacciHuge.getFibonacciHugeAdv(i, j));
    }

    @Test
    public void testPisano_2816213588_30524() {
        long i = 2816213588l, j = 30524;
        Assert.assertEquals(10249, FibonacciHuge.getFibonacciHugeAdv(i, j));
    }

}