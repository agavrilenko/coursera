import edu.princeton.cs.algs4.Picture;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by trash on 23-Jan-17.
 */
public class SeamCarverTest {
    private final static String PATH = "src/test/resources/";
    private SeamCarver carver;

    @Before
    public void init() {
        File file = new File(PATH + "4x6.png");
        carver = new SeamCarver(new Picture(file));
    }

    @Test(expected = NullPointerException.class)
    public void testSeamCarver_NullInput() {
        new SeamCarver(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSeamCaver_NullInRemoveVertical() {
        carver.removeVerticalSeam(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSeamCaver_NullInRemoveHorizontal() {
        carver.removeHorizontalSeam(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBound() {
        carver.energy(8, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneVPixelRemove() {
        carver = new SeamCarver(new Picture(PATH + "1x1.png"));
        int[] seam = new int[]{1};
        carver.removeVerticalSeam(seam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneHPixelRemove() {
        carver = new SeamCarver(new Picture(PATH + "1x1.png"));
        int[] seam = new int[]{1};
        carver.removeHorizontalSeam(seam);
    }
}
