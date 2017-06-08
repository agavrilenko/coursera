import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 05-Jun-17.
 */
public class CoveringSegmentsTest {

    @Test
    public void testSegments() {

        CoveringSegments.Segment[][] in = new CoveringSegments.Segment[][]{
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 3)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 1), new CoveringSegments.Segment(2, 2), new CoveringSegments.Segment(3, 3)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 3), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(3, 6)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(4, 7), new CoveringSegments.Segment(1, 3), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(5, 6)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 2), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(2, 6)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 2), new CoveringSegments.Segment(3, 4), new CoveringSegments.Segment(5, 6)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 3), new CoveringSegments.Segment(2, 4), new CoveringSegments.Segment(5, 6)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 6), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(3, 4)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 6), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(3, 4), new CoveringSegments.Segment(0, 1)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 6), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(3, 4), new CoveringSegments.Segment(0, 1), new CoveringSegments.Segment(5, 6)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(4, 7), new CoveringSegments.Segment(1, 3), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(5, 6)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(4, 7), new CoveringSegments.Segment(1, 3), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(5, 6), new CoveringSegments.Segment(6, 8)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(4, 7), new CoveringSegments.Segment(2, 5), new CoveringSegments.Segment(5, 6), new CoveringSegments.Segment(6, 10), new CoveringSegments.Segment(9, 9)},
                new CoveringSegments.Segment[]{new CoveringSegments.Segment(1, 6), new CoveringSegments.Segment(2, 4), new CoveringSegments.Segment(3, 5), new CoveringSegments.Segment(7, 7)},
        };
        int[][] out = new int[][]{
                new int[]{3},
                new int[]{1, 2, 3},
                new int[]{3},
                new int[]{3, 6},
                new int[]{2},
                new int[]{2, 4, 6},
                new int[]{3, 6},
                new int[]{4},
                new int[]{1, 4},
                new int[]{1, 4, 6},
                new int[]{3, 6},
                new int[]{3, 6},
                new int[]{5, 9},
                new int[]{4, 7},
        };
        int start = 0;
        int finish = in.length;
        for (int i = start; i < finish; i++) {
            Assert.assertArrayEquals("Failed on " + i, out[i], CoveringSegments.optimalPoints(in[i]));
        }

    }

}