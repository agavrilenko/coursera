import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    static double getOptimalValue(int capacity, int[] values, int[] weights) {
        if (capacity == 0) {
            return 0;
        }
        if (values.length < 1 || weights.length < 1) {
            return 0;
        }
        if (values.length != weights.length) {
            throw new RuntimeException("Error");
        }
        double value = 0;
        //write your code here

        Value[] loots = new Value[values.length];
        for (int i = 0; i < values.length; i++) {
            loots[i] = new Value(values[i], weights[i]);
        }

        Arrays.sort(loots);

        double rest = capacity;
        for (int i = loots.length - 1; i >= 0; i--) {
            if (rest >= loots[i].weight) {
                value += loots[i].value;
                rest -= loots[i].weight;
            } else {
                value += loots[i].ratio * rest;
                break;
            }
        }

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    private static class Value implements Comparable {
        private int value;
        private int weight;
        private double ratio;

        public Value(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = ((double) value) / this.weight;
        }

        @Override
        public int compareTo(Object o) {
            Value other;
            if (o != null && o instanceof Value) {
                other = (Value) o;
            } else {
                return 1;
            }
            return Double.compare(this.ratio, other.ratio);
        }
    }
} 
