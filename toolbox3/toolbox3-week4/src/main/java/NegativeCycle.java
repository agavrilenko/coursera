import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class NegativeCycle {
    static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {

        ArrayList<Edge>[] graph = new ArrayList[adj.length];
        int[] weights = new int[adj.length];
        int[] path = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            weights[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
            path[i] = -1;
            for (int j = 0; j < adj[i].size(); j++) {
                graph[i].add(new Edge(i, adj[i].get(j), cost[i].get(j)));
            }
        }
        adj = null;
        cost = null;
        boolean changed = false;
        boolean[] visited = new boolean[graph.length];
        for (int j = 0; j < graph.length; j++) {
            if (!visited[j]) {
                for (int i = 0; i < graph.length - 1; i++) {
                    visited = new boolean[graph.length];
                    changed = improvePath(graph, weights, path, j, visited);
                    if (!changed) {
                        break;
                    }
                }
                if (changed) {
                    return 1;
                }
            }
        }
        return 0;
    }


    private static boolean improvePath(ArrayList<Edge>[] graph, int[] weights, int[] path, int v, boolean[] visited) {
        boolean changed;
        LinkedList<Edge> queue = new LinkedList<>();

        changed = false;
        queue.addAll(graph[v]);
        weights[v] = 0;
        visited[v] = true;
        while (queue.size() > 0) {
            Edge e = queue.remove();
            int weight = weights[e.from] + e.weight;
            if (weights[e.to] == Integer.MAX_VALUE || weight < weights[e.to]) {
                weights[e.to] = weight;
                path[e.to] = e.from;
                changed = true;
            }
            if (!visited[e.to]) {
                queue.addAll(graph[e.to]);
                visited[e.to] = true;
            }
        }
        return changed;
    }

    private static class Edge {
        int from, to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
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
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

