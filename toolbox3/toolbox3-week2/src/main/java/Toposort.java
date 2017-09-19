import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {

    private static boolean[] visited;
    private static ArrayList<Integer> order;

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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

