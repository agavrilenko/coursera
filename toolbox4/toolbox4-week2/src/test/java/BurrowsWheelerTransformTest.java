import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by trash on 14-Oct-17.
 */
public class BurrowsWheelerTransformTest {

    @Test
    public void testBWT() {
        String[] inFiles = new String[]{
                "bwt/sample3",
                "bwt/sample1",
                "bwt/sample2",
                "bwt/sample4",
        };
        String[] outFiles = new String[]{
                "bwt/sample3.a",
                "bwt/sample1.a",
                "bwt/sample2.a",
                "bwt/sample4.a",
        };

        for (int i = 0; i < inFiles.length; i++) {
            Scanner scIn = new Scanner(BurrowsWheelerTransformTest.class.getClassLoader().getResourceAsStream(inFiles[i]));
            Scanner scOut = new Scanner(BurrowsWheelerTransformTest.class.getClassLoader().getResourceAsStream(outFiles[i]));
            String expected = scOut.nextLine();
            Assert.assertEquals(String.format("Failed on TC[%s]", i), expected, new BurrowsWheelerTransform().BWT(scIn.nextLine()));

        }


    }

}