import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by trash on 04-Nov-16.
 */
public class PercolationStats {
    // perform trials independent experiments on an n-by-n grid
    private List<Double> values = new ArrayList<Double>();


    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("either n ≤ 0 or trials ≤ 0");
        }

        int cell, cycle = 0;
        double cnt = 0;

        while (cycle < trials) {
            Percolation per = new Percolation(n);
            while (true) {
                Random rnd = new Random();
                cell = rnd.nextInt(n * n) + 1;
                int row = (cell - 1) / n + 1;
                int col = (cell - 1) % n + 1;
                if (per.isOpen(row, col)) {
                    continue;
                }
                cnt++;
                per.open(row, col);
                if (per.percolates()) {
                    values.add(cnt / (n * n));
                    cnt = 0;
                    cycle++;
                    break;
                }
            }
        }
    }

    private List<Double> getValues() {
        return values;
    }

    // sample mean of percolation threshold
    public double mean() {
        final double[] actualValues = new double[getValues().size()];
        for (int i = 0; i < getValues().size(); i++) {
            actualValues[i] = getValues().get(i).doubleValue();
        }
        return StdStats.mean(actualValues);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        final double[] actualValues = new double[getValues().size()];
        for (int i = 0; i < getValues().size(); i++) {
            actualValues[i] = getValues().get(i).doubleValue();
        }
        return StdStats.stddev(actualValues);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(values.size());
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(values.size());
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int attempts = Integer.parseInt(args[1]);
        if (n <= 0 || attempts <= 0) {
            throw new IllegalArgumentException("either n ≤ 0 or trials ≤ 0");
        }
        PercolationStats stat = new PercolationStats(n, attempts);
        System.out.println("mean                    = " + stat.mean());
        System.out.println("stddev                  = " + stat.stddev());
        System.out.println("95% confidence interval = " + stat.confidenceLo() + "," + stat.confidenceHi());
    }
}
