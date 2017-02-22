import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 14-Feb-17.
 */
public class CircularSuffixArrayTest {

    @Test
    public void testCircularSuffixArray_Simple() {
        String s = "ABRACADABRA!";
        CircularSuffixArray suffixArray = new CircularSuffixArray(s);
        Assert.assertEquals(11, suffixArray.index(0));
        Assert.assertEquals(10, suffixArray.index(1));
        Assert.assertEquals(7, suffixArray.index(2));
        Assert.assertEquals(0, suffixArray.index(3));
        Assert.assertEquals(3, suffixArray.index(4));
        Assert.assertEquals(5, suffixArray.index(5));
        Assert.assertEquals(8, suffixArray.index(6));
        Assert.assertEquals(1, suffixArray.index(7));
        Assert.assertEquals(4, suffixArray.index(8));
        Assert.assertEquals(6, suffixArray.index(9));
        Assert.assertEquals(9, suffixArray.index(10));
        Assert.assertEquals(2, suffixArray.index(11));
        Assert.assertEquals(12, suffixArray.length());
    }

    @Test(expected = NullPointerException.class)
    public void testCornerCase_Null() {
        new CircularSuffixArray(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCornerCase_Outbound() {
        String s = "ABRACADABRA!";
        CircularSuffixArray suffixArray = new CircularSuffixArray(s);
        Assert.assertEquals(11, suffixArray.index(12));
    }

    @Test
    public void testSort() {

        String toTest = "ABRACADABRA!";

        char[] source = toTest.toCharArray();
        int[] ind = new int[toTest.length()];
        int[] aux = new int[toTest.length()];
        for (int i = 0; i < toTest.length(); i++) {
            ind[i] = i;
            aux[i] = i;
        }

        CircularSuffixArray.sort(aux, source, ind, 0, toTest.length() - 1, 0);
        Assert.assertEquals(11, ind[0]);
        Assert.assertEquals(11, ind[0]);
        Assert.assertEquals(10, ind[1]);
        Assert.assertEquals(7, ind[2]);
        Assert.assertEquals(0, ind[3]);
        Assert.assertEquals(3, ind[4]);
        Assert.assertEquals(5, ind[5]);
        Assert.assertEquals(8, ind[6]);
        Assert.assertEquals(1, ind[7]);
        Assert.assertEquals(4, ind[8]);
        Assert.assertEquals(6, ind[9]);
        Assert.assertEquals(9, ind[10]);
        Assert.assertEquals(2, ind[11]);
    }

}