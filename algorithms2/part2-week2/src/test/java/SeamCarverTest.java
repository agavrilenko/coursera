import edu.princeton.cs.algs4.Picture;
import org.junit.Assert;
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

    @Test
    public void testEnerge_VerticalSeam() {
        carver = new SeamCarver(new Picture(PATH + "6x5.png"));
        int[] actual = new int[]{};
        int[] expeted = new int[]{3, 4, 3, 2, 2};
        carver.removeVerticalSeam(actual);
        Assert.assertEquals(expeted, actual);

    }

    @Test
    public void testEnerge_HorizontalSeam() {
        carver = new SeamCarver(new Picture(PATH + "6x5.png"));
        int[] actual = new int[]{};
        int[] expeted = new int[]{2, 2, 1, 2, 1, 2};
        carver.removeHorizontalSeam(actual);
        Assert.assertEquals(expeted, actual);
    }

    @Test
    public void testEnerge_Simple() {
        carver = new SeamCarver(new Picture(PATH + "3x4.png"));
        Assert.assertEquals(Math.sqrt(52024d), carver.energy(1, 2), 0);
        Assert.assertEquals(Math.sqrt(52225d), carver.energy(1, 1), 0);
        Assert.assertEquals(1000, carver.energy(0, 0), 0);
        Assert.assertEquals(1000, carver.energy(0, 1), 0);
        Assert.assertEquals(1000, carver.energy(0, 2), 0);
        Assert.assertEquals(1000, carver.energy(0, 3), 0);
        Assert.assertEquals(1000, carver.energy(1, 0), 0);
        Assert.assertEquals(1000, carver.energy(1, 3), 0);
        Assert.assertEquals(1000, carver.energy(2, 0), 0);
        Assert.assertEquals(1000, carver.energy(2, 1), 0);
        Assert.assertEquals(1000, carver.energy(2, 2), 0);
        Assert.assertEquals(1000, carver.energy(2, 3), 0);
    }
}
