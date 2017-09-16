import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ConnectedComponents {
    static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        int visited[] = new int[adj.length];

        for (int i = 0; i < adj.length; i++) {
            if (visited[i] != 0) {
                continue;
            }
            result++;
            LinkedList<Integer> queue = new LinkedList<>();
            queue.push(i);
            while (queue.size() > 0) {
                Integer node = queue.removeFirst();
                if (visited[node] != 0) {
                    continue;
                }
                visited[node] = result;

                for (Integer nd : adj[node]) {
                    queue.addLast(nd);
                }
            }
        }

        return result;
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
        System.out.println(numberOfComponents(adj));
    }
}

