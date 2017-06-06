import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 05-Jun-17.
 */
public class FractionalKnapsackTest {

    @Test
    public void testFractKnapsack_1() {

        Assert.assertEquals(180.0000, FractionalKnapsack.getOptimalValue(50, new int[]{60, 100, 120}, new int[]{20, 50, 30}), 0.0001);

    }

    @Test
    public void testFractKnapsack_2() {

        Assert.assertEquals(166.6667, FractionalKnapsack.getOptimalValue(10, new int[]{500}, new int[]{30}), 0.0001);

    }

}