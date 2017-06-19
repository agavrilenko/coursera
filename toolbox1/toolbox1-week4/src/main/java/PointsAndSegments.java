import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PointsAndSegments {


    static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {

        int[] cnt = new int[points.length];
        Point[] segs = new Point[starts.length + ends.length + points.length];
        for (int i = 0; i < starts.length; i++) {
            segs[2 * i] = new Start(starts[i], 1);
            segs[2 * i + 1] = new End(ends[i], 3);
        }
        for (int i = 0; i < points.length; i++) {
            segs[2 * starts.length + i] = new Regular(points[i], 2);

        }
        Arrays.sort(segs);

        int cnts = 0;
        int segCnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < segs.length; i++) {
            if (segs[i] instanceof Regular) {
                map.put(segs[i].getPoint(), segCnt);
                continue;
            }
            if (segs[i] instanceof Start) {
                segCnt++;
                continue;
            }
            if (segs[i] instanceof End) {
                segCnt--;
                continue;
            }
        }
        for (int i = 0; i < points.length; i++) {
            cnt[i] = map.get(points[i]);
        }
        return cnt;
    }

    static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

    private static class Point implements Comparable {
        private int point;
        private int priority;

        private Point(int start, int priority) {
            this.point = start;
            this.priority = priority;
        }

        public int getPoint() {
            return point;
        }

        @Override
        public int compareTo(Object o) {
            Point obj;
            if (!(o instanceof Point)) {
                throw new IllegalArgumentException("Incomparable");
            }
            obj = (Point) o;
            int diff = this.point - obj.point;
            if (diff == 0) {
                return this.priority - obj.priority;
            } else {
                return diff;
            }
        }
    }


    private static class End extends Point {
        private End(int start, int priority) {
            super(start, priority);
        }
    }

    private static class Start extends Point {
        private Start(int start, int priority) {
            super(start, priority);
        }
    }

    private static class Regular extends Point {
        private Regular(int start, int priority) {
            super(start, priority);
        }
    }
}

