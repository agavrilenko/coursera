import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CoveringSegments {

    static int[] optimalPoints(Segment[] segments) {
        //write your code here


        ArrayList<Integer> points = new ArrayList<>();
        Arrays.sort(segments, (o1, o2) -> o1.end - o2.end);

        int right = segments[0].end;
        points.add(right);
        for (int i = 1; i < segments.length; i++) {
            if (!(right >= segments[i].start && right <= segments[i].end)) {
                right = segments[i].end;
                points.add(right);
            }
        }
        return points.stream().mapToInt(value -> value).toArray();
    }

    static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
