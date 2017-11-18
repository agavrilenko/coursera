import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Created by trash on 14-Nov-17.
 */
public class EnergyValuesTest {

    @Test
    public void testEnergyValues() {

        int[] in = new int[]{1, 2, 3, 4};

        for (int i = 0; i < in.length; i++) {
            String inputFile = "energy_values/" + String.format("%02d", in[i]);
            String outputFile = inputFile + ".a";
            System.out.println("Processing " + inputFile);
            Scanner scIn = new Scanner(new BufferedInputStream(EnergyValuesTest.class.getClassLoader().getResourceAsStream(inputFile)));
            int size = Integer.valueOf(scIn.nextLine());
            double a[][] = new double[size][size];
            double b[] = new double[size];
            for (int raw = 0; raw < size; ++raw) {
                for (int column = 0; column < size; ++column) {
                    a[raw][column] = scIn.nextInt();
                }
                b[raw] = scIn.nextInt();
            }
            Equation eq = new Equation(a, b);

            Scanner outSc = new Scanner(new BufferedInputStream(EnergyValuesTest.class.getClassLoader().getResourceAsStream(outputFile)));
            double[] exp = new double[size];
            for (int j = 0; j < size; j++) {
                exp[j] = outSc.nextDouble();
            }
            Assert.assertArrayEquals("Failed on TC " + i, exp, EnergyValues.SolveEquation(eq), 0.001);
        }

    }

}