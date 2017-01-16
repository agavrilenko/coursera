import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by trash on 25-Nov-16.
 */
public class SolverTest {

    @Test
    public void testSimpleSolution_Degenerate() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        Solver solver = new Solver(board);
        Assert.assertEquals(0, solver.moves());
        Assert.assertTrue(solver.solution().iterator().hasNext());
        Assert.assertEquals(board, solver.solution().iterator().next());

    }

    @Test
    public void testSimpleSolution() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
        Board solution = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

        Solver solver = new Solver(board);
        Assert.assertEquals(1, solver.moves());
        Iterator<Board> iterator = solver.solution().iterator();
        Assert.assertTrue(iterator.hasNext());
        Board next = iterator.next();
        Assert.assertEquals(board, next);
        next = iterator.next();
        Assert.assertEquals(solution, next);
    }

    @Test
    public void testSimpleSolution_TwoMoves() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {0, 7, 8}});
        Board interm = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
        Board solution = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

        Solver solver = new Solver(board);
        Assert.assertEquals(2, solver.moves());
        Iterator<Board> iterator = solver.solution().iterator();
        Assert.assertTrue(iterator.hasNext());
        Board next = iterator.next();
        Assert.assertEquals(board, next);
        next = iterator.next();
//        Assert.assertEquals(interm, next);
        next = iterator.next();
        Assert.assertEquals(solution, next);
    }

    @Test
    public void testSimpleSolution_Puzzle04() {
        Board board = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});

        Board solution = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

        Solver solver = new Solver(board);
        Assert.assertEquals(4, solver.moves());
        Iterator<Board> iterator = solver.solution().iterator();
        Assert.assertTrue(iterator.hasNext());
        Board next = iterator.next();
        Assert.assertEquals(board, next);
        iterator.next();
        iterator.next();
        iterator.next();
        next = iterator.next();
        Assert.assertEquals(solution, next);


    }

    @Test
    public void testSimpleSolution_Unsolvable() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {8, 7, 0}});

        Solver solver = new Solver(board);
        Assert.assertFalse(solver.isSolvable());
    }

    @Test
    public void testSimpleSolution_Unsolvable_WithChange() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 6, 5}, {8, 7, 0}});

        Solver solver = new Solver(board);
        Assert.assertTrue(solver.isSolvable());
        System.out.println("Number of moves is " + solver.moves());
        Iterator<Board> iter = solver.solution().iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    @Ignore
    @Test
    public void testSimpleSolution_Puzzle4x4_unsolvable() {
        Board board = new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 15, 14, 0}});

        Solver solver = new Solver(board);
        Assert.assertFalse(solver.isSolvable());
    }

    @Test
    public void testSimpleSolution_Puzzle06() {
        Board board = new Board(new int[][]{{0, 1, 2, 3}, {5, 6, 7, 4}, {9, 10, 11, 8}, {13, 14, 15, 12}});

        Solver solver = new Solver(board);
        Assert.assertTrue(solver.isSolvable());
        Assert.assertEquals(6, solver.moves());
    }


}