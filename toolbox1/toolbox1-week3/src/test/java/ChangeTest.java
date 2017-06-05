import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 05-Jun-17.
 */
public class ChangeTest {

    @Test
    public void testChange() {
        int[] in = new int[]{2, 28, 100, 101, 105, 65, 14, 0};
        int[] out = new int[]{2, 6, 10, 11, 11, 7, 5, 0};
        for (int i = 0; i < in.length; i++) {
            Assert.assertEquals(out[i], Change.getChange(in[i]));
        }

    }

}