package my.org.coursera.week1;

import java.util.Scanner;

/**
 * Created by trash on 31-Oct-16.
 */
public class UFRunner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UF uf = new QuickUnionUF(sc.nextInt());
        sc.nextLine();
        while (true) {
            try {
                String line = sc.nextLine();
                if ("p".equals(line)) {
                    uf.print();
                    continue;
                }
                if ("e".equals(line)) {
                    uf = new QuickUnionUF(sc.nextInt());
                    sc.nextLine();
                    continue;
                }

                String[] chars = line.split(" ");
                String operation = chars[0];
                int p = Integer.valueOf(chars[1]);
                if ("r".equals(operation)) {
                    System.out.println("root of " + p + " id " + uf.root(p));
                    continue;
                }
                if("d".equals(operation)){
                    System.out.println(uf.printMessage(p));
                    continue;
                }
                int q = Integer.valueOf(chars[2]);
                if ("c".equals(operation)) {
                    System.out.println(p + " is " + (uf.connected(p, q) ? "" : "not") + " connected to " + q);
                } else if ("u".equals(operation)) {
                    if (uf.connected(p, q)) {
                        System.out.println(p + " and " + q + " already connected");
                        continue;
                    }
                    uf.union(p, q);
                    System.out.println("connected " + p + " and " + q + uf.printMessage(p));

                } else {
                    System.out.println("Operation is not supported");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Continue to next line");
                continue;
            }
        }
    }
}
