import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    private static boolean[] visited;
    private static ArrayList<Integer> order;

    static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //create reverse adj
        ArrayList<Integer>[] rAdj = createReverseAdj(adj);
        // post order of reversed adj
        ArrayList<Integer> order = toposort(rAdj);
        //build scc by post order of rev adj
        visited = new boolean[adj.length];
        int count = 0;
        for (int i = 0; i < order.size(); i++) {
            if (visited[order.get(i)]) {
                continue;
            }
            count++;
            dfs1(adj, order.get(i));

        }

        return count;
    }


    static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        visited = new boolean[adj.length];
        order = new ArrayList<>();


        for (int i = 0; i < adj.length; i++) {
            if (visited[i]) {
                continue;
            }
            dfs(adj, i);
            order.add(i);
        }

        Collections.reverse(order);
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int s) {
        visited[s] = true;
        if (adj[s].size() == 0) {
            return;
        }
        for (Integer node : adj[s]) {
            if (visited[node]) {
                continue;
            }
            dfs(adj, node);
            order.add(node);
        }
    }

    private static void dfs1(ArrayList<Integer>[] adj, int s) {
        visited[s] = true;
        if (adj[s].size() == 0) {
            return;
        }
        for (Integer node : adj[s]) {
            if (visited[node]) {
                continue;
            }
            dfs1(adj, node);
        }
    }

    static ArrayList<Integer>[] createReverseAdj(ArrayList<Integer>[] adj) {
        ArrayList<Integer>[] rAdj = (ArrayList<Integer>[]) new ArrayList[adj.length];
        for (int i = 0; i < adj.length; i++) {
            rAdj[i] = new ArrayList<Integer>();

        }
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                rAdj[adj[i].get(j)].add(i);
            }
        }
        return rAdj;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

