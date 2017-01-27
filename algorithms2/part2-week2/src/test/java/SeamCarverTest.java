import edu.princeton.cs.algs4.Picture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
    public void testEnerge_VerticalSeam_3x4() {
        carver = new SeamCarver(new Picture(PATH + "3x4.png"));
        int[] expeted = new int[]{0, 1, 1, 0};
        int[] actual = carver.findVerticalSeam();
        assertArrayEquals(expeted, actual);

    }

    @Test
    public void testSeam_5x6() {
        carver = new SeamCarver(new Picture(PATH + "5x6.png"));
        int[] expeted = new int[]{1, 2, 2, 3, 2, 1};
        int[] actual = carver.findVerticalSeam();
        assertArrayEquals(expeted, actual);
        expeted = new int[]{2, 3, 2, 3, 2};
        actual = carver.findHorizontalSeam();
        assertArrayEquals(expeted, actual);

    }

    @Test
    public void testSeam_4x6() {
        carver = new SeamCarver(new Picture(PATH + "4x6.png"));
        int[] expeted = new int[]{1, 2, 1, 1, 2, 1};
        int[] actual = carver.findVerticalSeam();
        assertArrayEquals(expeted, actual);
        expeted = new int[]{1, 2, 1, 0};
        actual = carver.findHorizontalSeam();
        assertArrayEquals(expeted, actual);

    }

    @Ignore()// multiple paths are available
    @Test
    public void testSeam_Stripes() {
        carver = new SeamCarver(new Picture(PATH + "stripes.png"));
        int[] expeted = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
        int[] actual = carver.findVerticalSeam();
        assertArrayEquals(expeted, actual);
        expeted = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 0};
        actual = carver.findHorizontalSeam();
        assertArrayEquals(expeted, actual);

    }

    @Test
    public void testSeam_12x10() {
        carver = new SeamCarver(new Picture(PATH + "12x10.png"));
        int[] expeted = new int[]{6, 7, 7, 6, 6, 7, 7, 7, 8, 7};
        int[] actual = carver.findVerticalSeam();
        assertArrayEquals(expeted, actual);
        expeted = new int[]{7, 8, 7, 8, 7, 6, 5, 6, 6, 5, 4, 3};
        actual = carver.findHorizontalSeam();
        assertArrayEquals(expeted, actual);

    }

    @Test
    public void testEnerge_HorizontalSeam_3x4() {
        carver = new SeamCarver(new Picture(PATH + "3x4.png"));
        int[] expeted = new int[]{1, 2, 1};
        int[] actual = carver.findHorizontalSeam();
        assertArrayEquals(expeted, actual);

    }

    @Test
    public void testEnerge_VerticalSeam_6x5() {
        carver = new SeamCarver(new Picture(PATH + "6x5.png"));
        int[] expeted = new int[]{3, 4, 3, 2, 1};
        int[] actual = carver.findVerticalSeam();
        assertArrayEquals(expeted, actual);

    }

    @Test
    public void testEnerge_HorizontalSeam() {
        carver = new SeamCarver(new Picture(PATH + "6x5.png"));
        int[] expeted = new int[]{1, 2, 1, 2, 1, 0};
        int[] actual = carver.findHorizontalSeam();
        assertArrayEquals(expeted, actual);
    }

    @Test
    public void testEnerge_Simple() {
        carver = new SeamCarver(new Picture(PATH + "3x4.png"));
        assertEquals(Math.sqrt(52024d), carver.energy(1, 2), 0);
        assertEquals(Math.sqrt(52225d), carver.energy(1, 1), 0);
        assertEquals(1000, carver.energy(0, 0), 0);
        assertEquals(1000, carver.energy(0, 1), 0);
        assertEquals(1000, carver.energy(0, 2), 0);
        assertEquals(1000, carver.energy(0, 3), 0);
        assertEquals(1000, carver.energy(1, 0), 0);
        assertEquals(1000, carver.energy(1, 3), 0);
        assertEquals(1000, carver.energy(2, 0), 0);
        assertEquals(1000, carver.energy(2, 1), 0);
        assertEquals(1000, carver.energy(2, 2), 0);
        assertEquals(1000, carver.energy(2, 3), 0);
    }

    @Test
    public void testTopologicalOrder_3x4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        carver = new SeamCarver(new Picture(PATH + "3x4.png"));
        Method buildHTopology = SeamCarver.class.getDeclaredMethod("buildVTopology", int.class);
        Object result = buildHTopology.invoke(carver, 1);
        Assert.assertTrue(result instanceof Queue);
        assertEquals(10, ((Queue<Integer>) result).size());
    }

    @Test
    public void testTopologicalOrder_6x5() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        carver = new SeamCarver(new Picture(PATH + "6x5.png"));
        LinkedList<Integer> queue = carver.buildVTopology(2);
        LinkedList<Integer> expected = new LinkedList<>();
        expected.addAll(Arrays.asList(24, 25, 18, 26, 19, 12, 27, 20, 13, 28, 21, 14, 7, 29, 22, 15, 8, 23, 16, 9, 2));
        assertArrayEquals(expected.toArray(), queue.toArray());


    }
}
