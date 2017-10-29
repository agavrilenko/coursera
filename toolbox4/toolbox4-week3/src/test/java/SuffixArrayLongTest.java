import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by trash on 24-Oct-17.
 */
public class SuffixArrayLongTest {

    @Test
    public void testSuffixArrayLong() {
        String[] inFiles = new String[]{
                "suffix_array_long/sample3",
                "suffix_array_long/sample1",
                "suffix_array_long/sample2",
                "suffix_array_long/sample4",
        };
        String[] outFiles = new String[]{
                "suffix_array_long/sample3.a",
                "suffix_array_long/sample1.a",
                "suffix_array_long/sample2.a",
                "suffix_array_long/sample4.a",
        };

        for (int i = 0; i < inFiles.length; i++) {
            Scanner scIn = new Scanner(KnuthMorrisPrattTest.class.getClassLoader().getResourceAsStream(inFiles[i]));
            String text = scIn.nextLine();


            Scanner scOut = new Scanner(KnuthMorrisPrattTest.class.getClassLoader().getResourceAsStream(outFiles[i]));
            String[] expected;
            if (!scOut.hasNext()) {
                expected = new String[0];
            } else {
                String line = scOut.nextLine();
                expected = line.split(" ");
            }
            int[] exp = new int[text.length()];
            final AtomicInteger j = new AtomicInteger(0);
            Arrays.stream(expected).mapToInt(st -> Integer.parseInt(st)).forEach(result -> exp[j.getAndIncrement()] = result);
            Assert.assertArrayEquals(String.format("Failed on TC[%s]", i), exp, new SuffixArrayLong().computeSuffixArray(text));

        }
    }

}