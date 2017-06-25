import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trash on 23-Jun-17.
 */
public class PrimitiveCalculatorTest {

    @Test
    public void testCalcSimple() {
        int[] in = new int[]{
                1,
                5,
                96234,};
        List<Integer>[] out = new List[]{
                Arrays.asList(new Integer[]{1}),
                Arrays.asList(new Integer[]{1, 2, 4, 5}),
                Arrays.asList(new Integer[]{1, 3, 9, 10, 11, 22, 66, 198, 594, 1782, 5346, 16038, 16039, 32078, 96234}),
        };
        for (int i = 0; i < in.length; i++) {

            Assert.assertArrayEquals("Failed on " + i, out[i].toArray(), PrimitiveCalculator.optimal_sequence_adv(in[i]).toArray());
        }
    }

}