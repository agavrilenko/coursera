import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by trash on 15-Oct-17.
 */
public class InverseBWTTest {

    @Test
    public void testInverseBWT() {

        String[] inFiles = new String[]{
                "bwtinverse/sample1",
                "bwtinverse/sample5",
                "bwtinverse/sample2",
                "bwtinverse/sample3",
                "bwtinverse/sample4",
        };
        String[] outFiles = new String[]{
                "bwtinverse/sample1.a",
                "bwtinverse/sample5.a",
                "bwtinverse/sample2.a",
                "bwtinverse/sample3.a",
                "bwtinverse/sample4.a",
        };

        for (int i = 0; i < inFiles.length; i++) {
            Scanner scIn = new Scanner(BurrowsWheelerTransformTest.class.getClassLoader().getResourceAsStream(inFiles[i]));
            Scanner scOut = new Scanner(BurrowsWheelerTransformTest.class.getClassLoader().getResourceAsStream(outFiles[i]));
            String expected = scOut.nextLine();
            Assert.assertEquals(String.format("Failed on TC[%s]", i), expected, new InverseBWT().inverseBWT(scIn.nextLine()));

        }


    }

}