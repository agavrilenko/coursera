import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by trash on 22-Oct-17.
 */
public class KnuthMorrisPrattTest {

    @Test
    public void testKMP() {
        String[] inFiles = new String[]{
                "kmp/sample3",
                "kmp/sample1",
                "kmp/sample2",
        };
        String[] outFiles = new String[]{
                "kmp/sample3.a",
                "kmp/sample1.a",
                "kmp/sample2.a",
        };

        for (int i = 0; i < inFiles.length; i++) {
            Scanner scIn = new Scanner(KnuthMorrisPrattTest.class.getClassLoader().getResourceAsStream(inFiles[i]));
            String pattern = scIn.nextLine();
            String text = scIn.nextLine();


            Scanner scOut = new Scanner(KnuthMorrisPrattTest.class.getClassLoader().getResourceAsStream(outFiles[i]));
            String[] expected;
            if (!scOut.hasNext()) {
                expected = new String[0];
            } else {
                String line = scOut.nextLine();
                expected = line.split(" ");
            }
            List<Integer> exp = new ArrayList<>();
            Arrays.stream(expected).mapToInt(st -> Integer.parseInt(st)).forEach(result -> exp.add(result));
            Assert.assertArrayEquals(String.format("Failed on TC[%s]", i), exp.toArray(), new KnuthMorrisPratt().findPattern(pattern, text).toArray());

        }
    }

}