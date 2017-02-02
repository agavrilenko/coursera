import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by trash on 30-Jan-17.
 */
public class BaseballElimination {
    private int[] w, l, r;
    private int[][] g;
    private Map<String, Integer> teams = new LinkedHashMap<>();
    private Map<String, Boolean> eliminated = new HashMap<>();
    private Map<String, List<String>> certificates = new HashMap<>();

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
            String[] p = data[i].replaceAll(" +", " ").split(" ");
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

    private void checkTrivialCase(String team) {
        int teamId = teams.get(team);
        for (int i = 0; i < numberOfTeams(); i++) {
            if (i == teamId) {
                continue;
            }
            if (w[teamId] + r[teamId] < w[i]) {
                eliminated.put(team, true);
                List<String> list = certificates.get(team);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(teams.keySet().toArray(new String[]{})[i]);
                certificates.put(team, list);
            }
        }
    }


    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (eliminated.get(team) != null) {
            return eliminated.get(team);
        }
        checkTrivialCase(team);
        if (eliminated.get(team) != null) {
            return eliminated.get(team);
        }
        createFordFulkerson(team);
        return eliminated.get(team);
    }

    private void createFordFulkerson(String team) {
        int teamIndex = teams.get(team);

        LinkedList<String> tTeams = new LinkedList<>();
        for (String t : teams.keySet().toArray(new String[]{})) {
            if (!t.equals(team)) {
                tTeams.add(t);
            }
        }

        int[][] gTmp = new int[tTeams.size()][tTeams.size()];
        int gI = 0, gJ;
        for (int i = 0; i < g.length; i++) {
            if (i == teamIndex) {
                continue;
            }
            gJ = 0;
            for (int j = 0; j < g.length; j++) {
                if (j == teamIndex) {
                    continue;
                }
                gTmp[gI][gJ++] = g[i][j];
            }
            gI++;
        }

        // s + t + teams wo team + number of games inside wo team
        int interGames = (teams.size() - 2) * (teams.size() - 1) / 2;
        int size = 1 + 1 + teams.size() - 1 + interGames;
        FlowNetwork flow = new FlowNetwork(size);
        // s = 0; t = size -1
        // init s
        int throughInd = 1;
        int possibleWinsForTeam = w[teamIndex] + r[teamIndex];
        int teamsInd = 0;
        for (int i = 0; i < gTmp.length; i++) {
            for (int j = i + 1; j < gTmp.length; j++) {
                // connect s and games [i][j]
                flow.addEdge(new FlowEdge(0, throughInd, gTmp[i][j]));
                // connect games [i][j] with team i not including examinated team
                flow.addEdge(new FlowEdge(throughInd, size - (teams.size()) + i, Double.POSITIVE_INFINITY));
                flow.addEdge(new FlowEdge(throughInd++, size - (teams.size()) + j, Double.POSITIVE_INFINITY));
            }
        }
        // init t
        teamsInd = 0;
        for (int i = 0; i < teams.size() - 1; i++) {
            if (i == teamIndex) {
                continue;
            }
            // connect team[i] with t
            flow.addEdge(new FlowEdge(size - teams.size() + teamsInd, size - 1, possibleWinsForTeam - w[i]));
            teamsInd++;
        }
        LocalFordFulkerson ford = new LocalFordFulkerson(flow, 0, size - 1);
        boolean elilm = false;
        for (FlowEdge edge : flow.adj(0)) {
            if (edge.from() == 0 && edge.residualCapacityTo(edge.to()) > 0) {
                elilm = true;
                break;
            }
        }
        eliminated.put(team, elilm);
        List<String> certificate = new ArrayList<>();
        int offset = interGames + 1;
        for (FlowEdge edge : flow.adj(size - 1)) {
            int from = edge.from();
            if (ford.inCut(from)) {
                String elimTeam = tTeams.get(from - offset);
                certificate.add(elimTeam);
            }
        }
        certificates.put(team, certificate);
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (certificates.get(team) != null) {
            return certificates.get(team);
        }

        checkTrivialCase(team);
        if (certificates.get(team) != null) {
            return certificates.get(team);
        }
        createFordFulkerson(team);
        return certificates.get(team);
    }

    private static class LocalFordFulkerson {
        private static final double FLOATING_POINT_EPSILON = 1.0E-11D;
        private boolean[] marked;
        private FlowEdge[] edgeTo;
        private double value;

        public LocalFordFulkerson(FlowNetwork G, int s, int t) {
            this.validate(s, G.V());
            this.validate(t, G.V());
            if (s == t) {
                throw new IllegalArgumentException("Source equals sink");
            } else if (!this.isFeasible(G, s, t)) {
                throw new IllegalArgumentException("Initial flow is infeasible");
            } else {
                double bottle;
                for (this.value = this.excess(G, t); this.hasAugmentingPath(G, s, t); this.value += bottle) {
                    bottle = 1.0D / 0.0;

                    int v;
                    for (v = t; v != s; v = this.edgeTo[v].other(v)) {
                        bottle = Math.min(bottle, this.edgeTo[v].residualCapacityTo(v));
                    }

                    for (v = t; v != s; v = this.edgeTo[v].other(v)) {
                        this.edgeTo[v].addResidualFlowTo(v, bottle);
                    }
                }

                assert this.check(G, s, t);

            }
        }

        public double value() {
            return this.value;
        }

        public boolean inCut(int v) {
            this.validate(v, this.marked.length);
            return this.marked[v];
        }

        private void validate(int v, int V) {
            if (v < 0 || v >= V) {
                throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V - 1));
            }
        }

        private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
            this.edgeTo = new FlowEdge[G.V()];
            this.marked = new boolean[G.V()];
            Queue queue = new Queue();
            queue.enqueue(Integer.valueOf(s));
            this.marked[s] = true;

            while (!queue.isEmpty() && !this.marked[t]) {
                int v = ((Integer) queue.dequeue()).intValue();
                Iterator i$ = G.adj(v).iterator();

                while (i$.hasNext()) {
                    FlowEdge e = (FlowEdge) i$.next();
                    int w = e.other(v);
                    if (e.residualCapacityTo(w) > 0.0D && !this.marked[w]) {
                        this.edgeTo[w] = e;
                        this.marked[w] = true;
                        queue.enqueue(Integer.valueOf(w));
                    }
                }
            }

            return this.marked[t];
        }

        private double excess(FlowNetwork G, int v) {
            double excess = 0.0D;
            Iterator i$ = G.adj(v).iterator();

            while (i$.hasNext()) {
                FlowEdge e = (FlowEdge) i$.next();
                if (v == e.from()) {
                    excess -= e.flow();
                } else {
                    excess += e.flow();
                }
            }

            return excess;
        }

        private boolean isFeasible(FlowNetwork G, int s, int t) {
            int v;
            for (v = 0; v < G.V(); ++v) {
                Iterator i$ = G.adj(v).iterator();

                while (i$.hasNext()) {
                    FlowEdge e = (FlowEdge) i$.next();
                    if (e.flow() < -1.0E-11D || e.flow() > e.capacity() + 1.0E-11D) {
                        System.err.println("Edge does not satisfy capacity constraints: " + e);
                        return false;
                    }
                }
            }

            if (Math.abs(this.value + this.excess(G, s)) > 1.0E-11D) {
                System.err.println("Excess at source = " + this.excess(G, s));
                System.err.println("Max flow         = " + this.value);
                return false;
            } else if (Math.abs(this.value - this.excess(G, t)) > 1.0E-11D) {
                System.err.println("Excess at sink   = " + this.excess(G, t));
                System.err.println("Max flow         = " + this.value);
                return false;
            } else {
                for (v = 0; v < G.V(); ++v) {
                    if (v != s && v != t && Math.abs(this.excess(G, v)) > 1.0E-11D) {
                        System.err.println("Net flow out of " + v + " doesn\'t equal zero");
                        return false;
                    }
                }

                return true;
            }
        }

        private boolean check(FlowNetwork G, int s, int t) {
            if (!this.isFeasible(G, s, t)) {
                System.err.println("Flow is infeasible");
                return false;
            } else if (!this.inCut(s)) {
                System.err.println("source " + s + " is not on source side of min cut");
                return false;
            } else if (this.inCut(t)) {
                System.err.println("sink " + t + " is on source side of min cut");
                return false;
            } else {
                double mincutValue = 0.0D;

                for (int v = 0; v < G.V(); ++v) {
                    Iterator i$ = G.adj(v).iterator();

                    while (i$.hasNext()) {
                        FlowEdge e = (FlowEdge) i$.next();
                        if (v == e.from() && this.inCut(e.from()) && !this.inCut(e.to())) {
                            mincutValue += e.capacity();
                        }
                    }
                }

                if (Math.abs(mincutValue - this.value) > 1.0E-11D) {
                    System.err.println("Max flow value = " + this.value + ", min cut value = " + mincutValue);
                    return false;
                } else {
                    return true;
                }
            }
        }


    }

}
