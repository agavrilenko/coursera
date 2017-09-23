import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Bipartite {
    static int bipartite(ArrayList<Integer>[] adj) {
        boolean visited[] = new boolean[adj.length];
        int[] path = new int[adj.length];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(0);
        path[0] = 1;

        ArrayList<Integer>[] adjT = (ArrayList<Integer>[]) new ArrayList[adj.length];
        for (int i = 0; i < adj.length; i++) {
            adjT[i] = new ArrayList<>();
        }
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                adjT[i].add(adj[i].get(j));
                adjT[adj[i].get(j)].add(i);
            }
        }

        while (queue.size() > 0) {
            Integer node = queue.removeFirst();
            int next = path[node] == 1 ? 2 : 1;
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            for (Integer nd : adjT[node]) {
                if (path[node] == path[nd]) {
                    return 0;
                }
                if (!visited[nd]) {
                    queue.addLast(nd);
                    path[nd] = next;
                }
            }
        }
        return 1;
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
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

