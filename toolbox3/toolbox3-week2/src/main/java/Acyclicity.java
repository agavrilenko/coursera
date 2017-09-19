import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {

    private static boolean[] visited;
    private static boolean[] stack;
    private static boolean hasCycle = false;

    static int acyclic(ArrayList<Integer>[] adj) {
        hasCycle = false;
        visited = new boolean[adj.length];
        stack = new boolean[adj.length];

        ArrayList<Integer> current = adj[0];
        visited[0] = true;
        stack[0] = true;
        dfs(current, adj);

        return hasCycle ? 1 : 0;
    }

    private static void dfs(ArrayList<Integer> current, ArrayList<Integer>[] adj) {

        for (Integer node : current) {
            if (hasCycle) {
                return;
            }
            if (stack[node]) {
                hasCycle = true;
                return;
            }

            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            stack[node] = true;
            dfs(adj[node], adj);
            stack[node] = false;
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
        System.out.println(acyclic(adj));
    }
}

