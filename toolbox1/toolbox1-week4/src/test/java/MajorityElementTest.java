import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 12-Jun-17.
 */
public class MajorityElementTest {
    @Test
    public void testMajority() {
        int[] in = new int[]{2, 3, 9, 2, 2};
        Assert.assertEquals(1, MajorityElement.getMajorityElement(in, 0, in.length));
    }

    @Test
    public void testMajority_1() {
        int[] in = new int[]{1, 2, 3, 4};
        Assert.assertEquals(0, MajorityElement.getMajorityElement(in, 0, in.length));
    }

    @Test
    public void testMajority_2() {
        int[] in = new int[]{1, 2, 3, 1};
        Assert.assertEquals(0, MajorityElement.getMajorityElement(in, 0, in.length));
    }

    @Test
    public void testMajority_3() {
        int[] in = new int[]{512766168, 717383758, 5, 126144732, 5, 573799007, 5, 5, 5, 405079772};
        Assert.assertEquals(0, MajorityElement.getMajorityElement(in, 0, in.length));
    }

}