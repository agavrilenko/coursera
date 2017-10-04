import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectingPoints {
    static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        ArrayList<Edge>[] graph = new ArrayList[x.length];
        int[] cluster = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
            cluster[i] = i;
        }
        for (int i = 0; i < graph.length - 1; i++) {
            for (int j = i + 1; j < graph.length; j++) {
                double length = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                graph[i].add(new Edge(i, j, length));
                graph[j].add(new Edge(j, i, length));
            }
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.addAll(graph[0]);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Edge e = queue.remove();
            if (isInDifferentClusters(e.from, e.to, cluster)) {
                relink(e.to, e.from, cluster);
                cluster[e.to] = e.from;
                result += e.weight;
                queue.addAll(graph[e.to]);
            }
        }


        return result;
    }

    private static void relink(int to, int from, int[] cluster) {
        cluster[getParent(to, cluster)] = getParent(from, cluster);
    }

    private static boolean isInDifferentClusters(int from, int to, int[] cluster) {
        int parentFrom = getParent(from, cluster);
        int parentTo = getParent(to, cluster);
        return parentFrom != parentTo;
    }

    private static int getParent(int x, int[] cluster) {
        if (cluster[x] == x) {
            return x;
        }
        return getParent(cluster[x], cluster);

    }

    private static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;

        public Edge(int x, int to, double weight) {
            this.from = x;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(weight, o.weight);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

