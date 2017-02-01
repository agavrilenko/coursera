import edu.princeton.cs.algs4.In;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by trash on 30-Jan-17.
 */
public class BaseballElimination {
    private int[] w, l, r;
    private int[][] g;
    private Map<String, Integer> teams = new LinkedHashMap<>();

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In input = new In(new Scanner(BaseballElimination.class.getClassLoader().getResourceAsStream(filename)));
        String[] data = input.readAllLines();
        int length = Integer.valueOf(data[0]).intValue();
        w = new int[length];
        l = new int[length];
        r = new int[length];
        g = new int[length][length];
        for (int i = 1; i < data.length; i++) {
            String[] p = data[i].replaceAll(" +"," ").split(" ");
            String name = p[0];
            teams.put(name, i - 1);
            w[i - 1] = Integer.parseInt(p[1]);
            l[i - 1] = Integer.parseInt(p[2]);
            r[i - 1] = Integer.parseInt(p[3]);
            for (int j = 4; j < 4 + length; j++) {
                g[i - 1][j - 4] = Integer.parseInt(p[j]);
            }

        }
    }

    // number of teams
    public int numberOfTeams() {
        return teams.size();
    }

    // all teams
    public Iterable<String> teams() {
        return teams.keySet();
    }

    // number of wins for given team
    public int wins(String team) {
        return w[teams.get(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        return l[teams.get(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        return r[teams.get(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return g[teams.get(team1)][teams.get(team2)];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        return false;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }
}
