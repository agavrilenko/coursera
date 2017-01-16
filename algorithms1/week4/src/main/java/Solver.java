import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

/**
 * Created by trash on 22-Nov-16.
 */
public class Solver {
    private List<Board> solution;
    private boolean isSolvable = true;


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        BoardExt in = new BoardExt(initial);
        BoardExt parallel = in.twin();
        Comparator<BoardExt> comparator = new SolverComparator();
        MinPQ<BoardExt> pq = new MinPQ<>(comparator);
        MinPQ<BoardExt> pqTwin = new MinPQ<>(comparator);

        BoardExt cand = in;
        BoardExt candTwin = parallel;
        if (!cand.current.isGoal() && !candTwin.current.isGoal()) {
            // incoming board
            pq.insert(in);
            pqTwin.insert(candTwin);
            while (true) {
                cand = pq.delMin();
                if (cand.current.isGoal()) {
                    break;
                }
                Iterable<Board> neighbors = cand.current.neighbors();
                Iterator<Board> iter = neighbors.iterator();
                while (iter.hasNext()) {

                    Board neighbor = iter.next();
                    if (cand.parent == null || !neighbor.equals(cand.parent.current)) {
                        pq.insert(new BoardExt(neighbor, cand));
                    }
                }
                // procedure for twin board
                candTwin = pqTwin.delMin();
                if (candTwin.current.isGoal()) {
                    break;
                }
                Iterable<Board> neighborsTwin = candTwin.current.neighbors();
                Iterator<Board> iterTwin = neighborsTwin.iterator();
                while (iterTwin.hasNext()) {
                    Board neighborTwin = iterTwin.next();
                    if (candTwin.parent == null || !neighborTwin.equals(candTwin.parent.current)) {
                        pqTwin.insert(new BoardExt(neighborTwin, candTwin));
                    }
                }
            }
        }
        if (cand.current.isGoal()) {
            solution = new ArrayList<>();
            solution.add(cand.current);
            while (cand.parent != null) {
                cand = cand.parent;
                solution.add(cand.current);
            }
            Collections.reverse(solution);
        } else {
            isSolvable = false;
        }

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solution.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }

    private static class BoardExt {
        private BoardExt parent;
        private Board current;
        private int cnt;

        public BoardExt(Board current) {
            this.current = current;
        }

        public BoardExt(Board current, BoardExt parent) {
            this.parent = parent;
            this.current = current;
            this.cnt = this.parent.cnt + 1;
        }

        public int manhattan() {
            return current.manhattan() + cnt;
        }

        public BoardExt twin() {
            return new BoardExt(current.twin());
        }
    }

    private static class SolverComparator implements Comparator<BoardExt> {
        @Override
        public int compare(BoardExt o1, BoardExt o2) {
            int mnh1 = o1.manhattan();
            int mnh2 = o2.manhattan();
            return mnh1 == mnh2 ? 0 : mnh1 > mnh2 ? 1 : -1;
        }
    }
}