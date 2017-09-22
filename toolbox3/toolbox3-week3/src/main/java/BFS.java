import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BFS {
    static int distance(ArrayList<Integer>[] adj, int x, int y) {
        boolean visited[] = new boolean[adj.length];
        int[] path = new int[adj.length];
        ArrayList<Integer>[] adjT = (ArrayList<Integer>[]) new ArrayList[adj.length];
        for (int i = 0; i < adj.length; i++) {
            path[i] = -1;
            adjT[i] = new ArrayList<>();
        }
        for (int i = 0; i < adj.length; i++) {

            for (int j = 0; j < adj[i].size(); j++) {
                adjT[i].add(adj[i].get(j));
                adjT[adj[i].get(j)].add(i);
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(x);
        while (queue.size() > 0) {
            Integer node = queue.removeFirst();
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            for (Integer nd : adjT[node]) {
                if (!visited[nd]) {
                    queue.addLast(nd);
                    if (path[nd] == -1) {
                        path[nd] = node;
                    }
//                    visited[nd] = true;
                }
            }
        }

        LinkedList<Integer> resultPath = new LinkedList<>();
        Integer initialNode = y;
//        resultPath.addFirst(initialNode);
        while (initialNode != -1) {
            int node = path[initialNode];
            if (node != -1) {
                resultPath.addFirst(node);
            }
            initialNode = node;
        }
        return resultPath.size() > 0 ? resultPath.size() : -1;
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
        System.out.println(distance(adj, x, y));
    }
}

