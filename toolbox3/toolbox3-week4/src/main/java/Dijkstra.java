import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
    static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        if (adj.length <= 1) {
            return 0;
        }
        if (s == t) {
            return 0;
        }
        ArrayList<Edge>[] vertices = new ArrayList[adj.length];
        boolean[] visited = new boolean[adj.length];
        int[] path = new int[adj.length];
        long[] costs = new long[adj.length];
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for (int i = 0; i < adj.length; i++) {
            vertices[i] = new ArrayList<>(i);
            path[i] = -1;
            costs[i] = Long.MAX_VALUE;
            for (int j = 0; j < adj[i].size(); j++) {
                Integer to = adj[i].get(j);
                Integer wight = cost[i].get(j);
                vertices[i].add(new Edge(i, to, wight));
            }
        }

        queue.addAll(vertices[s]);
        visited[s] = true;
        costs[s] = 0;
        while (queue.size() > 0) {
            Edge e = queue.remove();
            long weightFrom = costs[e.from] + e.weight;
            if (weightFrom < costs[e.to]) {
                costs[e.to] = weightFrom;
                path[e.to] = e.from;
            }
            if (!visited[e.to]) {
                queue.addAll(vertices[e.to]);
                visited[e.to] = true;
            }
        }
        return costs[t] != Long.MAX_VALUE ? costs[t] : -1L;
    }


    public static int distance1(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int n = adj.length;
        // Initialize dist[] by inf, except source s.
        // dist [v] will be an upper bound on the actual distance from S to v.
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        dist[s] = 0;
        // Traverse each vertex outside of known region R.
        for (int j = 0; j < n; j++) {
            int u = minDistVertex(dist, visited);
            // BZ: returned minVertex is -1? unreachable from S.
            if (u == -1) continue;
            visited[u] = true;
            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i), w = cost[u].get(i);
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    // BZ: update previous node of v as well for reconstruction?
                    prev[v] = u;
                }
            }
        }
        // BZ: no need to reconstruct.
        // After the call to algorithm, all the distances are set correctly.
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        Integer weight;

        public Edge(int from, int to, int wight) {
            this.from = from;
            this.to = to;
            this.weight = wight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight.compareTo(o.weight);
        }

        @Override
        public String toString() {
            return "E{" +
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }


    public static int minDistVertex(int[] dist, boolean[] visited) {
        /**Find the vertex with min dist and not yet visited.*/
        int minDist = Integer.MAX_VALUE, minVertex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (visited[v]) continue;
            if (dist[v] < minDist) minVertex = v;
            minDist = Math.min(minDist, dist[v]);
        }
        return minVertex;
    }

    /**
     * Runtime if using array: O(|V|^2).</br>
     * Runtime if using PriorityQueue: O((|V| + |E|) * log|V|).</br>
     * BZ: Priority Queue stores same integer?<ul>
     *     <li>when comparing dist[v], will stop by same v...
     *     <li>=> Wrap {v, dist-value}; sort PQ on dist-value.
     *     <li>=> Even offering same v, the dist-value differs.</ul>
     */
    private static class DistNode {
        int v;
        int dist;
        public DistNode(int v, int d) {
            this.v = v;
            this.dist = d;
        }
    }
    private static int distance_faster(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int n = adj.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<DistNode> pq = new PriorityQueue<>(new Comparator<DistNode>(){
            @Override
            public int compare(DistNode v1, DistNode v2) {
                // BZ: v1.dist - v2.dist? if v2.dist is +inf? overflow!
                return v1.dist < v2.dist ? -1 : 1;
            }
        });
        for (int i = 1; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            pq.offer(new DistNode(i, Integer.MAX_VALUE));
        }
        dist[0] = 0;
        pq.offer(new DistNode(0, 0));
        while (! pq.isEmpty()) {
            DistNode u = pq.poll();
            if (visited[u.v]) continue;
            visited[u.v] = true;
            for (int i = 0; i < adj[u.v].size(); i++) {
                int next = adj[u.v].get(i), w = cost[u.v].get(i);
                if (dist[next] > dist[u.v] + w) {
                    dist[next] = dist[u.v] + w;
                    pq.offer(new DistNode(next, dist[next]));
                }
            }
        }
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    }
}

