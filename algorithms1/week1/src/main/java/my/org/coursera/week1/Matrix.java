package my.org.coursera.week1;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by trash on 03-Nov-16.
 */
public class Matrix {
    int[] id;
    double probability;
    int size;

    public Matrix(int n, double probability) {
        this.size = n;
        this.probability = probability;
        id = new int[n * n];
        Random rnd = new Random(2343242);
        for (int i = 0; i < n * n; i++) {
            id[i] = rnd.nextDouble() <= probability ? 1 : 0;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(id[i * size + j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            if ("q".equals(cmd)){
                System.exit(0);
            }
            String [] values = cmd.split(" ");
            Matrix m = new Matrix(Integer.parseInt(values[0]), Double.parseDouble(values[1]));
            m.printMatrix();
        }
    }
}
