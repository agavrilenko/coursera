import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 02-Jun-17.
 */
public class LCMTest {

    @Test
    public void testLCM() {

        int n = 100;
        for (int i = 1; i < n; i++)
            for (int j = 1; j < n; j++) {
                {
                    Assert.assertEquals(String.format("Failed on i=%s, j=%s", i, j), LCM.lcm_naive(i, j), LCM.lcm_adv(i, j));
                }
            }
    }

    @Test
    public void testLCM_6_8() {
        int i = 6, j = 8;
        Assert.assertEquals(String.format("Failed on i=%s, j=%s", i, j), LCM.lcm_naive(i, j), LCM.lcm_adv(i, j));
    }

    @Test
    public void testLCM_28851538_1183019() {
        int i = 28851538, j = 1183019;
        Assert.assertEquals(1933053046, LCM.lcm_adv(i, j));
    }

}