import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 29-May-17.
 */
public class MaxPairwiseProductTest {
    @Test
    public void test(){

        int[] nums = new int[]{100000,90000};
        Assert.assertEquals(9000000000L, MaxPairwiseProduct.getMaxPairwiseProduct(nums));
    }

}