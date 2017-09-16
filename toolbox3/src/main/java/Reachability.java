import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Reachability {
    static int reach(ArrayList<Integer>[] adj, int x, int y) {

        boolean visited[] = new boolean[adj.length];
        visited[x] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(x);
        while (queue.size() > 0) {
            Integer node = queue.removeFirst();
            visited[node] = true;
            if (node == y) {
                return 1;
            }
            for (Integer nd : adj[node]) {
                if (!visited[nd]) {
                    queue.addLast(nd);
                }
            }
        }
        return 0;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }

}

