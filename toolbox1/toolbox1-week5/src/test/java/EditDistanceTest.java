import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 27-Jun-17.
 */
public class EditDistanceTest {

    @Test
    public void testEditDistance_Simple() {
        String[] in1 = new String[]{
                "editing",
                "ab",
                "short",
        };
        String[] in2 = new String[]{
                "distance",
                "ab",
                "ports",
        };
        int[] out = new int[]{
                5,
                0,
                3,
        };
        for (int i = 0; i < out.length; i++) {
            Assert.assertEquals("Failed on i = " + i, out[i], EditDistance.EditDistance(in1[i], in2[i]));
        }
    }

}