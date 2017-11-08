import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by trash on 06-Nov-17.
 */
public class AirlineCrewsTest {
    @Test
    public void testAirline() {
        List<Integer> avoid = Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9);
        for (int i = 1; i <= 33; i++) {

//            if (avoid.contains(i)) {
//                System.out.println(String.format("Skipped %03d", i));
//                continue;
//            }

            String fileName = "airline_crews/" + String.format("%02d", i);
            System.out.println("Processing " + fileName);
            Scanner scIn = new Scanner(new BufferedInputStream(AirlineCrewsTest.class.getClassLoader().getResourceAsStream(fileName)));
            String[] text = scIn.nextLine().split(" ");
            int numLeft = Integer.valueOf(text[0]);
            int numRight = Integer.valueOf(text[1]);

            boolean[][] adjMatrix = new boolean[numLeft][numRight];
            for (int k = 0; k < numLeft; ++k) {
                for (int j = 0; j < numRight; ++j) {
                    adjMatrix[k][j] = (scIn.nextInt() == 1);
                }
            }
            Scanner scOut = new Scanner(AirlineCrewsTest.class.getClassLoader().getResourceAsStream(fileName + ".a"));
            String line = scOut.nextLine();
            int[] exp = Arrays.stream(line.split(" ")).mapToInt(st -> Integer.valueOf(st)).toArray();
            long countExp = Arrays.stream(exp).filter(k -> k > -1).count();

            Assert.assertEquals(String.format("Failed on TC[%s]", i), countExp,
                    Arrays.stream(new AirlineCrews().findMatching(adjMatrix)).filter(k -> k > -1).count());
        }
    }
}