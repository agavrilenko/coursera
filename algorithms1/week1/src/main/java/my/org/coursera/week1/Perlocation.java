package my.org.coursera.week1;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by trash on 03-Nov-16.
 */
public class Perlocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            if ("q".equals(cmd)) {
                System.exit(0);
            }
            boolean print = false;
            if ("p".equals(cmd)) {
                print = true;
                cmd = sc.nextLine();
            }
            String[] values = cmd.split(" ");
            long iniTime = System.currentTimeMillis();
            final int sz = Integer.parseInt(values[0]);
            int attempts = Integer.parseInt(values[1]);
            int cell, cnt = 0, cycles = 0;
            BigDecimal ratio = new BigDecimal("0");
            final int sz_2 = sz * sz;
            while (cycles < attempts) {
                QuickUnionUF uf = new QuickUnionUF(sz_2 + 2);
                int[] aux = new int[sz_2 + 2];
                //     aux[0] = aux[sz * sz + 1] = 1;
                for (int i = 1; i < sz + 1; i++) {
                    uf.union(0, i);
                }

                for (int i = sz_2 + 1 - sz; i < sz_2 + 1; i++) {
                    uf.union(sz_2 + 1, i);
                }
                boolean connection;
                // uf.print();
                while (true) {
                    connection = false;
                    Random rnd = new Random();
                    cell = rnd.nextInt(sz_2) + 1;
                    if (aux[cell] == 1) {
                        continue;
                    }
                    aux[cell] = 1;
                    cnt++;
                    if (print) {
                        System.out.println("Cell is " + cell);
                        printAux(sz, cell, aux);
                    }
                    //connect right cell
                    if (aux[cell + 1] == 1 && cell % sz != 0 && !uf.connected(cell, cell + 1)) {
                        connection = true;
                        uf.union(cell, cell + 1);
                        if (print) {
                            System.out.print(" right ");
                        }
                    }
                    // connect left cell
                    if (aux[cell - 1] == 1 && cell % sz != 1 && !uf.connected(cell, cell - 1)) {
                        connection = true;
                        uf.union(cell, cell - 1);
                        if (print) {
                            System.out.print(" left ");
                        }
                    }
                    //connect below cell
                    if (cell + sz <= sz_2 && aux[cell + sz] == 1 && !uf.connected(cell, cell + sz)) {
                        connection = true;
                        uf.union(cell, cell + sz);
                        if (print) {
                            System.out.print(" below ");
                        }
                    }
//                    else if ((cell + sz) / sz >= sz && !uf.connected(cell, sz * sz + 1)) {
//                        connection = true;
//                        uf.union(cell, sz * sz + 1);
//                    }
                    //connect above cell
                    if (cell - sz >= 1 && aux[cell - sz] == 1 && !uf.connected(cell, cell - sz)) {
                        connection = true;
                        uf.union(cell, cell - sz);
                        if (print) {
                            System.out.print(" up ");
                        }
                    }
//                    else if ((cell - sz) / sz <= 0 && !uf.connected(cell, 0)) {
//                        connection = true;
//                        uf.union(cell, 0);
//                    }

                    if (print) {
                        System.out.println();
                        if (connection) {
                            uf.print();
                        }
                    }
                    if (uf.connected(0, sz_2 + 1)) {

                        ratio = ratio.add(new BigDecimal(cnt).divide(new BigDecimal(sz_2), 20, BigDecimal.ROUND_UP));
                        cnt = 0;
                        // System.out.println("Number of operations is " + cnt + ". Ration is " + ratio);
                        cycles++;
                        break;
                    }
                }
            }
            long finishTime = System.currentTimeMillis();
            long delta = (finishTime - iniTime) / 1000;
            long hours = delta / 3600;
            long mins = (delta - hours * 3600) / 60;
            long seconds = (delta - hours * 3600 - mins * 60);
            long milis = finishTime - iniTime - hours * 100 / 36 - (mins * 1000) / 60 - seconds * 1000;
            System.out.println("Average ratio is " + ratio.divide(BigDecimal.valueOf(attempts), 5, BigDecimal.ROUND_UP).toString());
            System.out.println("It took [" + hours + ":" + mins + ":" + seconds + "." + milis + "]");
        }
    }

    private static void printAux(int sz, int cell, int[] aux) {
        boolean wasFeeler = false;
        boolean feeler = false;
        for (int i = 0; i < aux.length; i++) {
            feeler = (cell == i);
            if (i % sz == 1) {
                System.out.print(" ");
            }
            if (feeler) {
                wasFeeler = true;
            }
            System.out.print((wasFeeler && i % sz == 1 ? " " : "") + (feeler ? "[" : "") + (wasFeeler ? "" : " ") + aux[i] + (feeler ? "]" : ""));

            wasFeeler = feeler;
            if (i % sz == 0) {
                System.out.println();
            }


        }
    }
}
