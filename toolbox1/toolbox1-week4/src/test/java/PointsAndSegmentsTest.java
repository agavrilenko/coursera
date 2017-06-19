import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 16-Jun-17.
 */
public class PointsAndSegmentsTest {

    @Test
    public void testPointsAndSegments_1() {
        int[] starts = new int[]{0, 7};
        int[] ends = new int[]{5, 10};
        int[] points = new int[]{1, 6, 11};
        Assert.assertArrayEquals(PointsAndSegments.naiveCountSegments(starts, ends, points), PointsAndSegments.fastCountSegments(starts, ends, points));
    }

    @Test
    public void testPointsAndSegments_2() {
        int[] starts = new int[]{-10};
        int[] ends = new int[]{10};
        int[] points = new int[]{-100, 100, 0};
        Assert.assertArrayEquals(PointsAndSegments.naiveCountSegments(starts, ends, points), PointsAndSegments.fastCountSegments(starts, ends, points));
    }

    @Test
    public void testPointsAndSegments_3() {
        int[] starts = new int[]{0, -3, 7};
        int[] ends = new int[]{5, 2, 10};
        int[] points = new int[]{1, 6};
        Assert.assertArrayEquals(PointsAndSegments.naiveCountSegments(starts, ends, points), PointsAndSegments.fastCountSegments(starts, ends, points));
    }

}