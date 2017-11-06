import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Evacuation {
    private static FastScanner in;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();

        FlowGraph graph = readGraph();
        System.out.println(maxFlow(graph, 0, graph.size() - 1));
    }

    static int maxFlow(FlowGraph graph, int from, int to) {

        int flow = 0;
        int[] path = new int[graph.size()];
        int[] pathEdge = new int[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        boolean[] visitedSrc = new boolean[graph.size()];
        LinkedList<Integer> queue = new LinkedList<>();
        boolean newPathAdded = true;
        Integer vertex;
        int[] pathSrc = new int[graph.size()];
        for (int i = 0; i < pathSrc.length; i++) {
            pathSrc[i] = -1;
            visitedSrc[i] = false;
        }
        while (newPathAdded) {
            System.arraycopy(pathSrc, 0, path, 0, graph.size());
            System.arraycopy(visitedSrc, 0, visited, 0, graph.size());
            queue.add(from);
            path[from] = -1;
            newPathAdded = false;
            while (!queue.isEmpty()) {
                vertex = queue.removeFirst();
                if (visited[vertex]) {
                    continue;
                }
                visited[vertex] = true;
                if (vertex == to) {
                    queue.clear();
                    break;
                }
                List<Integer> list = graph.graph[vertex];
                for (Integer i : list) {
                    Edge edge = graph.edges.get(i);
                    if (visited[edge.to] || edge.capacity == edge.flow) {
                        continue;
                    }
                    path[edge.to] = vertex;
                    if (edge.to == to) {
                        queue.clear();
                        break;
                    }
                    queue.add(edge.to);
                }
            }
            //min path
            int min = Integer.MAX_VALUE;
            int ind = to;
            while (path[ind] != -1) {
                List<Integer> list = graph.graph[path[ind]];
                int currentAvailableFlow = -1;
                for (Integer i : list) {
                    Edge edge = graph.edges.get(i);
                    int available = edge.capacity - edge.flow;
                    if (edge.to == ind && available > 0) {
                        currentAvailableFlow = currentAvailableFlow >= available ? currentAvailableFlow : available;
                        pathEdge[path[ind]] = i;
                    }
                }
                min = min > currentAvailableFlow ? currentAvailableFlow : min;
                ind = path[ind];
            }
            if (min < Integer.MAX_VALUE) {
                flow += min;
                newPathAdded = true;

                ind = path[to];
                while (ind != -1) {
                    graph.addFlow(pathEdge[ind], min);
                    ind = path[ind];
                }
            }
        }

        return flow;
    }

    static FlowGraph readGraph() throws IOException {
        int vertex_count = in.nextInt();
        int edge_count = in.nextInt();
        FlowGraph graph = new FlowGraph(vertex_count);

        for (int i = 0; i < edge_count; ++i) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, capacity = in.nextInt();
            graph.addEdge(from, to, capacity);
        }
        return graph;
    }

    static class Edge {
        int from, to, capacity, flow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    from + "->" + to +
                    ", [" + capacity +
                    "," + flow + "]}";
        }
    }

    /* This class implements a bit unusual scheme to store the graph edges, in order
     * to retrieve the backward edge for a given edge quickly. */
    static class FlowGraph {
        /* List of all - forward and backward - edges */
        private List<Edge> edges;

        /* These adjacency lists store only indices of edges from the edges list */
        private List<Integer>[] graph;

        public FlowGraph(int n) {
            this.graph = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; ++i) {
                this.graph[i] = new ArrayList<>();
            }
            this.edges = new ArrayList<>();
        }

        public void addEdge(int from, int to, int capacity) {
            /* Note that we first append a forward edge and then a backward edge,
             * so all forward edges are stored at even indices (starting from 0),
             * whereas backward edges are stored at odd indices. */
            Edge forwardEdge = new Edge(from, to, capacity);
            Edge backwardEdge = new Edge(to, from, 0);
            graph[from].add(edges.size());
            edges.add(forwardEdge);
            graph[to].add(edges.size());
            edges.add(backwardEdge);
        }

        public int size() {
            return graph.length;
        }

        public List<Integer> getIds(int from) {
            return graph[from];
        }

        public Edge getEdge(int id) {
            return edges.get(id);
        }

        public void addFlow(int id, int flow) {
            /* To get a backward edge for a true forward edge (i.e id is even), we should get id + 1
             * due to the described above scheme. On the other hand, when we have to get a "backward"
             * edge for a backward edge (i.e. get a forward edge for backward - id is odd), id - 1
             * should be taken.
             *
             * It turns out that id ^ 1 works for both cases. Think this through! */
            edges.get(id).flow += flow;
            edges.get(id ^ 1).flow -= flow;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
