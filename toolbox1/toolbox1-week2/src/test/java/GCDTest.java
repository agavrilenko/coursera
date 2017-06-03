import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 02-Jun-17.
 */
public class GCDTest {

    @Test
    public void testGCD() {

        int n = 1000;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                Assert.assertEquals(String.format("Failed on i=%s,j=%s", i, j), GCD.gcd_naive(i, j), GCD.gcd_adv(i, j));
            }
        }
    }

    @Test
    public void testGCD_18_35() {
        int i = 18, j = 35;
        Assert.assertEquals(GCD.gcd_naive(i, j), GCD.gcd_adv(i, j));
    }

    @Test
    public void testGCD_28851538_1183019() {
        int i = 28851538, j = 1183019;
        Assert.assertEquals(GCD.gcd_naive(i, j), GCD.gcd_adv(i, j));
    }

}